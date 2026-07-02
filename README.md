# 📚 Library Management System with JDBC

A Java-based console application that demonstrates a Library Management System using Java 21, JDBC, MySQL, and Maven. The system allows librarians to manage books, users, and borrowing transactions through a menu-driven interface.

---

## 📖 Project Overview

This application provides basic library management functionality, including:

- Book management (Add, View, Update, Delete)
- User registration and management
- Borrowing books
- Returning books
- Viewing borrowing history
- MySQL database integration using JDBC

The project follows a layered architecture by separating the application into configuration, model, DAO, and service layers.

---

## 🚀 Features

### 📚 Book Management
- Add new books
- View all books
- Update book details
- Delete books

### 👤 User Management
- Register new users
- View registered users

### 🔄 Borrowing System
- Borrow available books
- Return borrowed books
- Automatically update book availability
- Store borrowing history

### 💾 Database
- MySQL database
- JDBC connectivity
- Foreign key relationships
- Transaction support

---

## 🛠 Technologies Used

- Java 21
- JDBC
- MySQL
- Maven
- IntelliJ IDEA
- JUnit 5

---

## 📂 Project Structure

```
LibraryManagementSystemWithJDBC
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.nhlaks
│   │   │       ├── config
│   │   │       │   └── DatabaseConnection.java
│   │   │       │
│   │   │       ├── dao
│   │   │       │   ├── BookDAO.java
│   │   │       │   ├── UserDAO.java
│   │   │       │   └── TransactionDAO.java
│   │   │       │
│   │   │       ├── model
│   │   │       │   ├── Book.java
│   │   │       │   ├── User.java
│   │   │       │   └── BorrowTransaction.java
│   │   │       │
│   │   │       ├── service
│   │   │       │   └── LibraryService.java
│   │   │       │
│   │   │       └── Main.java
│   │   │
│   │   └── resources
│   │       └── schema.sql
│   │
│   └── test
│       └── java
│           └── com.nhlaks
│               ├── BookDAOTest.java
│               ├── UserDAOTest.java
│               └── TransactionDAOTest.java
│
└── pom.xml
```

---

## 🗄 Database Schema

The project uses three tables:

### Books

| Column | Type |
|---------|------|
| id | INT |
| title | VARCHAR |
| author | VARCHAR |
| available | BOOLEAN |

### Users

| Column | Type |
|---------|------|
| id | INT |
| name | VARCHAR |

### Transactions

| Column | Type |
|---------|------|
| id | INT |
| book_id | INT |
| user_id | INT |
| borrow_date | DATE |
| return_date | DATE |

---

## ⚙ Installation

### 1. Clone the repository

```bash
git clone https://github.com/yourusername/LibraryManagementSystemWithJDBC.git
```

### 2. Open in IntelliJ IDEA

Open the project as a Maven project.

### 3. Configure MySQL

Create a database named:

```
library_db
```

Execute:

```
schema.sql
```

### 4. Configure Database Connection

Update `DatabaseConnection.java`:

```java
private static final String URL = "jdbc:mysql://localhost:3306/library_db";
private static final String USER = "root";
private static final String PASSWORD = "your_password";
```

Replace `your_password` with your MySQL password.

### 5. Run the Application

Run:

```
Main.java
```

---

## 📋 Application Menu

```
===== Library Management System =====

1. Add Book
2. View Books
3. Update Book
4. Delete Book
5. Register User
6. View Users
7. Borrow Book
8. Return Book
9. View Transactions
0. Exit
```

---

## 🧪 Testing

The project includes JUnit tests for:

- BookDAO
- UserDAO
- TransactionDAO

Run the tests using Maven:

```bash
mvn test
```

or directly from IntelliJ IDEA.

---

## 📸 Sample Output

```
===== Library Management System =====

1. Add Book
2. View Books
3. Update Book
4. Delete Book
5. Register User
6. View Users
7. Borrow Book
8. Return Book
9. View Transactions
0. Exit

Choice: 1

Title:
Clean Code

Author:
Robert C. Martin

Book added successfully.
```

---

## 🎯 Learning Outcomes

This project demonstrates:

- Object-Oriented Programming (OOP)
- Layered Architecture
- JDBC Programming
- CRUD Operations
- MySQL Integration
- Exception Handling
- Maven Project Management
- Unit Testing with JUnit

---

## 👨‍💻 Author

**Lubisi Nhlalala**

Advanced Diploma in Information Technology

Passionate about Software Development, Artificial Intelligence, and Machine Learning.

---

## 📄 License

This project is intended for educational purposes.
