window.onload = fetchLoans;

async function fetchLoans() {
  const response = await fetch("/api/loans");
  const loansData = await response.json();
  renderLoans(loansData);
}

function renderLoans(loans) {
  const container = document.getElementById("loansContainer");
  container.innerHTML = "";

  const keys = Object.keys(loans);
  if (keys.length === 0) {
    container.innerHTML = "<p>No loans available.</p>";
    return;
  }

  keys.forEach(id => {
    const loan = loans[id];
    const loanDiv = document.createElement("div");
    loanDiv.classList.add("loan-item", "d-flex", "justify-content-between", "align-items-center", "p-3", "bg-white", "rounded", "shadow-sm", "mb-3");
    loanDiv.innerHTML = `
      <span><strong>${loan.book}</strong> loaned to ${loan.user} — Loan: ${loan.loanDate} | Return: ${loan.returnDate}</span>
      <div>
        <button class="btn btn-sm btn-secondary me-2" onclick="loadLoanForEdit('${id}', '${loan.book}', '${loan.user}', '${loan.loanDate}', '${loan.returnDate}')">Update</button>
        <button class="btn btn-sm btn-danger" onclick="deleteLoan('${id}')">Delete</button>
      </div>
    `;
    container.appendChild(loanDiv);
  });
}

async function addLoan(event) {
  event.preventDefault();

  const book = document.getElementById("loanBook").value.trim();
  const user = document.getElementById("loanUser").value.trim();
  const loanDate = document.getElementById("loanDate").value.trim();
  const returnDate = document.getElementById("returnDate").value.trim();

  if (book && user && loanDate && returnDate) {
    await fetch("/api/loans", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ book, user, loanDate, returnDate })
    });

    resetForm();
    fetchLoans();
  }
}

function loadLoanForEdit(id, book, user, loanDate, returnDate) {
  document.getElementById("loanBook").value = book;
  document.getElementById("loanUser").value = user;
  document.getElementById("loanDate").value = loanDate;
  document.getElementById("returnDate").value = returnDate;
  document.getElementById("editingLoanId").value = id;

  document.getElementById("submitButton").classList.add("d-none");
  document.getElementById("updateButtonPatch").classList.remove("d-none");
}

async function updateLoanPATCH() {
  const id = document.getElementById("editingLoanId").value;
  const book = document.getElementById("loanBook").value.trim();
  const user = document.getElementById("loanUser").value.trim();
  const loanDate = document.getElementById("loanDate").value.trim();
  const returnDate = document.getElementById("returnDate").value.trim();

  const body = {};
  if (book) body.book = book;
  if (user) body.user = user;
  if (loanDate) body.loanDate = loanDate;
  if (returnDate) body.returnDate = returnDate;

  if (id) {
    await fetch(`/api/loans/${id}`, {
      method: "PATCH",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(body)
    });

    resetForm();
    fetchLoans();
  }
}

async function deleteLoan(id) {
  await fetch(`/api/loans/${id}`, { method: "DELETE" });
  fetchLoans();
}

function resetForm() {
  document.getElementById("addLoanForm").reset();
  document.getElementById("editingLoanId").value = "";
  document.getElementById("submitButton").classList.remove("d-none");
  document.getElementById("updateButtonPatch").classList.add("d-none");
}

document.getElementById("addLoanForm").addEventListener("submit", addLoan);
document.getElementById("updateButtonPatch").addEventListener("click", updateLoanPATCH);
