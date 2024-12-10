# FinalProject - Vehicle Registration API

A RESTful API developed with **Spring Boot** for user management and vehicle registrations. This project includes authentication, user roles, and persistence on an in-memory **H2** database. Ideal for learning or developing similar systems in development environments.

## Table of Contents
- [Description](#description)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Use](#use)
- [Documentation with Swagger](#documentation-with-swagger)
- [Contribute](#contribute)
- [License](#license)

## Description

The **Vehicle Registration API** project allows you to manage users with roles and vehicle registrations using CRUD operations. Includes JWT token-based authentication, data validations, custom exception handling, and persistence in a MySQL database built with Docker.

## Features

- Authentication and authorization with **Spring Security** and **JWT**.
- RESTful API to manage users and vehicles.
- **H2** in-memory database for simplicity in development.
- Interactive API documentation with **Swagger UI**.
- Custom validations and centralized exception handling.
- Modular structure to facilitate scalability.

## Prerequisites

Make sure you have the following installed on your machine:
- **Java 17** or higher
- **Maven** (optional if you use IDEs like IntelliJ)
- An API client like Postman or cURL to test the endpoints

## Installation

1. Clone this repository to your local machine:
   ```bash
   git clone https://github.com/yourusername/vehicleregistration.git
   cd vehicle registration

2. Build and run the project:
   ```bash
   ./mvnw spring-boot:run

4. Access the H2 database console in your browser:
   ```bash
   URL: http://localhost:8080/h2-console
   JDBC URL: jdbc:h2:mem:testdb
   User: sa
   Password: password
   From the console you can consult the tables and data.

## Use

Authentication
- POST /signup: Register a new user.
    - Body: Data of the new user (name, email, password, role).
- POST /login: Log in and get a JWT token for authentication.
    - Body: User credentials (email and password).

User management
- GET /users/get/me: Obtains the profile data of the authenticated user.
- GET /users/get/{id}: Gets a user profile by its ID (only accessible to administrators).

Vehicle management
- POST /cars/add: Add a new vehicle to the system.
    - Body: Vehicle information (make, model, year, etc.).
- GET /cars/get/{id}: Gets the details of a vehicle by its ID.

Roles and permissions:
- ADMIN: Access all endpoints, including user and vehicle management.
- CLIENT: You can manage your own profile and vehicles, but you do not have access to the global administration of users or vehicles.
Each endpoint is protected and requires authentication using the JWT token obtained when logging in.

## Documentation with Swagger
Swagger UI is enabled for documenting and testing the API. 
- You can access the interface at:
    URL: http://localhost:8080/swagger-ui.html

- To get the JSON schemas from the API, you can use:
    OpenAPI Docs: http://localhost:8080/api-docs

Swagger makes it easy to explore and test endpoints right from the browser.

## Contribute

Contributions are welcome! If you want to collaborate on this project, follow these steps:

1. Make a **fork** of the repository.
    ```bash
    git fork https://github.com/yourusername/vehicleregistration.git

2. Clone your forked repository to your local machine:
   ```bash
   git clone https://github.com/yourusername/vehicleregistration.git
   cd vehicle registration

4. Create a new branch for your feature or bug fix:
   ```bash
   git checkout -b feature/new-feature

6. Make the necessary changes and commit:
   ```bash
   git add .
   git commit -m "Added new functionality"

8. Upload your branch to the remote repository:
   ```bash
   git push origin feature/new-feature

10. Open a Pull Request from your forked repository to the main repository.

## License

This project is licensed under the [GPL-3.0 License](https://www.gnu.org/licenses/gpl-3.0.html), which means that you can use, modify and distribute the code under the terms of the GPL -3.0. 

For more details, see the [LICENSE](LICENSE) file in this repository.
