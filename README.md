# Student Pantry Management System

## Project Overview

### 1. Context
The project involves the development of an API-driven multi-user web-based application specifically designed for our college to manage and optimize the pantry service catering to university students. The system aims to simplify the process of accessing and managing pantry items for students while also providing administrators with the necessary tools to efficiently control and monitor inventory.

### 2. Motivations/Problem Statement
The motivation behind this project arises from the need to address several challenges faced by both students and administrators in managing a student pantry service:
- **Limited Visibility:** Students often lack real-time visibility into product availability, leading to dissatisfaction when desired items are out of stock.
- **Administrative Complexity:** Managing pantry operations, including inventory management, and handling many students simultaneously can be complex and time-consuming without a dedicated system.
- **Student Convenience:** Challenges in accessing pantry items conveniently and managing their pantry needs efficiently.
- **Inventory Management:** Administrators and employees struggle with manual inventory tracking, restocking, and ensuring a variety of items are available.

### 3. Expected Contributions (Solution) and Applicable Architecture and Design Pattern Uses
- **Sophisticated Software Architecture:** Implement a sophisticated multi-tier web application architecture that ensures scalability, robustness, and maintainability.
- **Design Patterns:** Incorporate well-established design patterns such as Singleton, Observer, and Abstract Factory Design Pattern to enhance system efficiency and maintainability.
- **API-Driven Approach:** Develop a robust API-driven system, allowing for easy integration with other systems and potential future enhancements.
- **User-Friendly Interface:** Create a user-friendly web interface that allows students to easily browse, order, and manage pantry items.
- **Role Management:** Involves creating a multiuser system that leverages data encapsulation to define and enforce user roles, access control, and data isolation, ensuring both security and streamlined operations within our application.
- **Administrator Access:** Provide administrator (superuser) role with tools for real-time inventory monitoring, volunteer scheduling, and reporting.
- **Notification System:** Implement a real-time email notification system for order confirmations, restocking updates, and other important announcements.

## System Requirements

Before proceeding with the installation, ensure that the following software is installed on your system:
- Java
- Maven (mvn)
- Postgres
- AngularJS

Application has dependencies on the following components:

- Spring Framework
- JPA (Java Persistence API)

## Installation Steps

### Backend (Java, Spring Boot, Hibernate)

1. **Clone the Repository:**
    ```
    git clone https://solnmates-admin@bitbucket.org/solnmates/studentpantrymanagement.git
    ```
   Note: If installing using compressed code, unzip the compressed code and follow the steps below.

2. **Navigate to the Backend Directory:**
    ```
    cd studentPantryManagement/studentPantry
    ```
3. **Build and Install Dependencies:**
    ```
    mvn clean install
    ```
4. **Run the Application:**
    ```
    mvn spring-boot:run
    ```

### Frontend (Java, Spring Boot, Hibernate)

1. **Clone the Repository:**
    ```
    git clone https://solnmates-admin@bitbucket.org/solnmates/pantry-pal-frontend-app.git
    ```
   Note: If installing using compressed code, unzip the compressed code and follow the steps below.

2. **Navigate to the Frontend Directory:**
    ```
    cd pantry-pal-frontend-app
    ```
3. **Install Node Modules:**
    ```
    npm install
    ```
4. **Run the Application:**
    ```
    ng serve
    ```

### Configuration

**Backend Configuration:**
Navigate to studentPantryManagement/studentPantry/src/main/resources/application.properties. Modify the PostgreSQL credentials as needed.

**Database Initialization:**
Create Database:
Open PostgreSQL and create a new database named studentPantry.
    CREATE DATABASE studentPantry;


### Testing

**Run Unit Tests:**
mvn test


**Test Backend Endpoints:**
Use Postman to test the backend endpoints. The base URL is http://localhost:8080.

### Troubleshooting

If you encounter any issues during installation or configuration, refer to the logs in the console.

## API Documentation

### 1. Login

**Request**
- Method: POST
- URL: http://localhost:8080/login
- Body:
    ```json
    {
        "email": "admin@umich.edu",
        "userPasswd": "dmin123",
        "userrole": "ADMIN"
    }
    ```
**Tests**
- Response status code is 200
- Response has the required fields - message and user
- User ID should be null
- Username should be null
- Email is in a valid format

### 2. Register User

**Request**
- Method: POST
- URL: http://localhost:8080/register
- Body:
    ```json
    {
        "email": "student4@umich.edu",
        "userPasswd": "student123",
        "userrole": "STUDENT",
        "studentId": "32145894",
        "username": "student1"
    }
    ```
**Tests**
- Response status code is 200
- Validate that the message field is not empty
- Username is a non-empty string
- Email is in a valid format

### 3. Logout

**Request**
- Method: POST
- URL: http://localhost:8080/logout
- Body:
    ```json
    {
        "email": "admin@umich.edu"
    }
    ```
**Tests**
- Response status code is 200
- Response has the required fields - message and user
- Message is a non-empty string
- User is null

### 4. Create Order

