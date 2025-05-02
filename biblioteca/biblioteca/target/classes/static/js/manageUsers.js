window.onload = fetchUsers;

async function fetchUsers() {
  const response = await fetch("/api/users");
  const users = await response.json();
  renderUsers(users);
}

function renderUsers(users) {
  const container = document.getElementById("usersContainer");
  container.innerHTML = "";

  users.forEach(user => {
    const div = document.createElement("div");
    div.className = "col-md-4";

    div.innerHTML = `
      <div class="card book-item">
        <div class="card-body">
          <h5 class="card-title">${user.name}</h5>
          <p class="card-text">Email: ${user.email}</p>
          <button class="btn btn-info btn-sm me-2" onclick="loadUserForEdit(${user.id}, '${user.name}', '${user.email}')">Update</button>
          <button class="btn btn-danger btn-sm" onclick="deleteUser(${user.id})">Delete</button>
        </div>
      </div>
    `;

    container.appendChild(div);
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
  document.getElementById("editUserId").value = id;
  document.getElementById("submitBtn").style.display = "inline-block";
  document.getElementById("updateBtn").style.display = "inline-block";
}

async function updateUser() {
  const id = document.getElementById("editUserId").value;
  const name = document.getElementById("userName").value.trim();
  const email = document.getElementById("userEmail").value.trim();

  if (id && name && email) {
    await fetch("/api/users/" + id, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ name, email })
    });

    resetForm();
    fetchUsers();
  }
}

async function deleteUser(id) {
  await fetch("/api/users/" + id, { method: "DELETE" });
  fetchUsers();
}

function resetForm() {
  document.getElementById("userName").value = "";
  document.getElementById("userEmail").value = "";
  document.getElementById("editUserId").value = "";
  document.getElementById("submitBtn").style.display = "inline-block";
  document.getElementById("updateBtn").style.display = "none";
}
