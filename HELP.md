# HELP.md

## Common Issues and Solutions

### Problem: `pgadmin_container is unhealthy`
**Reason:** pgAdmin's healthcheck fails because it's checking `http://localhost:80`, but inside Docker, `localhost` is wrong.

**Solution:**
- Ignore pgAdmin container health if not critical.
- Or manually start pgAdmin with `docker compose up` separately.
- Your Spring Boot app only depends on PostgreSQL being healthy.

Alternatively, in `application.yml`, skipping pgAdmin is already configured:
```yaml
spring:
  docker:
    compose:
      skip:
        in-service:
          pgadmin_container: true
```

✅ You don't need pgAdmin for Spring Boot to run.

### Problem: Network Resource Still In Use After `docker compose down`
**Reason:** Some containers are still running.

**Solution:**
```bash
docker ps  # find running containers

docker stop <container_id>

docker network prune  # remove unused networks
```

### Problem: Application Fails to Connect to Database

- Ensure PostgreSQL is running:
```bash
docker compose ps
```
- Verify PostgreSQL is healthy before starting the app.

If needed, check logs:
```bash
docker logs postgres_container
```

---

## Additional Notes

- pgAdmin is only needed if you want a graphical interface to the database.
- Use Postman or curl to test your Spring Boot REST endpoints.

---

## Project Structure
```
├── src/main/java/com/example/demo
│   ├── config
│   ├── controller
│   ├── model
│   ├── repository
│   ├── service
│   └── DemoSpringBootDockerComposeApplication.java
├── src/main/resources
│   ├── application.yml
├── docker-compose.yml
├── servers.json (for pgAdmin)
├── pom.xml
```

