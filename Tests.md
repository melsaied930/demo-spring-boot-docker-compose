Here are full `curl` test commands for your REST endpoints, assuming the Spring Boot app is running locally at `http://localhost:8080` and the API accepts and returns JSON.

---

## ✅ `RoleController` — `/roles`

### 🔍 Get all roles
```bash
curl -X GET http://localhost:8080/roles
```

### 🔍 Get a role by ID
```bash
curl -X GET http://localhost:8080/roles/<ROLE_UUID>
```

### 🆕 Create a new role
```bash
curl -X POST http://localhost:8080/roles \
  -H "Content-Type: application/json" \
  -d '{"name": "ADMIN"}'
```

### ✏️ Update an existing role
```bash
curl -X PUT http://localhost:8080/roles/<ROLE_UUID> \
  -H "Content-Type: application/json" \
  -d '{"name": "MODERATOR"}'
```

### ❌ Delete a role
```bash
curl -X DELETE http://localhost:8080/roles/<ROLE_UUID>
```

---

## ✅ `UserController` — `/users`

### 🔍 Get all users
```bash
curl -X GET http://localhost:8080/users
```

### 🔍 Get a user by ID
```bash
curl -X GET http://localhost:8080/users/<USER_UUID>
```

### 🆕 Create a new user
```bash
curl -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{
    "username": "johndoe",
    "password": "secret",
    "roles": []
}'
```



### ✏️ Update an existing user
```bash
curl -X PUT http://localhost:8080/users/<USER_UUID> \
  -H "Content-Type: application/json" \
  -d '{
    "username": "johnsmith",
    "password": "newpassword",
    "roles": []
}'
```

### ❌ Delete a user
```bash
curl -X DELETE http://localhost:8080/users/<USER_UUID>
```

---

Let me know if you want to auto-generate UUIDs for test data or run everything in a script 🚀