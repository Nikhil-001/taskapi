# Task API

A production-ready REST API for task management built with Spring Boot, JWT authentication, and Redis.

**Live URL:** https://taskapi-production-3f0b.up.railway.app

## Tech Stack
- Java 17 + Spring Boot 4
- Spring Security + JWT (jjwt)
- Spring Data JPA + H2
- Redis (token blacklisting)
- Docker + Railway

## Features
- User registration and login with JWT
- BCrypt password hashing
- Stateless authentication — no sessions
- Redis-powered logout with token blacklisting
- Full task CRUD with ownership enforcement

## API Endpoints

### Auth
| Method | Endpoint | Auth | Description |
|--------|----------|------|-------------|
| POST | /api/auth/register | No | Register new user |
| POST | /api/auth/login | No | Login, returns JWT |
| POST | /api/auth/logout | Yes | Blacklist token in Redis |

### Tasks
| Method | Endpoint | Auth | Description |
|--------|----------|------|-------------|
| POST | /api/tasks | Yes | Create task |
| GET | /api/tasks | Yes | Get all your tasks |
| GET | /api/tasks/{id} | Yes | Get one task |
| PUT | /api/tasks/{id} | Yes | Update task |
| DELETE | /api/tasks/{id} | Yes | Delete task |

## Running Locally

### With Docker
```bash
docker-compose up --build
```

### Without Docker
```bash
# Start Redis
brew services start redis

# Run the app
./mvnw spring-boot:run
```

## Example Requests

**Register**
```bash
curl -X POST https://taskapi-production-3f0b.up.railway.app/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"name":"John","email":"john@example.com","password":"secret123"}'
```

**Create Task**
```bash
curl -X POST https://taskapi-production-3f0b.up.railway.app/api/tasks \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"title":"My task","status":"TODO"}'
```

## Architecture
```
Controller → Service → Repository → H2 Database
                ↓
           JWT Filter → Redis (token blacklist)
```
