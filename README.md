1. What is JDBC?

JDBC (Java Database Connectivity) is a Java API that allows Java programs to connect and interact with databases.
It provides a standard interface to execute SQL queries, retrieve results, and update the database.

Part of Java Standard Edition (JSE).

Works with any database that has a JDBC driver.

Key Point: JDBC is platform-independent — your Java code stays the same even if the database changes (just need the appropriate driver).

2. Features of JDBC

Database independent: Works with multiple databases (Oracle, MySQL, SQL Server, etc.)

Standard API: Same set of classes and interfaces for all databases.

Supports SQL execution: Can execute SELECT, INSERT, UPDATE, DELETE commands.

Supports transaction management: Commit, rollback, and savepoints.

Supports stored procedures and advanced database features.

3. JDBC Architecture

JDBC uses a 4-layer architecture:

A. JDBC API Layer

Used by Java applications to interact with the database.

Main interfaces: Connection, Statement, PreparedStatement, ResultSet, etc.

B. JDBC Driver Manager

Loads database drivers and establishes the connection.

Manages different types of JDBC drivers.

C. JDBC Driver Layer

Converts JDBC calls to database-specific calls.

Types of drivers:

Type 1: JDBC-ODBC bridge (obsolete)

Type 2: Native API driver

Type 3: Network protocol driver

Type 4: Pure Java driver (most popular, e.g., ojdbc8.jar for Oracle)

D. Database

The actual relational database (Oracle, MySQL, PostgreSQL, etc.) that stores and manages data.

4. Steps to Connect and Use JDBC

Load the JDBC Driver

Class.forName("oracle.jdbc.driver.OracleDriver");


Establish Connection

Connection con = DriverManager.getConnection(
    "jdbc:oracle:thin:@localhost:1521:orcl", "username", "password");


Create Statement

Statement st = con.createStatement();


Execute SQL Queries

For SELECT:

ResultSet rs = st.executeQuery("SELECT * FROM employee");


For INSERT/UPDATE/DELETE:

st.executeUpdate("INSERT INTO employee VALUES (101, 'Alice', 50000)");


Process ResultSet (for SELECT queries)

while(rs.next()) {
    int id = rs.getInt("emp_id");
    String name = rs.getString("emp_name");
    System.out.println(id + " " + name);
}


Close Resources

rs.close();
st.close();
con.close();

5. Important JDBC Interfaces and Classes
Interface/Class	Purpose
Connection	Represents a connection to the database.
Statement	Executes static SQL queries.
PreparedStatement	Executes parameterized SQL queries (safer & faster).
CallableStatement	Executes stored procedures.
ResultSet	Stores the result of SELECT queries.
DriverManager	Manages JDBC drivers and connections.
SQLException	Handles database errors.
6. Advantages of JDBC

Platform-independent access to databases.

Supports multiple SQL operations.

Can work with any relational database with a driver.

Supports dynamic queries with PreparedStatement.

Supports transactions (commit, rollback).

7. Limitations of JDBC

Requires SQL knowledge to use effectively.

Statement objects are prone to SQL injection (use PreparedStatement to avoid).

Manual resource management is needed (close() methods).

8. Best Practices

Always use PreparedStatement for dynamic queries.

Always close Connection, Statement, ResultSet to prevent memory leaks.

Handle SQLException properly with try-catch blocks.

Use connection pooling in large applications for efficiency.
