#!/bin/bash

BASE_URL="http://localhost:9090"

echo "Testing access for ADMIN user..."

echo -e "\n-> Accessing /admin with admin:admin (Expect: 200 OK)"
curl -s -o /dev/null -w "%{http_code}\n" -u admin:admin "$BASE_URL/admin"

echo -e "\n-> Accessing /user with admin:admin (Expect: 200 OK)"
curl -s -o /dev/null -w "%{http_code}\n" -u admin:admin "$BASE_URL/user"

echo -e "\n-> Accessing /user with user:user (Expect: 200 OK)"
curl -s -o /dev/null -w "%{http_code}\n" -u user:user "$BASE_URL/user"

echo -e "\n-> Accessing /admin with user:user (Expect: 403 Forbidden)"
curl -s -o /dev/null -w "%{http_code}\n" -u user:user "$BASE_URL/admin"
