### CuraHub Backend API — Current Endpoints

## Patient API Endpoints

### Users (`/api/users`)
- GET `/api/users` — List users
```http
GET /api/users
```

- GET `/api/users/{id}` — Get user by ID
```http
GET /api/users/1
```

- POST `/api/users` — Create user
```http
POST /api/users
Content-Type: application/json

{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john@example.com",
  "phone": "555-555-5555"
}
```

- PUT `/api/users/{id}` — Update user
```http
PUT /api/users/1
Content-Type: application/json

{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.updated@example.com",
  "phone": "555-555-1234"
}
```

- DELETE `/api/users/{id}` — Delete user
```http
DELETE /api/users/1
```

## Doctor API Endpoints

### Doctors (`/api/doctors`)
- GET `/api/doctors` — List doctors
```http
GET /api/doctors
```

- GET `/api/doctors/{id}` — Get doctor by ID
```http
GET /api/doctors/1
```

- GET `/api/doctors/search?name={name}` — Search doctors by name
```http
GET /api/doctors/search?name=Smith
```

- POST `/api/doctors` — Create doctor
```http
POST /api/doctors
Content-Type: application/json

{
  "user": { "id": 1 },
  "description": "Board-certified cardiologist"
}
```

- DELETE `/api/doctors/{id}` — Delete doctor
```http
DELETE /api/doctors/1
```

### Availability (`/api/availability`)
- GET `/api/availability` — List availability
```http
GET /api/availability
```

- GET `/api/availability/{id}` — Get availability by ID
```http
GET /api/availability/1
```

- GET `/api/availability/doctor/{id}` — List availability by doctor ID
```http
GET /api/availability/doctor/1
```

- POST `/api/availability` — Create availability
```http
POST /api/availability
Content-Type: application/json

{
  "doctor": { "id": 1 },
  "startTime": "2025-10-28T09:00:00",
  "endTime": "2025-10-28T12:00:00",
  "length": "00:30:00"
}
```

- DELETE `/api/availability/{id}` — Delete availability
```http
DELETE /api/availability/1
```

