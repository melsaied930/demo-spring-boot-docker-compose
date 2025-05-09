#!/bin/bash

echo "Getting token for tenant-1-user-admin"
curl -s -X POST http://localhost:8080/realms/tenant-1/protocol/openid-connect/token \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -u tenant-1-client-app-1:shared-secret \
  -d "grant_type=password" \
  -d "username=tenant-1-user-admin" \
  -d "password=admin" | jq
echo -e "\n"

echo "Getting token for tenant-1-user-user"
curl -s -X POST http://localhost:8080/realms/tenant-1/protocol/openid-connect/token \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -u tenant-1-client-app-2:shared-secret \
  -d "grant_type=password" \
  -d "username=tenant-1-user-user" \
  -d "password=user" | jq
echo -e "\n"

echo "Getting token for tenant-2-user-admin"
curl -s -X POST http://localhost:8080/realms/tenant-2/protocol/openid-connect/token \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -u tenant-2-client-app-1:shared-secret \
  -d "grant_type=password" \
  -d "username=tenant-2-user-admin" \
  -d "password=admin" | jq
echo -e "\n"

echo "Getting token for tenant-2-user-user"
curl -s -X POST http://localhost:8080/realms/tenant-2/protocol/openid-connect/token \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -u tenant-2-client-app-2:shared-secret \
  -d "grant_type=password" \
  -d "username=tenant-2-user-user" \
  -d "password=user" | jq
echo -e "\n"
