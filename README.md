# Bookstore REST API
A RESTful API for managing an online bookstore, built using Spring Boot, Hibernate, and PostgreSQL. The API provides functionality to manage books, authors, and genres, including CRUD operations and search capabilities.

##Setup Instructions
##Step 1: Clone the Repository
git clone <repository-url>
cd bookstore

##Step 2: Configure the Database
Update src/main/resources/application.properties with your PostgreSQL credentials:
spring.datasource.url=jdbc:postgresql://localhost:5432/bookstore
spring.datasource.username=<your-username>
spring.datasource.password=<your-password>
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

##Step 3: Build the Project
Build the project using Maven:
mvn clean install

##Step 4: Run the Application
Start the application:
mvn spring-boot:run
The API will be available at: http://localhost:8080
