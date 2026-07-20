# Distributed Lock Service

A distributed locking service built with Spring Boot, Redis, and PostgreSQL.

## Tech Stack

- Java 21
- Spring Boot 3
- PostgreSQL
- Redis
- Docker
- Flyway
- Maven

---

## Prerequisites

- Java 21
- Maven
- Docker Desktop

---

## Clone the Repository

```bash
git clone "https://github.com/YATHAGROVER/DistributedLockService.git"
cd distributed-lock-service
```

---

## Configure Environment Variables

Create a `.env` file in the project root.

Example:

```env
DB_HOST=localhost
DB_PORT=5432
DB_NAME=lockdb
DB_USERNAME=postgres
DB_PASSWORD=postgres

REDIS_HOST=localhost
REDIS_PORT=6379

SERVER_PORT=8080

DEFAULT_LEASE_TIME=30
```

You can copy `.env.example` and update the values.

---

## Start PostgreSQL and Redis

```bash
docker compose up -d
```

---

## Build the Project

```bash
mvn clean install
```

---

## Run the Application

```bash
mvn spring-boot:run
```

The application starts on:

```
http://localhost:8080
```

---

