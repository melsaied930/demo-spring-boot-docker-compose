#!/bin/bash

echo "=== Create a new User ==="
curl -i -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{"username":"newuser","password":"newpassword"}'
echo -e "\n"

echo "=== Get User by ID ==="
curl -i -X GET http://localhost:8080/users/3 \
  -H "Accept: application/json"
echo -e "\n"

echo "=== Get All Users ==="
curl -i -X GET http://localhost:8080/users \
  -H "Accept: application/json"
echo -e "\n"

echo "=== Update Existing User ==="
curl -i -X PUT http://localhost:8080/users/3 \
  -H "Content-Type: application/json" \
  -d '{"username":"updateduser","password":"updatedpassword"}'
echo -e "\n"

echo "=== Delete User ==="
curl -i -X DELETE http://localhost:8080/users/1
echo -e "\n"
