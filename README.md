# Demo - HCA Parallon Java Code Exercise

This is a simple Spring Boot application that demonstrates the implementation of a RESTful API for managing customer accounts. The application allows you to create, update, delete, and retrieve customer accounts using various endpoints.

## Technologies Used

- Java 11
- Spring Boot 2.5.5
- Spring Data JPA
- H2 Database
- Springfox Swagger for API Documentation

## Setup and Running

1. Clone the repository:
git clone: https://github.com/Big-Red-Jon/Demo
2. Navigate to the project directory: cd demo
3. Build and run the application using Maven: mvn spring-boot:run
4. Access the Swagger UI documentation at: http://localhost:8080/demo/swagger-ui/index.html
5. Access the H2 Console: Open your web browser and navigating to the URL: http://localhost:8080/h2-console.
6. Enter Connection Details: 
* On the H2 Console login page, you'll see a form to enter the database connection details:
* - JDBC URL: This should match the `spring.datasource.url` value from your configuration (e.g., `jdbc:h2:mem:testdb`).
* - Username: Use the `spring.datasource.username` value from your configuration (e.g., `sa`).
* - Password: Leave this field empty or use the `spring.datasource.password` value from your configuration.
7. Login to H2 Console: After entering the connection details, click the "Connect" button to log in to the H2 Console.
8. Use H2 Console: Once you're logged in, you'll have access to the H2 Console's web interface. You can execute SQL queries, view tables, and interact with the in-memory database.
9. Make sure to follow these steps when interacting with the API endpoints to ensure proper functionality.

## API Endpoints

### Customers

- GET /customers: Retrieve all customers
- GET /customers/{customerId}: Retrieve a specific customer by ID
- POST /customers: Create a new customer
- PUT /customers/{customerId}: Update an existing customer
- DELETE /customers/{customerId}: Delete a customer

### Accounts

- GET /accounts: Retrieve all accounts
- GET /accounts/{accountId}: Retrieve a specific account by ID
- POST /accounts/create: Create a new account
- PUT /accounts/{accountId}/edit: Update an account's balance
- DELETE /accounts/{accountId}/delete: Delete an account
- PUT /accounts/{accountId}/deposit: Deposit funds into an account
- PUT /accounts/{accountId}/withdraw: Withdraw funds from an account
- GET /accounts/{accountId}/balance: Retrieve the balance of an account
- PUT /accounts/transfer: Transfer funds between accounts

## Project Structure

- `src/main/java/com/example/demo/controller`: Contains the REST controllers for handling customer and account operations.
- `src/main/java/com/example/demo/model`: Contains the domain model classes (Customer and Account).
- `src/main/java/com/example/demo/service`: Contains the service classes responsible for business logic.
- `src/main/java/com/example/demo/repository`: Contains the JPA repositories for database interactions.
- `src/main/java/com/example/demo`: Contains the main application class and Swagger configuration.

## Usage Tips

- When creating a new account via Postman, make sure to use Raw JSON and set the `Content-Type` header to `application/json`.
- After creating a new customer, change the `Content-Type` header back to `multipart/form-data` for other CRUD operations.
  

<img width="423" alt="Screenshot 2023-08-13 at 7 10 21 PM" src="https://github.com/Big-Red-Jon/Demo/assets/46500097/4844fb63-d0ea-477c-a273-cbbd56ae6ecb">






