# playground-api - School Management System

A Spring Boot application that provides REST APIs for managing teachers and students in a school system, featuring secure authentication, database migrations, and comprehensive API documentation.

## 🚀 Features

- Teacher and Student management with many-to-many relationships
- RESTful API endpoints with Spring MVC
- JWT-based authentication and authorization
- Database migrations with Flyway
- PostgreSQL database integration
- Spring Security implementation
- Actuator for application monitoring
- Spring Batch support for batch processing
- Comprehensive test coverage
- Docker support

## 📋 Prerequisites

- Java 17
- Maven 3.x
- PostgreSQL
- Docker (optional)

## 🛠️ Tech Stack

- **Spring Boot** 3.4.5
- **Spring Security** for authentication and authorization
- **Spring Data JPA** for data persistence
- **PostgreSQL** as the primary database
- **Flyway** for database migrations
- **JWT** for stateless authentication
- **Lombok** for reducing boilerplate code
- **Spring AOP** for aspect-oriented programming
- **Spring Actuator** for monitoring
- **Docker** for containerization

## 🏗️ Project Structure
The project follows a standard Spring Boot application structure:

### Main Directories
* `src/main/java/com.playground.api/`
    * `advice` - Global exception handlers
    * `aspect` - AOP aspects
    * `components` - Reusable components
    * `config` - Configuration classes
    * `controller` - REST controllers
    * `exception` - Custom exceptions
    * `jobs` - Batch jobs
    * `model` - Entity classes
    * `payload` - Request/Response DTOs
    * `repository` - Data repositories
    * `security` - Security configurations
    * `service` - Business logic
    * `view` - View related classes

### Resources
* `src/main/resources/`
    * `db/migration/postgresql` - Database migrations
    * `application.yml` - Main configuration
    * `application-local.yml` - Local configuration
    * `application-example.yml` - Example configuration

### Other Files
* `src/test` - Test files
* `docker-compose.yaml` - Docker composition file
* `pom.xml` - Project dependencies and build configuration
* `LICENSE` - MIT License file


### 🚀 Getting Started

### 1. Clone the repository
```bash
git clone [repository-url] 
cd PlaygroundAPI
```

### 2. Configure the Database
Create a PostgreSQL database and update the credentials in `application.yml` or use environment variables.

### 3. Build the Project
```bash
mvn clean install
```

### 4. Run the Application
```bash
mvn spring-boot:run
```

## 📚 API Documentation

### Key Endpoints

#### Teacher Management
- `POST /school/teacher/{teacherId}/addStudent` - Add a student to a teacher
- `GET /school/teacher/{teacherId}/students` - Get all students for a teacher
- `GET /school/student/{studentId}/teachers` - Get all teachers for a student

## 🔧 Configuration

Application configuration is managed through `application.yml` files:
- `application.yml` - Main configuration file
- `application-local.yml` - Local development settings
- `application-example.yml` - Example configuration template

## 🧪 Testing

Run the test suite with: mvn clean test

## 📦 Database Migrations

Flyway is used for database migrations

## 🛡️ Security

The application uses JWT-based authentication. Protected endpoints require a valid JWT token in the Authorization header.

## 🔍 Monitoring

Spring Actuator endpoints are available for monitoring application health and metrics.

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👥 Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a new Pull Request

## ✨ Acknowledgments

- Spring Boot team for the excellent framework
- All contributors to this project
