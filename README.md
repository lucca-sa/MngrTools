# MngrTools

MngrTools is a fullstack application designed for user and department management. The project is built with Spring Boot for the backend and Angular for the frontend, utilizing PostgreSQL for data storage. This project demonstrates fullstack development and Dockerization practices.

## Technologies Used

- **Angular**: Framework for building the frontend.
- **Spring Boot**: Framework for backend development, following the Ports and Adapters (Hexagonal) pattern.
- **Angular Material**: UI component library for Angular.
- **PostgreSQL**: Relational database management system.
- **Flyway**: Database migration tool.

# Project Structure

## Backend

The MngrTools backend follows the Ports and Adapters (Hexagonal) pattern, providing a clear separation between the core application and its external interfaces. The project uses Spring Data JPA and JPA Repository for database access, along with JPQL for complex queries. The backend code includes unit tests developed with JUnit and Mockito to ensure quality and reliability.
```bash
 ├── /adapter
 │    ├── /exception
 │    ├── /input
 │    │    ├── /generic
 │    │    ├── /user
 │    │    │    ├── /controller
 │    │    │    ├── /dto
 │    │    │    └── /swagger
 │    │    └── /department
 │    │         ├── /controller
 │    │         ├── /dto
 │    │         └── /swagger
 │    ├── /mapper
 │    └── /output
 │         ├── /database
 │         │    ├── /entity
 │         │    └── /repository
 │         └── /persistence
 ├── /domain
 │    ├── /model
 │    └── /service
 │         ├── /user
 │         └── /department
 └── /port
      ├── /user
      │    ├── /input
      │    └── /output
      └── /department
           ├── /input
           └── /output
```
## Frontend

The MngrTools frontend is built with Angular and uses Angular Material for UI components. The application structure is organized into components and services to facilitate code reuse and maintainability.
```bash
 ├── /app
 │    ├── /pages
 │    │    ├── /users
 │    │    │    ├── /user-create-update-modal
 │    │    │    └── /user-table
 │    │    └── /departments
 │    │         ├── /department-create-update-modal
 │    │         └── /department-table
 │    ├── /shared
 │    │    ├── /components
 │    │    │    ├── /confirm-dialog
 │    │    │    └── /header
 │    │    ├── /models
 │    │    └── /services
```
## Dockerization

To run the entire project with Docker, follow these instructions:

1. **Ensure Docker is installed on your machine.**

2. **Navigate to the root folder of the project where the `docker-compose.yml` file is located.**

3. **Run the following command to start all services:**

  ```bash
  docker compose up
  ```

   This command will build and start the containers for the backend, frontend, and PostgreSQL database.

4. **Access the application:**

   - **Frontend**: [http://localhost:4200](http://localhost:4200)
   - **Backend**: [http://localhost:8080](http://localhost:8080)
   - **PostgreSQL Database**: Port `5432` (configured in `docker-compose.yml`).
