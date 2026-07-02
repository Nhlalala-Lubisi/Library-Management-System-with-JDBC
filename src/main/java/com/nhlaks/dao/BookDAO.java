package com.nhlaks.dao;

import com.nhlaks.config.DatabaseConnection;
import com.nhlaks.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    // Add a new book
    public boolean addBook(Book book) {

        String sql = "INSERT INTO books(title, author, available) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setBoolean(3, book.isAvailable());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // View all books
    public List<Book> getAllBooks() {

        List<Book> books = new ArrayList<>();

        String sql = "SELECT * FROM books";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {

                Book book = new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getBoolean("available")
                );

                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    // Search book by ID
    public Book getBookById(int id) {

        String sql = "SELECT * FROM books WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getBoolean("available")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Update a book
    public boolean updateBook(Book book) {

        String sql = """
                UPDATE books
                SET title = ?, author = ?, available = ?
                WHERE id = ?
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setBoolean(3, book.isAvailable());
            statement.setInt(4, book.getId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Delete a book
    public boolean deleteBook(int id) {

        String checkSql =
                "SELECT COUNT(*) FROM transactions WHERE book_id = ?";

        String deleteSql =
                "DELETE FROM books WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection()) {

            try (PreparedStatement check =
                         connection.prepareStatement(checkSql)) {

                check.setInt(1, id);

                ResultSet rs = check.executeQuery();

                if (rs.next() && rs.getInt(1) > 0) {

                    System.out.println(
                            "Cannot delete book because it has borrowing history."
                    );

                    return false;
                }
            }

            try (PreparedStatement delete =
                         connection.prepareStatement(deleteSql)) {

                delete.setInt(1, id);

                return delete.executeUpdate() > 0;
            }

        } catch (SQLException e) {

            System.err.println(e.getMessage());
        }

        return false;
    }

    // Mark book as borrowed
    public boolean borrowBook(int bookId) {

        String sql = "UPDATE books SET available = FALSE WHERE id = ? AND available = TRUE";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, bookId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Mark book as returned
    public boolean returnBook(int bookId) {

        String sql = "UPDATE books SET available = TRUE WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, bookId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}