**Request**
- Method: POST
- URL: http://localhost:8080/order-history/create
- Body:
    ```json
    {
        "userId": 325678,
        "items": {
            "1": 2,
            "2": 1
        },
        "orderPlacedDate": "2023-12-01T10:30:00"
    }
    ```

### 5. Get Order History

**Request**
- Method: GET
- URL: http://localhost:8080/order-history

### 6. Browse Products

**Request**
- Method: GET
- URL: http://localhost:8080/products

**Response**
```json
[
    {
        "productId": 1,
        "productName": "frozen pizza",
        "productQuantity": 3,
        "productExpiryDate": "11/02/2023"
    },
    // ... (other product entries)
]

### 7. Add Product

**Request**
- Method: POST
- URL: http://localhost:8080/products/add
- Request Params:
    - productName: frozen corn
    - productQuantity: 5
    - productExpiryDate: 01/02/2024

**Response:**
Product added successfully

### 8. Update Product

**Request**
- Method: POST
- URL: http://localhost:8080/products/update
- Request Params:
    - productId: 1
    - productName: frozen corn
    - productQuantity: 5
    - productExpiryDate: 01/02/2024


# Service Class 

# UserService Service Class
## Description
The `UserServiceImpl` service class in the Student Pantry application provides functionalities related to user registration, login, logout, and user data retrieval.

The `UserService` service class includes the following main functionalities:

1. **User Registration:**
   - Method: `registerUser(StudentUserDto userDto)`
   - Description: Registers a new user, specifically a student, in the system.

2. **User Login:**
   - Method: `login(UserDto userDto)`
   - Description: Handles user login and authentication. Checks for admin login restrictions.

3. **User Logout:**
   - Method: `logout(UserDto userDto)`
   - Description: Logs out the user and performs necessary cleanup, including handling admin logout.

4. **Get User Details:**
   - Method: `getUserDetailsByUserId(long userId)`
   - Description: Retrieves user details by user ID.

5. **Get User by ID:**
   - Method: `getUserByUserId(long userId)`
   - Description: Retrieves a user by user ID.

6. **Get All Users:**
   - Method: `getAllUsers()`
   - Description: Retrieves a list of all users in the system.

7. **Get All User Emails:**
   - Method: `getAllUserEmails()`
   - Description: Retrieves a list of all user emails in the system.

# ProductService Class

## Description

The `ProductService` class in the Student Pantry application provides functionalities related to managing and interacting with products. It includes methods for retrieving all products, adding new products, updating existing products, and removing products. Additionally, it leverages external services for shortening URLs and sending order updates via email.

1. **Get All Products:**
   - Method: `getAllProducts()`
   - Description: Retrieves a list of all products stored in the system.

2. **Add Product:**
   - Method: `addProduct(String productName, int productQuantity, String productExpiryDate, String productImageURL)`
   - Description: Adds a new product to the system, shortens the product image URL, and sends order updates via email to all registered users.

3. **Update Product:**
   - Method: `updateProduct(Long productId, String productName, int productQuantity, String productExpiryDate)`
   - Description: Updates an existing product in the system based on the provided product ID. It also sends order updates via email to all registered users.

4. **Remove Product:**
   - Method: `removeProduct(Long productId)`
   - Description: Removes a product from the system based on the provided product ID.

# OrderHistoryService Class

## Description

The `OrderHistoryService` class in the Student Pantry application provides functionalities related to managing order history for users. It includes methods for creating orders, retrieving order history for a user, and performing validations on order placement frequency and the number of products in an order. Additionally, it sends order confirmation emails to users upon successful order placement.

The `OrderHistoryService` class includes the following main functionalities:

1. **Create Order:**
   - Method: `createOrder(OrderHistoryDto orderHistoryDto)`
   - Description: Creates an order for a user, validates order placement frequency, checks the number of products in the order, and sends order confirmation emails. Returns an `OrderHistoryResponse` containing details about the order placement status.

2. **Get Order History for User:**
   - Method: `getOrderHistoryForUser(Long userId)`
   - Description: Retrieves the order history for a specific user.

# CartService Class

## Description

The `CartService` class in the Student Pantry application provides functionalities related to managing the shopping cart for users. It includes methods for adding products to the cart, removing products from the cart, updating product quantities, and viewing the contents of the cart.

The `CartService` class includes the following main functionalities:

1. **Add Product to Cart:**
   - Method: `addProductToCart(Long userID, Long productID, int quantity)`
   - Description: Adds a product to the user's shopping cart. If the product is already in the cart, the quantity is updated.

2. **Remove Product from Cart:**
   - Method: `removeProductFromCart(Long userID, Long productID)`
   - Description: Removes a product from the user's shopping cart. If the product is not in the cart, no action is taken.

3. **Update Product Quantity:**
   - Method: `updateProductQuantity(Long userID, Long productID, int newQuantity)`
   - Description: Updates the quantity of a specific product in the user's shopping cart.

4. **View Cart:**
   - Method: `viewCart(Long userID)`
   - Description: Retrieves the contents of the user's shopping cart.





