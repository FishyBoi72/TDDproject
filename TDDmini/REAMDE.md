Running the Application

If you’re using an IDE: You can simply run the OrderManagementApplication.java class, and the app will start up.

From the command line: Navigate to the project folder and run: mvn spring-boot:run

This will launch the application, and it will be available at:http://localhost:8080

Testing the Application

I wrote several tests to ensure that the system works as expected. The tests are based on the CRUD operations (create, read, update, delete) for managing orders.

Unit tests: These tests check the internal business logic in the OrderService to make sure the system behaves correctly when creating, updating, or deleting an order.

Integration tests: These tests focus on the OrderController to ensure that the API endpoints work properly when handling requests and responses.

How to Run the Tests
To run the tests, simply use Maven. Open your terminal or command prompt in the project folder and run:
mvn test

This command will execute all the tests. The results will be displayed in the terminal, showing which tests passed and if there were any failures.

API Overview
The system exposes several REST API endpoints that allow users to manage orders. Below is a quick overview of how to use them.

Create a new order:
POST /api/orders

Example request body:


{
"customerName": "John Doe",
"orderDate": "2024-09-12",
"shippingAddress": "123 Main St",
"total": 100.50
}

Get all orders:
GET /api/orders

Get a specific order by ID:
GET /api/orders/{id}

Update an existing order:
PUT /api/orders/{id}

Example request body:

{
"customerName": "Jane Doe",
"orderDate": "2024-09-12",
"shippingAddress": "456 Elm St",
"total": 150.00
}

Delete an order:
DELETE /api/orders/{id}

You can use tools like Postman or cURL to make these requests and interact with the API.