# ABS Commerce API — E-Commerce Platform API

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-green)
![Status](https://img.shields.io/badge/status-in%20development-yellow)
![License](https://img.shields.io/badge/license-MIT-blue)

> Modern e-commerce backend API built with Java and Spring Boot, focused on clean architecture, scalability, and real-world business rules.

---

## 🚀 About the Project

**ABS Commerce** is a backend application that simulates a modern e-commerce platform.

The project was created as part of the **ABS — Application Backend Solutions** portfolio, with the goal of applying enterprise-level backend development practices using Java and Spring Boot.

Current focus:

- RESTful APIs
- Domain-driven modeling
- Database relationships
- Order processing
- Payment workflow
- Layered architecture
- Scalability concepts
- Clean Code principles

Although it started as a learning project, ABS Commerce is continuously evolving into a production-inspired backend application.

---

# 🧠 Domain Overview

The system is based on a real-world e-commerce architecture.

### Main Entities

- User
- Product
- Category
- Order
- OrderItem
- Payment

### Business Rules

- A user can have multiple orders.
- An order can contain multiple products.
- Products can belong to multiple categories.
- Orders may contain payment information.
- Orders have lifecycle status control:

  - WAITING_PAYMENT
  - PAID
  - SHIPPED
  - DELIVERED
  - CANCELED

---

# 🛠️ Technologies

## Backend

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- Maven

## Database

- H2 Database
- PostgreSQL *(planned)*

---

# 📐 Architecture

The project follows a layered architecture:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

Design principles:

- Separation of responsibilities
- Maintainability
- Scalability
- Clean Code
- SOLID principles

---

# 📦 Features

## Current Features

- User management
- Product management
- Category management
- Order management
- Payment relationship
- Order status management
- RESTful API endpoints
- JPA entity relationships

---

# 🔮 Roadmap

Planned improvements include:

- JWT Authentication & Authorization
- Swagger / OpenAPI documentation
- Validation handling
- Global exception handling
- Docker & Docker Compose
- PostgreSQL integration
- Unit and integration tests
- CI/CD with GitHub Actions
- Cloud deployment
- Pagination & filtering

---

# 📚 Learning Goals

This project was developed to strengthen practical knowledge in:

- Object-Oriented Programming
- Spring Boot
- RESTful API development
- Relational database modeling
- Enterprise backend architecture
- Clean Architecture
- Software design principles

---

# ▶️ Running the Project

## Requirements

- Java 17+
- Maven
- IntelliJ IDEA (recommended)

## Clone the repository

```bash
git clone https://github.com/absjrdev/abs-commerce.git
```

## Run the application

```bash
./mvnw spring-boot:run
```

---

# 📡 API Endpoints

Examples:

```http
GET    /products
GET    /orders
GET    /users
POST   /orders
```

Complete API documentation will be available through Swagger.

---

# 🧪 Project Status

🚧 **In Development**

ABS Commerce is continuously evolving as new features, architectural improvements, and infrastructure components are implemented.

---

# 👨‍💻 Author

Developed by **Arnaldo Borges dos Santos Junior**

---

## About ABS

**ABS — Application Backend Solutions**

A personal portfolio focused on building modern backend applications with Java, Spring Boot, and enterprise software engineering practices.

---

# 📄 License

This project is licensed under the MIT License and is available for learning and educational purposes.
