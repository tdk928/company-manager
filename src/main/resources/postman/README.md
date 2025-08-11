# Postman Collection for Company Manager API

This folder contains a Postman collection for testing the Company Manager API endpoints.

## 📁 Files

- **`Company_Manager_API.postman_collection.json`** - Complete Postman collection with all API endpoints

## 🚀 How to Import

1. **Open Postman**
2. **Click "Import"** button
3. **Select the JSON file**: `Company_Manager_API.postman_collection.json`
4. **The collection will appear** in your Postman workspace

## 🔧 Environment Setup

The collection uses a variable `{{baseUrl}}` which is set to `http://localhost:8080` by default.

### To change the base URL:
1. **Click on the collection** (Company Manager API)
2. **Go to "Variables" tab**
3. **Update the `baseUrl` value** to your server URL
4. **Save the collection**

## 📋 Available Endpoints

### **User Management**

#### **Create User** `POST /api/admin/users`
- **Valid Request Body Example:**
```json
{
    "firstName": "John",
    "secondName": "Michael",
    "lastName": "Doe",
    "egn": "1234567890"
}
```
- **Response includes role**: All newly created users automatically get the "admin" role

#### **Get All Users** `GET /api/admin/users`
- No request body needed

#### **Get User by ID** `GET /api/admin/users/{id}`
- Replace `{id}` with actual user ID (e.g., `1`)

#### **Get User by EGN** `GET /api/admin/users/egn/{egn}`
- Replace `{egn}` with actual EGN (e.g., `1234567890`)

#### **Update User** `PUT /api/admin/users/{id}`
- **Request Body Example:**
```json
{
    "firstName": "Jane",
    "secondName": "Elizabeth",
    "lastName": "Smith",
    "egn": "0987654321"
}
```

#### **Delete User** `DELETE /api/admin/users/{id}`
- Replace `{id}` with actual user ID

#### **Search Users by First Name** `GET /api/admin/users/search/firstname?firstName=John`
- Query parameter: `firstName`

#### **Search Users by Last Name** `GET /api/admin/users/search/lastname?lastName=Doe`
- Query parameter: `lastName`

### **Role Management**

#### **Get All Roles** `GET /api/admin/roles`
- No request body needed
- Returns all available system roles

#### **Get Role by ID** `GET /api/admin/roles/{id}`
- Replace `{id}` with actual role ID (e.g., `1`)

#### **Get Role by Name** `GET /api/admin/roles/name/{name}`
- Replace `{name}` with role name: `admin`, `company`, or `anonymous`

### **Company Management**

#### **Create Company** `POST /api/companies`
- **Valid Request Body Example:**
```json
{
    "name": "TechCorp Solutions Ltd",
    "eik": "123456789",
    "address": "123 Tech Street, Sofia, Bulgaria"
}
```
- **Response includes**: All company details with automatically set `validFrom` date

#### **Get All Companies** `GET /api/companies`
- No request body needed

#### **Get Company by ID** `GET /api/companies/{id}`
- Replace `{id}` with actual company ID (e.g., `1`)

#### **Get Company by EIK** `GET /api/companies/eik/{eik}`
- Replace `{eik}` with actual EIK (e.g., `123456789`)

#### **Update Company** `PUT /api/companies/{id}`
- **Request Body Example:**
```json
{
    "name": "Updated TechCorp Solutions Ltd",
    "eik": "123456789",
    "address": "456 Updated Street, Sofia, Bulgaria"
}
```

#### **Delete Company** `DELETE /api/companies/{id}`
- Replace `{id}` with actual company ID

#### **Search Companies by Name** `GET /api/companies/search/name?name=TechCorp`
- Query parameter: `name`

#### **Search Companies by Address** `GET /api/companies/search/address?address=Sofia`
- Query parameter: `address`

## ✅ Testing Validations

### **Valid User Data:**
- `firstName`: Required, max 30 characters
- `secondName`: Optional, max 30 characters
- `lastName`: Required, max 30 characters
- `egn`: Required, exactly 10 numeric digits

### **Valid Company Data:**
- `name`: Required, max 50 characters
- `eik`: Required, exactly 9 numeric digits (Bulgarian company identifier)
- `address`: Required, max 50 characters

### **Test Invalid Data:**
Try these to test validation errors:

#### **Invalid First Name:**
```json
{
    "firstName": "ThisIsAVeryLongFirstNameThatExceedsThirtyCharacters",
    "lastName": "Doe",
    "egn": "1234567890"
}
```

#### **Invalid Company Data:**
```json
{
    "name": "ThisIsAVeryLongCompanyNameThatExceedsFiftyCharactersAndShouldFailValidation",
    "eik": "12345",
    "address": "ThisIsAVeryLongCompanyAddressThatExceedsFiftyCharactersAndShouldFailValidation"
}
```
**Expected Error:** ERR001 - "First name cannot exceed 30 characters"

#### **Missing First Name:**
```json
{
    "lastName": "Doe",
    "egn": "1234567890"
}
```
**Expected Error:** ERR002 - "First name is required"

#### **Invalid EGN:**
```json
{
    "firstName": "John",
    "lastName": "Doe",
    "egn": "12345"
}
```
**Expected Error:** ERR007 - "EGN must be exactly 10 digits"

#### **Non-numeric EGN:**
```json
{
    "firstName": "John",
    "lastName": "Doe",
    "egn": "123456789a"
}
```
**Expected Error:** ERR008 - "EGN must contain only numeric characters"

## 🔍 Expected Responses

### **Success Responses:**
- **201 Created** for successful user creation
- **200 OK** for successful operations
- **204 No Content** for successful deletion

### **Error Responses:**
All errors follow this structure:
```json
{
    "timestamp": "2024-01-15T10:30:00",
    "status": 400,
    "error": "Bad Request",
    "errorCode": "ERR001",
    "message": "First name cannot exceed 30 characters"
}
```

## 🚀 Quick Start

1. **Start your Spring Boot application**
2. **Import the Postman collection**
3. **Set the base URL** (if different from localhost:8080)
4. **Start with "Create User"** to add a test user
5. **Use "Get All Users"** to verify the user was created
6. **Test other endpoints** as needed

## 📝 Notes

- **EGN must be unique** - you cannot create two users with the same EGN
- **Validation happens** at the entity level before database operations
- **All endpoints return** consistent error response format
- **Search endpoints** are case-insensitive and support partial matches

## 🏷️ Predefined Roles

The system comes with three predefined roles:

1. **admin** - Administrator with full system access
2. **company** - Company user with limited access  
3. **anonymous** - Anonymous user with minimal access

These roles are automatically created when you run the `create_roles_table.sql` script.

**Note**: All users are automatically assigned the "admin" role when created. The role is stored directly in the users table via a `role_id` foreign key.
