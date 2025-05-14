# Demo Spring Boot with Docker Compose

This project demonstrates a Spring Boot application integrated with PostgreSQL, pgAdmin, and Keycloak for authentication and authorization, all orchestrated using Docker Compose.

---

## 📦 Project Structure

- **Spring Boot App**: Backend service built using Java 21 and Spring Boot 3.4.4.
- **PostgreSQL**: Used as the main relational database.
- **pgAdmin**: GUI for managing the PostgreSQL instance.
- **Keycloak**: Identity and access management with multi-tenant realm setup.

---

## 🚀 Getting Started

### Prerequisites

- Java 21
- Docker & Docker Compose
- Maven 3.8+

---

## 🐳 Docker Setup

Run the full infrastructure with:

```bash
docker compose up --build
````

This will spin up:

* `postgres_container`: PostgreSQL 16
* `pgadmin_container`: pgAdmin 4 (login: `admin@demo.com` / `admin`)
* `keycloak`: Identity provider for handling OAuth2 flows

If you're facing issues with pgAdmin health checks, refer to the [Help section](#️-help--troubleshooting).

---

## ⚙️ Spring Boot Configuration

### Maven Dependencies (`pom.xml`)

Key dependencies include:

* `spring-boot-starter-web`
* `spring-boot-starter-data-jpa`
* `spring-boot-starter-security`
* `spring-boot-starter-oauth2-resource-server`
* PostgreSQL JDBC Driver

---

## 🔐 Keycloak Configuration

Keycloak is preconfigured using a custom `realm.json`:

```json
[
  {
    "realm": "tenant-1",
    "clients": [
      {
        "clientId": "tenant-1-client-app-1",
        "secret": "shared-secret"
      },
      {
        "clientId": "tenant-1-client-app-2",
        "secret": "shared-secret"
      }
    ],
    "users": [
      {
        "username": "tenant-1-user-admin",
        "credentials": [{"value": "admin"}],
        "realmRoles": ["tenant-1-role-admin"]
      },
      {
        "username": "tenant-1-user-user",
        "credentials": [{"value": "user"}],
        "realmRoles": ["tenant-1-role-user"]
      }
    ]
  }
]
```

### Import Realms (if not automated)

```bash
docker exec -it <keycloak_container_id> /opt/keycloak/bin/kc.sh import --file /opt/keycloak/data/import/realm.json
```

---

## 📊 pgAdmin Configuration

Preconfigured with `servers.json`:

```json
{
  "Servers": {
    "1": {
      "Name": "Keycloak DB",
      "Host": "postgres",
      "Port": 5432,
      "MaintenanceDB": "demo-db",
      "Username": "postgres"
    }
  }
}
```

Access pgAdmin at [http://localhost:5050](http://localhost:5050)

---

## 🧪 API Authentication

Use OAuth2 with password grant:

```bash
curl -X POST http://localhost:8080/realms/tenant-1/protocol/openid-connect/token \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -u tenant-1-client-app-1:shared-secret \
  -d "grant_type=password" \
  -d "username=tenant-1-user-user" \
  -d "password=user"
```

---

## 🛠️ Help & Troubleshooting

See [`HELP.md`](./HELP.md) for common issues like:

* Unhealthy pgAdmin container
* PostgreSQL connection failures
* Docker network conflicts

---

## 📜 License

This project is for educational and demo purposes. MIT-style license.

---

## 🙌 Acknowledgments

* [Spring Boot](https://spring.io/projects/spring-boot)
* [Keycloak](https://www.keycloak.org/)
* [PostgreSQL](https://www.postgresql.org/)
* [pgAdmin](https://www.pgadmin.org/)

```

---

Would you like me to save this `README.md` into your project directory or adjust it for any specific deployment or usage scenario (e.g., CI/CD, GitHub Actions, etc.)?
```
