window.onload = fetchUsers;

async function fetchUsers() {
  const response = await fetch("/api/users");
  const usersData = await response.json();
  renderUsers(usersData);
}

function renderUsers(users) {
  const container = document.getElementById("usersContainer");
  container.innerHTML = "";

  const keys = Object.keys(users);
  if (keys.length === 0) {
    container.innerHTML = "<p>No users available.</p>";
    return;
  }

  keys.forEach(id => {
    const user = users[id];
    const userDiv = document.createElement("div");
    userDiv.classList.add("book-item", "d-flex", "justify-content-between", "align-items-center", "p-3", "bg-white", "rounded", "shadow-sm", "mb-3");
    userDiv.innerHTML = `
      <span><strong>${user.name}</strong> - ${user.email}</span>
      <div>
        <button class="btn btn-sm btn-secondary me-2" onclick="loadUserForEdit('${id}', '${user.name}', '${user.email}')">Update</button>
        <button class="btn btn-sm btn-danger" onclick="deleteUser('${id}')">Delete</button>
      </div>
    `;
    container.appendChild(userDiv);
  });
}

async function addUser(event) {
  event.preventDefault();
  const name = document.getElementById("userName").value.trim();
  const email = document.getElementById("userEmail").value.trim();

  if (name && email) {
    await fetch("/api/users", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify([{ name, email }])
    });

    resetForm();
    fetchUsers();
  }
}

function loadUserForEdit(id, name, email) {
  document.getElementById("userName").value = name;
  document.getElementById("userEmail").value = email;
  document.getElementById("editingUserId").value = id;

  document.getElementById("submitButton").classList.add("d-none");
  document.getElementById("updateButtonPatch").classList.remove("d-none");
}

async function updateUserPATCH() {
  const id = document.getElementById("editingUserId").value;
  const name = document.getElementById("userName").value.trim();
  const email = document.getElementById("userEmail").value.trim();

  const body = {};
  if (name) body.name = name;
  if (email) body.email = email;

  if (id) {
    await fetch(`/api/users/${id}`, {
      method: "PATCH",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(body)
    });

    resetForm();
    fetchUsers();
  }
}

async function deleteUser(id) {
  await fetch(`/api/users/${id}`, { method: "DELETE" });
  fetchUsers();
}

function resetForm() {
  document.getElementById("addUserForm").reset();
  document.getElementById("editingUserId").value = "";
  document.getElementById("submitButton").classList.remove("d-none");
  document.getElementById("updateButtonPatch").classList.add("d-none");
}

document.getElementById("addUserForm").addEventListener("submit", addUser);
document.getElementById("updateButtonPatch").addEventListener("click", updateUserPATCH);
