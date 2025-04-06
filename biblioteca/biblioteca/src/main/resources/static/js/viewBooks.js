window.onload = fetchBooks;

async function fetchBooks() {
  try {
    const response = await fetch('/api/books');
    const data = await response.json();

    const booksList = document.getElementById('booksList');

    if (Object.keys(data).length === 0) {
      booksList.innerHTML = '<p>No books available.</p>';
      return;
    }

    Object.values(data).forEach(book => {
      const bookItem = document.createElement('div');
      bookItem.classList.add('book-item', 'mb-3', 'p-3', 'bg-light', 'rounded', 'shadow-sm');
      bookItem.innerHTML = `
        <h4>${book.title}</h4>
        <p><strong>Author:</strong> ${book.author}</p>
      `;
      booksList.appendChild(bookItem);
    });
  } catch (error) {
    console.error('Error fetching books:', error);
  }
}
