window.onload = fetchLoans;

async function fetchLoans() {
  const response = await fetch("/api/loans");
  const loans = await response.json();
  renderLoans(loans);
}

function renderLoans(loans) {
  const container = document.getElementById("loansContainer");
  container.innerHTML = "";

  loans.forEach(loan => {
    const div = document.createElement("div");
    div.className = "col-md-4";

    div.innerHTML = `
      <div class="card book-item">
        <div class="card-body">
          <h5 class="card-title">Loan ID: ${loan.id}</h5>
          <p class="card-text">
            User ID: ${loan.user?.id || 'N/A'}<br>
            Book ID: ${loan.book?.id || 'N/A'}<br>
            Loan Date: ${loan.loanDate}<br>
            Return Date: ${loan.returnDate || '—'}
          </p>
          <button class="btn btn-info btn-sm me-2" onclick="loadLoanForEdit(${loan.id}, ${loan.user?.id}, ${loan.book?.id}, '${loan.loanDate}', '${loan.returnDate || ''}')">Update</button>
          <button class="btn btn-danger btn-sm" onclick="deleteLoan(${loan.id})">Delete</button>
        </div>
      </div>
    `;

    container.appendChild(div);
  });
}

async function addLoan(event) {
  event.preventDefault();
  const user = { id: parseInt(document.getElementById("userId").value) };
  const book = { id: parseInt(document.getElementById("bookId").value) };
  const loanDate = document.getElementById("loanDate").value;
  const returnDate = document.getElementById("returnDate").value;

  const loan = { user, book, loanDate, returnDate };

  await fetch("/api/loans", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(loan)
  });

  resetForm();
  fetchLoans();
}

function loadLoanForEdit(id, userId, bookId, loanDate, returnDate) {
  document.getElementById("userId").value = userId;
  document.getElementById("bookId").value = bookId;
  document.getElementById("loanDate").value = loanDate;
  document.getElementById("returnDate").value = returnDate || "";
  document.getElementById("editLoanId").value = id;
  document.getElementById("submitBtn").style.display = "none";
  document.getElementById("updateBtn").style.display = "inline-block";
}

async function updateLoan() {
  const id = document.getElementById("editLoanId").value;
  const user = { id: parseInt(document.getElementById("userId").value) };
  const book = { id: parseInt(document.getElementById("bookId").value) };
  const loanDate = document.getElementById("loanDate").value;
  const returnDate = document.getElementById("returnDate").value;

  const loan = { user, book, loanDate, returnDate };

  await fetch("/api/loans/" + id, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(loan)
  });

  resetForm();
  fetchLoans();
}

async function deleteLoan(id) {
  await fetch("/api/loans/" + id, { method: "DELETE" });
  fetchLoans();
}

function resetForm() {
  document.getElementById("userId").value = "";
  document.getElementById("bookId").value = "";
  document.getElementById("loanDate").value = "";
  document.getElementById("returnDate").value = "";
  document.getElementById("editLoanId").value = "";
  document.getElementById("submitBtn").style.display = "inline-block";
  document.getElementById("updateBtn").style.display = "none";
}
