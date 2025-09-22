# Jdbc_project
This is a Java JDBC project demonstrating database connectivity, CRUD operations, and data management using Java.

Features

Connects Java application to a relational database (MySQL, PostgreSQL, or Oracle).

Performs CRUD operations:

Create: Insert new records

Read: Fetch records

Update: Modify records

Delete: Remove records

Uses PreparedStatement to prevent SQL injection.

Handles exceptions and errors gracefully.

Project Structure
JdbcProject/
│
├── src/                     # Java source files
│   ├── com/jdbcproject/
│   │   ├── DatabaseConnection.java
│   │   ├── Main.java
│   │   ├── EmployeeDAO.java
│   │   └── Employee.java
│
├── lib/                     # JDBC Driver Jar (e.g., mysql-connector-java-x.x.x.jar)
│
├── SQL/                     # Database schema and sample data
│   └── schema.sql
│
└── README.md

Setup Instructions
1. Database Setup

Install MySQL (or other database).

Create a database:

CREATE DATABASE jdbc_project;


Run the SQL script in SQL/schema.sql to create tables and insert sample data.

2. Configure Project

Add JDBC driver jar to your project classpath (lib/mysql-connector-java-x.x.x.jar).

Update DatabaseConnection.java with your database details:

String url = "jdbc:mysql://localhost:3306/jdbc_project";
String user = "root";
String password = "your_password";

3. Compile and Run
cd "C:\Users\Archana A\OneDrive\Desktop\JdbcProject\src"
javac -cp .;..\lib\mysql-connector-java-x.x.x.jar com/jdbcproject/*.java
java -cp .;..\lib\mysql-connector-java-x.x.x.jar com.jdbcproject.Main


For Linux/Mac, replace ; with : in classpath.

Run Main.java.

Follow the console menu to:

Add new employees.

View existing employees.

Update employee details.

Delete employees.
