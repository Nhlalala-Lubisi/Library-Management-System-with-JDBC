package com.nhlaks.dao;

import com.nhlaks.config.DatabaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class TransactionDAO {

    /**
     * Borrow a book.
     */
    public boolean borrowBook(int bookId, int userId) {

        String insertTransaction = """
                INSERT INTO transactions(book_id, user_id, borrow_date)
                VALUES (?, ?, ?)
                """;

        String updateBook = """
                UPDATE books
                SET available = FALSE
                WHERE id = ? AND available = TRUE
                """;

        try (Connection connection = DatabaseConnection.getConnection()) {

            connection.setAutoCommit(false);

            // Check if the book is available
            BookDAO bookDAO = new BookDAO();

            if (bookDAO.getBookById(bookId) == null) {
                System.out.println("Book not found.");
                return false;
            }

            if (!bookDAO.getBookById(bookId).isAvailable()) {
                System.out.println("Book is already borrowed.");
                return false;
            }

            // Insert transaction
            try (PreparedStatement transactionStatement =
                         connection.prepareStatement(insertTransaction)) {

                transactionStatement.setInt(1, bookId);
                transactionStatement.setInt(2, userId);
                transactionStatement.setDate(3, Date.valueOf(LocalDate.now()));

                transactionStatement.executeUpdate();
            }

            // Update book availability
            try (PreparedStatement updateStatement =
                         connection.prepareStatement(updateBook)) {

                updateStatement.setInt(1, bookId);
                updateStatement.executeUpdate();
            }

            connection.commit();

            System.out.println("Book borrowed successfully.");

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }

    /**
     * Return a borrowed book.
     */
    public boolean returnBook(int bookId) {

        String updateTransaction = """
                UPDATE transactions
                SET return_date = ?
                WHERE book_id = ?
                AND return_date IS NULL
                """;

        String updateBook = """
                UPDATE books
                SET available = TRUE
                WHERE id = ?
                """;

        try (Connection connection = DatabaseConnection.getConnection()) {

            connection.setAutoCommit(false);

            // Update transaction
            try (PreparedStatement transactionStatement =
                         connection.prepareStatement(updateTransaction)) {

                transactionStatement.setDate(1, Date.valueOf(LocalDate.now()));
                transactionStatement.setInt(2, bookId);

                int rows = transactionStatement.executeUpdate();

                if (rows == 0) {
                    System.out.println("Book is not currently borrowed.");
                    connection.rollback();
                    return false;
                }
            }

            // Update book availability
            try (PreparedStatement updateStatement =
                         connection.prepareStatement(updateBook)) {

                updateStatement.setInt(1, bookId);
                updateStatement.executeUpdate();
            }

            connection.commit();

            System.out.println("Book returned successfully.");

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }

    /**
     * View all transactions.
     */
    public void viewTransactions() {

        String sql = """
                SELECT t.id,
                       b.title,
                       u.name,
                       t.borrow_date,
                       t.return_date
                FROM transactions t
                JOIN books b ON t.book_id = b.id
                JOIN users u ON t.user_id = u.id
                ORDER BY t.id
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            System.out.println("\n===== Borrowing History =====");

            while (rs.next()) {

                System.out.println("Transaction ID : " + rs.getInt("id"));
                System.out.println("Book           : " + rs.getString("title"));
                System.out.println("User           : " + rs.getString("name"));
                System.out.println("Borrow Date    : " + rs.getDate("borrow_date"));
                System.out.println("Return Date    : " + rs.getDate("return_date"));
                System.out.println("-----------------------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}