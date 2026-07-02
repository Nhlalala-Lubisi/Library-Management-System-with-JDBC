-- ============================================
-- Library Management System Database Schema
-- ============================================

-- Create the database
DROP DATABASE IF EXISTS library_db;
CREATE DATABASE library_db;

-- Use the database
USE library_db;

-- ============================================
-- Books Table
-- ============================================

CREATE TABLE books (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(150) NOT NULL,
                       author VARCHAR(100) NOT NULL,
                       available BOOLEAN NOT NULL DEFAULT TRUE
);

-- ============================================
-- Users Table
-- ============================================

CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(100) NOT NULL
);

-- ============================================
-- Transactions Table
-- ============================================

CREATE TABLE transactions (
                              id INT AUTO_INCREMENT PRIMARY KEY,
                              book_id INT NOT NULL,
                              user_id INT NOT NULL,
                              borrow_date DATE NOT NULL,
                              return_date DATE DEFAULT NULL,

                              CONSTRAINT fk_book
                                  FOREIGN KEY (book_id)
                                      REFERENCES books(id)
                                      ON UPDATE CASCADE
                                      ON DELETE CASCADE,

                              CONSTRAINT fk_user
                                  FOREIGN KEY (user_id)
                                      REFERENCES users(id)
                                      ON UPDATE CASCADE
                                      ON DELETE CASCADE
);

-- ============================================
-- Sample Books
-- ============================================

INSERT INTO books (title, author, available) VALUES
                                                 ('Clean Code', 'Robert C. Martin', TRUE),
                                                 ('Effective Java', 'Joshua Bloch', TRUE),
                                                 ('Head First Java', 'Kathy Sierra', TRUE),
                                                 ('Design Patterns', 'Erich Gamma', TRUE),
                                                 ('The Pragmatic Programmer', 'Andrew Hunt', TRUE);

-- ============================================
-- Sample Users
-- ============================================

INSERT INTO users (name) VALUES
                             ('John Smith'),
                             ('Alice Johnson'),
                             ('Michael Brown');

-- ============================================
-- Sample Transactions
-- ============================================

INSERT INTO transactions
(book_id, user_id, borrow_date, return_date)
VALUES
    (1, 1, CURDATE(), NULL);

-- Mark borrowed book as unavailable
UPDATE books
SET available = FALSE
WHERE id = 1;

-- ============================================
-- Verify Data
-- ============================================

SELECT * FROM books;
SELECT * FROM users;
SELECT * FROM transactions;