package com.nhlaks.dao;

import com.nhlaks.config.DatabaseConnection;
import com.nhlaks.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // Register a new user
    public boolean addUser(User user) {

        String sql = "INSERT INTO users(name) VALUES (?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getName());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Retrieve a user by ID
    public User getUserById(int id) {

        String sql = "SELECT * FROM users WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Retrieve all users
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM users";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {

                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );

                users.add(user);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    // Update a user's details
    public boolean updateUser(User user) {

        String sql = "UPDATE users SET name = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getName());
            statement.setInt(2, user.getId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Delete a user
    public boolean deleteUser(int id) {

        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Check if a user exists
    public boolean userExists(int id) {

        String sql = "SELECT id FROM users WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}