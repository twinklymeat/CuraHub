### CuraHub Backend API — Sample Documentation (Draft)

This is a starter API guide for the CuraHub project. It uses placeholders until real values are finalized.

---

### Base Information
- **Base URL**: `<BASE_URL>` (e.g., `http://localhost:8080` in development)
- **API Version**: `v1`
- **Global Prefix**: `/api`

Example full path: `<BASE_URL>/api/v1/...`

---

### Resources and Endpoints (Draft)

#### Users
- **POST** `/api/v1/auth/signup` — Create a new user (patient/provider)
  - Body:
  ```json
  {
    "firstName": "<STRING>",
    "lastName": "<STRING>",
    "email": "<EMAIL>",
    "password": "<PASSWORD>",
    "role": "<PATIENT|DOCTOR|ADMIN>"
  }
  ```

- **POST** `/api/v1/auth/login` — Authenticate and get token
  - Body:
  ```json
  { "email": "<EMAIL>", "password": "<PASSWORD>" }
  ```
  - Response:
  ```json
  { "token": "<JWT_TOKEN>", "expiresIn": 3600 }
  ```

- **GET** `/api/v1/users/{id}` — Get user by ID [Auth required]
- **GET** `/api/v1/users/me` — Get current user profile [Auth required]
- **PUT** `/api/v1/users/me` — Update current user profile [Auth required]

Example:
```bash
curl -H "Authorization: Bearer <JWT_TOKEN>" \
  <BASE_URL>/api/v1/users/me
```

#### Doctors
- **GET** `/api/v1/doctors` — List doctors (optional filters: `specialty`, `name`)
- **GET** `/api/v1/doctors/{id}` — Get doctor by ID
- **POST** `/api/v1/doctors` — Create doctor [Admin/Doctor]
  - Body:
  ```json
  {
    "firstName": "<STRING>",
    "lastName": "<STRING>",
    "email": "<EMAIL>",
    "specialty": "<STRING>",
    "bio": "<STRING>"
  }
  ```
- **PUT** `/api/v1/doctors/{id}` — Update doctor [Admin/Doctor]
- **DELETE** `/api/v1/doctors/{id}` — Delete doctor [Admin]

#### Availability
- **GET** `/api/v1/availability?doctorId=<ID>&from=<ISO>&to=<ISO>` — List availability slots
- **POST** `/api/v1/availability` — Create slot [Doctor]
  - Body:
  ```json
  {
    "doctorId": "<UUID>",
    "start": "<ISO_DATETIME>",
    "end": "<ISO_DATETIME>",
    "location": "<STRING>"
  }
  ```
- **DELETE** `/api/v1/availability/{id}` — Remove slot [Doctor/Admin]

#### Appointments
- **GET** `/api/v1/appointments?patientId=<ID>&doctorId=<ID>&from=<ISO>&to=<ISO>` — List appointments
- **POST** `/api/v1/appointments` — Book appointment [Patient]
  - Body:
  ```json
  {
    "patientId": "<UUID>",
    "doctorId": "<UUID>",
    "start": "<ISO_DATETIME>",
    "end": "<ISO_DATETIME>",
    "reason": "<STRING>"
  }
  ```
- **GET** `/api/v1/appointments/{id}` — Appointment details
- **PUT** `/api/v1/appointments/{id}/cancel` — Cancel appointment [Patient/Doctor]

#### Reviews
- **GET** `/api/v1/reviews?doctorId=<ID>` — List reviews for a doctor
- **POST** `/api/v1/reviews` — Create review [Patient]
  - Body:
  ```json
  {
    "doctorId": "<UUID>",
    "patientId": "<UUID>",
    "rating": 5,
    "comment": "<STRING>"
  }
  ```

---

### Request/Response Examples

- Create Appointment
```bash
curl -X POST <BASE_URL>/api/v1/appointments \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <JWT_TOKEN>" \
  -d '{
    "patientId": "<UUID>",
    "doctorId": "<UUID>",
    "start": "2025-10-28T09:00:00Z",
    "end": "2025-10-28T09:30:00Z",
    "reason": "Follow-up"
  }'
```

- Fetch Doctor Availability
```bash
curl "<BASE_URL>/api/v1/availability?doctorId=<UUID>&from=2025-10-27T00:00:00Z&to=2025-11-03T00:00:00Z"
```

---

### Notes
- This is a draft. Replace placeholders with real paths, fields, and auth details as controllers/services are finalized in the Spring Boot app.
- Coordinate response schemas with the data models in the codebase (`User`, `Doctor`, `Availability`, `Appointments`, `Reviews`).
