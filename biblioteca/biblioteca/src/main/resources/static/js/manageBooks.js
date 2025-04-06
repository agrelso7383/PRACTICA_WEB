// When the page loads, fetch the list of books from the server
window.onload = fetchBooks;

// Fetch all books from the backend API
async function fetchBooks() {
  const response = await fetch("/api/books");
  const booksData = await response.json();
  renderBooks(booksData);
}

// Display the list of books on the page
function renderBooks(books) {
  const container = document.getElementById("booksContainer");
  container.innerHTML = "";

  const keys = Object.keys(books);
  if (keys.length === 0) {
    container.innerHTML = "<p>No books available.</p>";
    return;
  }

   // For each book, create a card with title, author, and buttons
  keys.forEach(id => {
    const book = books[id];
    const bookDiv = document.createElement("div");
    bookDiv.classList.add("book-item", "d-flex", "justify-content-between", "align-items-center", "p-3", "bg-white", "rounded", "shadow-sm", "mb-3");
    bookDiv.innerHTML = `
      <span><strong>${book.title}</strong> by ${book.author}</span>
      <div>
        <button class="btn btn-sm btn-secondary me-2" onclick="loadBookForEdit('${id}', '${book.title}', '${book.author}')">Update</button>
        <button class="btn btn-sm btn-danger" onclick="deleteBook('${id}')">Delete</button>
      </div>
    `;
    container.appendChild(bookDiv);
  });
}

// Add a new book using POST
async function addBook(event) {
  event.preventDefault();
  const title = document.getElementById("bookTitle").value.trim();
  const author = document.getElementById("bookAuthor").value.trim();

  if (title && author) {
    await fetch("/api/books", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify([{ title, author }])
    });

    resetForm();
    fetchBooks();
  }
}

// Load selected book data into the form to allow editing
function loadBookForEdit(id, title, author) {
  document.getElementById("bookTitle").value = title;
  document.getElementById("bookAuthor").value = author;
  document.getElementById("editingBookId").value = id;

  document.getElementById("submitButton").classList.add("d-none");
  document.getElementById("updateButtonPatch").classList.remove("d-none");
}

// Update the book fully (PUT)
async function updateBookPUT() {
  const id = document.getElementById("editingBookId").value;
  const title = document.getElementById("bookTitle").value.trim();
  const author = document.getElementById("bookAuthor").value.trim();

  if (id && title && author) {
    await fetch(`/api/books/${id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ id, title, author })
    });

    resetForm();
    fetchBooks();
  }
}

// Update only some fields of the book (PATCH)
async function updateBookPATCH() {
  const id = document.getElementById("editingBookId").value;
  const title = document.getElementById("bookTitle").value.trim();
  const author = document.getElementById("bookAuthor").value.trim();

  const body = {};
  if (title) body.title = title;
  if (author) body.author = author;

  if (id) {
    await fetch(`/api/books/${id}`, {
      method: "PATCH",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(body)
    });

    resetForm();
    fetchBooks();
  }
}

// Delete a book by its ID
async function deleteBook(id) {
  await fetch(`/api/books/${id}`, { method: "DELETE" });
  fetchBooks();
}

// Clear the form and reset buttons
function resetForm() {
  document.getElementById("addBookForm").reset();
  document.getElementById("editingBookId").value = "";
  document.getElementById("submitButton").classList.remove("d-none");
  document.getElementById("updateButtonPatch").classList.add("d-none");
}

document.getElementById("addBookForm").addEventListener("submit", addBook);
document.getElementById("updateButtonPut")?.addEventListener("click", updateBookPUT);
document.getElementById("updateButtonPatch").addEventListener("click", updateBookPATCH);
