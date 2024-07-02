# **Sample Ecommerce Application**

This is a Spring Boot application designed to manage an ecommerce platform. It includes functionalities for different user roles such as Admin, Employee, and Customer, and provides various features like role-based authentication, product management, and order processing.

## Table of Contents

- [Roles](#roles)
- [Technologies Used](#technologies-used)
- [Concepts Used](#concepts-used)
- [Public Functionalities](#public-functionalities)
- [Private Role-Based Functionalities](#private-role-based-functionalities)
- [Setup Instructions](#setup-instructions)
- [Environment Variables](#environment-variables)
- [Running the Application](#running-the-application)

## Roles

### 1. Admin
### 2. Employee
### 3. Customer

## Technologies Used

- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- Docker
- JWT (JSON Web Tokens)
- Lombok
- MySQL
- JUnit
- Mockito

## Concepts Used

* ### Role Based Authentication
* ### Dependency Inversion
* ### Stateless Token Authorization
* ### Dynamic JPA Query
* ### Pagination
* ### Exception Handling
* ### Validation
* ### Unit Testing

## Public Functionalities

* ### Register Customer
* ### Log User In
* ### Get Products Page By Category and Product Name

## Private Role-Based Functionalities

* ## Admin
    * ### Create Employees
    * ### Create Admins
* ## Employee
    * ### Create Products
    * ### Update Products
    * ### Create Categories
    * ### Update Categories
    * ### Manage Product-Category Association
* ## Customer
    * ### Update Cart
    * ### Place Order
    * ### Cancel Order

## Setup Instructions

To run this project locally, follow these steps:

1. **Clone the repository:**
    ```bash
    git clone https://github.com/Pruthvik007/SampleEcommerceApplication.git
    cd SampleEcommerceApplication
    ```

2. **Ensure Docker is installed on your machine.**

3. **Create a `.env` file in the root folder of the project and add values for the following keys:**

    ```plaintext
    MYSQL_DATABASE=your_database_name
    MYSQL_USER=your_database_user
    MYSQL_PASSWORD=your_database_password
    MYSQL_ROOT_PASSWORD=your_root_password
    JWT_SECRET=your_jwt_secret
    ADMIN_NAME=your_admin_name
    ADMIN_EMAIL=your_admin_email
    ADMIN_PASSWORD=your_admin_password
    ```

4. **Build and run the application using Docker Compose:**
    ```bash
    docker-compose up --build
    ```

## Environment Variables

The application requires the following environment variables to be set:

- `MYSQL_DATABASE`: The name of the MySQL database.
- `MYSQL_USER`: The MySQL database user.
- `MYSQL_PASSWORD`: The password for the MySQL database user.
- `MYSQL_ROOT_PASSWORD`: The root password for MySQL.
- `JWT_SECRET`: The secret key for JWT token generation.
- `ADMIN_NAME`: The name of the admin user.
- `ADMIN_EMAIL`: The email of the admin user.
- `ADMIN_PASSWORD`: The password of the admin user.

## Running the Application

Once the Docker containers are up and running, the application will be accessible at `http://localhost:8080`.

You can use tools like Postman to interact with the API endpoints. Make sure to register a customer, log in, and use the JWT token for accessing protected routes.

### Important Points:
- For customers, we need to **register first**.
- For all other roles (Admin, Employee, Customer), we need to **log in first** and send the JWT token under the Authorization tab as `Bearer <token>` for further requests.

## Exception Handling

The application includes custom exception handling for various scenarios such as:

- `UserException`
- `OrderException`
- `ProductException`
- `CategoryException`

These exceptions ensure that meaningful error messages are returned to the client.

## Order Status

The application supports the following order statuses:

- `CREATED`
- `DELIVERED`
- `CANCELLED`

## User Status

The application supports the following user statuses:

- `CREATED`
- `VERIFIED`
- `INACTIVE`

## Additional Information

For more details on the implementation, refer to the source code and the comments provided within the codebase.

---

Feel free to contribute to this project by submitting issues or pull requests. For major changes, please open an issue first to discuss what you would like to change.

Happy coding!
