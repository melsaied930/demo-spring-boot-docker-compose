Here are the full `curl` test commands for all the endpoints defined in your `RoleController` and `UserController`. These are meant to be run against your local Spring Boot backend at `http://localhost:8080`.

---

### 🟦 **RoleController `/roles`**

#### 🔹 Get All Roles
```bash
curl -X GET http://localhost:8080/roles
```

#### 🔹 Get Role by ID
```bash
curl -X GET http://localhost:8080/roles/1
```

#### 🔹 Create Role
```bash
curl -X POST http://localhost:8080/roles \
  -H "Content-Type: application/json" \
  -d '{"name": "ADMIN"}'
```

#### 🔹 Update Role
```bash
curl -X PUT http://localhost:8080/roles/1 \
  -H "Content-Type: application/json" \
  -d '{"id": 1, "name": "SUPER_ADMIN"}'
```

#### 🔹 Delete Role
```bash
curl -X DELETE http://localhost:8080/roles/1
```

---

### 🟩 **UserController `/users`**

#### 🔹 Get All Users
```bash
curl -X GET http://localhost:8080/users
```

#### 🔹 Get User by ID
```bash
curl -X GET http://localhost:8080/users/1
```

#### 🔹 Create User
```bash
curl -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{"username": "john_doe", "password": "secure123"}'
```

#### 🔹 Update User
```bash
curl -X PUT http://localhost:8080/users/1 \
  -H "Content-Type: application/json" \
  -d '{"id": 1, "username": "john_updated", "password": "newpassword"}'
```

#### 🔹 Delete User
```bash
curl -X DELETE http://localhost:8080/users/1
```

---

Let me know if you want to include the many-to-many relation (e.g., assigning roles to users), or run these from a script, or add UUID support instead of `Long`.