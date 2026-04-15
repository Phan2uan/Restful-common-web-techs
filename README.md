# 🎓 Student Management API

## 📌 Description

This is a backend RESTful API for managing student information.
The system supports CRUD operations, search with multiple filters, pagination, and birthday notifications.

---

## 🚀 Technologies Used

* Java
* Spring Boot
* JDBC
* Lombok
* RESTful API
* JSON

---

## 🏗️ Project Architecture

This project follows a **3-layer architecture**:

```
Controller → Service → Repository → Database
```

* **Controller**: Handle HTTP requests (API layer)
* **Service**: Business logic and validation
* **Repository**: Data access using JDBC

---

## 📂 Features

* ✅ Create student
* ✅ Update student
* ✅ Delete student
* ✅ Get all students
* ✅ Search students (multi-criteria + pagination)
* ✅ Get students with birthday today
* ✅ Input validation
* ✅ Logging system

---

## 📊 Database Schema

```sql
CREATE TABLE student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(50) NOT NULL,
    birthday DATE NOT NULL,
    class_name VARCHAR(50),
    major VARCHAR(50),
    hometown VARCHAR(100),
    gender VARCHAR(10),
    average_mark DECIMAL(4,2)
);
```

---

## 📡 API Endpoints

### 🔹 Get all students

```
GET /students
```

---

### 🔹 Create student

```
POST /students
```

**Request Body:**

```json
{
  "fullName": "Quan",
  "birthday": "2003-01-01",
  "className": "CNTT1",
  "major": "IT",
  "hometown": "HN",
  "gender": "Nam",
  "averageMark": 9.0
}
```

---

### 🔹 Update student

```
PUT /students/{id}
```

---

### 🔹 Delete student

```
DELETE /students/{id}
```

---

### 🔹 Search students

```
POST /students/search
```

**Request Body:**

```json
{
  "name": "quan",
  "gender": "Nam",
  "minMark": 7,
  "maxMark": 10,
  "page": 0,
  "size": 5
}
```

---

### 🔹 Get students with birthday today

```
GET /students/birthday
```

---

## 🧪 Testing

You can test APIs using Postman.

### Example search request:

```json
{
  "name": "quan",
  "minMark": 7,
  "maxMark": 10
}
```

---

## ⚠️ Validation Rules

* Name is required and must not exceed 50 characters
* Birthday must be valid (not in the future)
* Average mark must be between 0 and 10

---

## 📁 Project Structure

```
src/
 ├── controller/
 ├── service/
 ├── repository/
 ├── model/
 └── dto/
```

---

## 📝 Logging

The system logs:

* API calls
* Errors
* Important operations (create, update, delete)

---

## 🎯 Author

* Name: Quân Phan
* Role: Backend Developer (Java Intern)

---

## 🚀 Future Improvements

* Use Spring Data JPA / Hibernate
* Add authentication (JWT)
* Connect real database (MySQL/PostgreSQL)
* Deploy to cloud (AWS / Docker)
