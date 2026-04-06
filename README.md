# Product Management System

A backend Product Management System built using Spring Boot, designed to handle core e-commerce functionalities such as product management, cart operations, order processing, and user management.

The application follows a layered architecture with Controllers, Services, and Repositories, along with proper exception handling and security configuration.

---

## Features

* User registration and management
* Role-based authentication and authorization using Spring Security
* Product management (CRUD operations)
* Category management
* Cart and cart item handling
* Address management
* Order placement and tracking
* Inventory management
* Global exception handling

---

## Tech Stack

* Java 17
* Spring Boot
* Spring Security
* Spring Data JPA (Hibernate)
* Maven
* MySQL / Oracle (configurable)
* REST APIs

---

## Project Structure

```
com.jorce.ProductManagement
│
├── config              # Security configuration
├── controller          # REST controllers
├── entity              # JPA entities
├── repository          # Data access layer
├── service             # Business logic
├── exception           # Custom exceptions and handlers
└── ProductManagementApplication.java
```

---

## Security

* Configured using Spring Security
* Role-based access control (Admin/User)
* Password encryption using BCrypt
* Custom UserDetailsService implementation

## API Endpoints (Sample)

### User

* POST /users/register
* GET /users/{id}

### Product

* POST /products
* GET /products
* PUT /products/{id}
* DELETE /products/{id}

### Cart

* POST /cart/add
* GET /cart/{userId}

### Orders

* POST /orders/place
* GET /orders/{id}

---

## Exception Handling

Custom exceptions are implemented for:

* Product not found
* User not found
* Cart-related issues
* Inventory shortages

These are handled globally using @ControllerAdvice.

---

## Key Highlights

* Clean layered architecture (Controller → Service → Repository)
* Proper separation of concerns
* Scalable design suitable for real-world applications
* Easy to extend with additional features

---

This project is open-source and available under the MIT License.
