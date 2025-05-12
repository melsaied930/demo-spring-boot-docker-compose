#!/bin/bash

echo "Testing access for ADMIN user..."

echo -e "\n-> Accessing /admin with admin:admin (Expect: 200 OK)"
curl -s -o /dev/null -w "%{http_code}\n" -u admin:admin "http://localhost:9090/admin"

echo -e "\n-> Accessing /user with admin:admin (Expect: 200 OK)"
curl -s -o /dev/null -w "%{http_code}\n" -u admin:admin "http://localhost:9090/user"

echo -e "\n-> Accessing /user with user:user (Expect: 200 OK)"
curl -s -o /dev/null -w "%{http_code}\n" -u user:user "http://localhost:9090/user"

echo -e "\n-> Accessing /admin with user:user (Expect: 403 Forbidden)"
curl -s -o /dev/null -w "%{http_code}\n" -u user:user "http://localhost:9090/admin"
