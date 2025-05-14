#!/bin/bash

curl -H "Authorization: Bearer eyJhbGc...." http://localhost:9090/user

curl -X GET http://localhost:8080/realms/tenant-1

curl -X POST http://localhost:8080/realms/tenant-1/protocol/openid-connect/token \
-H "Content-Type: application/x-www-form-urlencoded" \
-d "grant_type=password" \
-d "username=tenant-1-user-user" \
-d "password=user" \
-d "client_id=tenant-1-client-app-1" \
-d "client_secret=your-client-secret" | jq -r ".access_token"

echo '<your-token>' | cut -d '.' -f2 | base64 -d

curl --location 'http://localhost:9090/'

curl -s -H "Authorization: Bearer $(curl -s \
-X POST "http://localhost:8080/realms/tenant-1/protocol/openid-connect/token" \
-H "Content-Type: application/x-www-form-urlencoded" \
-u "tenant-1-client-app-1:shared-secret" \
-d "grant_type=password" \
-d "username=tenant-1-user-user" \
-d "password=user" | jq -r '.access_token')" \
http://localhost:9090/user

curl -s -H "Authorization: Bearer $(curl -s \
-X POST "http://localhost:8080/realms/tenant-1/protocol/openid-connect/token" \
-H "Content-Type: application/x-www-form-urlencoded" \
-u "tenant-1-client-app-1:shared-secret" \
-d "grant_type=password" \
-d "username=tenant-1-user-admin" \
-d "password=admin" | jq -r '.access_token')" \
http://localhost:9090/admin

curl -s -H "Authorization: Bearer $(curl -s \
-X POST "http://localhost:8080/realms/tenant-1/protocol/openid-connect/token" \
-H "Content-Type: application/x-www-form-urlencoded" \
-u "tenant-1-client-app-1:shared-secret" \
-d "grant_type=password" \
-d "username=tenant-1-user-admin" \
-d "password=admin" | jq -r '.access_token')" \
http://localhost:9090/user

curl -s -H "Authorization: Bearer $(curl -s \
-X POST "http://localhost:8080/realms/tenant-1/protocol/openid-connect/token" \
-H "Content-Type: application/x-www-form-urlencoded" \
-u "tenant-1-client-app-1:shared-secret" \
-d "grant_type=password" \
-d "username=tenant-1-user-user" \
-d "password=user" | jq -r '.access_token')" \
http://localhost:9090/admin
