#!/bin/bash

echo "=== Create a new Role ==="
curl -i -X POST http://localhost:9090/roles \
  -H "Content-Type: application/json" \
  -d '{"name":"Admin User"}'
echo -e "\n"

echo "=== Get Role by ID ==="
curl -i -X GET http://localhost:9090/roles/3 \
  -H "Accept: application/json"
echo -e "\n"

echo "=== Get All Roles ==="
curl -i -X GET http://localhost:9090/roles \
  -H "Accept: application/json"
echo -e "\n"

echo "=== Update Existing Role ==="
curl -i -X PUT http://localhost:9090/roles/3 \
  -H "Content-Type: application/json" \
  -d '{"name":"Updated Admin User"}'
echo -e "\n"

echo "=== Delete Role ==="
curl -i -X DELETE http://localhost:9090/roles/3
echo -e "\n"
