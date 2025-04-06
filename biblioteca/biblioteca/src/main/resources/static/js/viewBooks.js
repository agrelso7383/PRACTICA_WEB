// When the page loads, fetch and display the list of books
window.onload = fetchBooks;

// Fetch books from the backend API and render them in the DOM
async function fetchBooks() {
  try {
    const response = await fetch('/api/books');
    const data = await response.json();

    const booksList = document.getElementById('booksList');

    // Show message if there are no books
    if (Object.keys(data).length === 0) {
      booksList.innerHTML = '<p>No books available.</p>';
      return;
    }

    // Create a styled element for each book
    Object.values(data).forEach(book => {
      const bookItem = document.createElement('div');
      bookItem.classList.add(
        'book-item',  // Custom styling class
        'mb-3',       // Margin bottom
        'p-3',        // Padding
        'bg-light',   // Light background
        'rounded',    // Rounded corners
        'shadow-sm'   // Soft shadow
        );
      bookItem.innerHTML = `
        <h4>${book.title}</h4>
        <p><strong>Author:</strong> ${book.author}</p>
      `;
      booksList.appendChild(bookItem);
    });
  } catch (error) {
    // Handle any errors from the fetch request
    console.error('Error fetching books:', error);
  }
}
