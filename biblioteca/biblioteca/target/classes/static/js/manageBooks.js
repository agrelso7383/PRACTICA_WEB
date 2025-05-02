window.onload = fetchBooks;

async function fetchBooks() {
  const response = await fetch("/api/books");
  const books = await response.json();
  renderBooks(books);
}

function renderBooks(books) {
  const container = document.getElementById("booksContainer");
  container.innerHTML = "";

  books.forEach(book => {
    const div = document.createElement("div");
    div.className = "col-md-4";

    div.innerHTML = `
      <div class="card book-item">
        <div class="card-body">
          <h5 class="card-title">${book.title}</h5>
          <p class="card-text">Author: ${book.author}</p>
          <button class="btn btn-info btn-sm me-2" onclick="loadBookForEdit(${book.id}, '${book.title}', '${book.author}')">Update</button>
          <button class="btn btn-danger btn-sm" onclick="deleteBook(${book.id})">Delete</button>
        </div>
      </div>
    `;

    container.appendChild(div);
  });
}

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

function loadBookForEdit(id, title, author) {
  document.getElementById("bookTitle").value = title;
  document.getElementById("bookAuthor").value = author;
  document.getElementById("editBookId").value = id;
  document.getElementById("submitBtn").style.display = "none";
  document.getElementById("updateBtn").style.display = "inline-block";
}

async function updateBook() {
  const id = document.getElementById("editBookId").value;
  const title = document.getElementById("bookTitle").value.trim();
  const author = document.getElementById("bookAuthor").value.trim();

  if (id && title && author) {
    await fetch("/api/books/" + id, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ title, author })
    });

    resetForm();
    fetchBooks();
  }
}

async function deleteBook(id) {
  await fetch("/api/books/" + id, { method: "DELETE" });
  fetchBooks();
}

function resetForm() {
  document.getElementById("bookTitle").value = "";
  document.getElementById("bookAuthor").value = "";
  document.getElementById("editBookId").value = "";
  document.getElementById("submitBtn").style.display = "inline-block";
  document.getElementById("updateBtn").style.display = "none";
}
