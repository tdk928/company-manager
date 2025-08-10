# Company Manager API Endpoints

## Base URL
When running locally: `http://localhost:8080`
When deployed: `http://your-domain:8080`

## Available Endpoints

### 1. Get All Companies
**GET** `/api/companies`

**Description:** Retrieves all companies from the database

**URL:** `http://localhost:8080/api/companies`

**Response:** List of all companies
```json
[
  {
    "id": 1,
    "name": "TechCorp Solutions"
  },
  {
    "id": 2,
    "name": "Global Innovations Ltd"
  },
  {
    "id": 3,
    "name": "Digital Dynamics Inc"
  },
  {
    "id": 4,
    "name": "Future Systems Co"
  },
  {
    "id": 5,
    "name": "Smart Technologies Group"
  }
]
```

### 2. Get Company by ID
**GET** `/api/companies/{id}`

**Description:** Retrieves a specific company by its ID

**URL:** `http://localhost:8080/api/companies/1`

**Response:** Company object
```json
{
  "id": 1,
  "name": "TechCorp Solutions"
}
```

### 3. Create New Company
**POST** `/api/companies`

**Description:** Creates a new company

**URL:** `http://localhost:8080/api/companies`

**Request Body:**
```json
{
  "name": "New Company Name"
}
```

**Response:** Created company with generated ID
```json
{
  "id": 6,
  "name": "New Company Name"
}
```

### 4. Update Company
**PUT** `/api/companies/{id}`

**Description:** Updates an existing company

**URL:** `http://localhost:8080/api/companies/1`

**Request Body:**
```json
{
  "name": "Updated Company Name"
}
```

**Response:** Updated company
```json
{
  "id": 1,
  "name": "Updated Company Name"
}
```

### 5. Delete Company
**DELETE** `/api/companies/{id}`

**Description:** Deletes a company by ID

**URL:** `http://localhost:8080/api/companies/1`

**Response:** HTTP 200 OK (no content)

## Testing the API

### Using cURL

**Get all companies:**
```bash
curl -X GET http://localhost:8080/api/companies
```

**Get company by ID:**
```bash
curl -X GET http://localhost:8080/api/companies/1
```

**Create new company:**
```bash
curl -X POST http://localhost:8080/api/companies \
  -H "Content-Type: application/json" \
  -d '{"name": "Test Company"}'
```

**Update company:**
```bash
curl -X PUT http://localhost:8080/api/companies/1 \
  -H "Content-Type: application/json" \
  -d '{"name": "Updated Company"}'
```

**Delete company:**
```bash
curl -X DELETE http://localhost:8080/api/companies/1
```

### Using Postman

1. **Import the collection** or create new requests
2. **Set base URL:** `http://localhost:8080`
3. **Use the endpoints above** with appropriate HTTP methods

### Using Browser

**Get all companies:** Simply navigate to `http://localhost:8080/api/companies`

## Running the Application

### Local Development
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=railway
```

### Verify Database Connection
Before testing, ensure your Railway PostgreSQL database is connected and contains the test data from `insert_test_companies.sql`.

## Expected Behavior

1. **Start the application** with Railway profile
2. **Navigate to** `http://localhost:8080/api/companies`
3. **See all 5 test companies** returned by `getAllCompanies()` method
4. **Test other endpoints** as needed

## Troubleshooting

- **Port 8080 already in use:** Change port in `application-railway.yml`
- **Database connection failed:** Check Railway credentials and network
- **No companies returned:** Ensure test data was inserted into the database
