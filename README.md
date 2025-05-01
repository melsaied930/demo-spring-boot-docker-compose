# README.md

# Demo Spring Boot Docker Compose Application

This project is a simple **Spring Boot** application that connects to a **PostgreSQL** database and manages users and
roles. It uses **Docker Compose** to orchestrate the services.

---

## Features

- Spring Boot 3.4.4 with Java 21
- PostgreSQL 16 database
- pgAdmin 4 for database management (optional)
- Docker Compose integration
- Basic User and Role management via JPA

---

## Requirements

- Java 21
- Maven 3.8+
- Docker
- Docker Compose

---

## How to Run

1. **Clone the repository**

```bash
  git clone <repository-url>
  cd demo-spring-boot-docker-compose
```

2. **Start Docker containers**

```bash
  docker compose up -d
```

2. **Show Docker logs**

```bash
  docker compose logs -f
```

3. **Run the Spring Boot Application**

```bash
  ./mvnw spring-boot:run
```

4. **Access the application**

- Application: [http://localhost:8080](http://localhost:8080)
- pgAdmin: [http://localhost:5050](http://localhost:5050)
    - **Email:** `admin@demo.com`
    - **Password:** `admin`

5. **Stop the containers**

```bash
  docker compose down -v
```

---

## Environment Variables

Configured in `docker-compose.yml`:

- PostgreSQL:
    - `POSTGRES_DB=demo-db`
    - `POSTGRES_USER=postgres`
    - `POSTGRES_PASSWORD=postgres`
- pgAdmin:
    - `PGADMIN_DEFAULT_EMAIL=admin@demo.com`
    - `PGADMIN_DEFAULT_PASSWORD=admin`

---

## Useful Maven Commands

- Build project:

```bash
  ./mvnw clean package
```

- Run tests:

```bash
  ./mvnw test
```
