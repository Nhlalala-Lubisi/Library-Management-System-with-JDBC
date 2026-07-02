package com.nhlaks;

import com.nhlaks.dao.UserDAO;
import com.nhlaks.model.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    private final UserDAO userDAO = new UserDAO();

    @Test
    void testAddUser() {

        User user = new User("Testing User");

        assertTrue(userDAO.addUser(user));
    }

    @Test
    void testGetUserById() {

        User user = userDAO.getUserById(1);

        assertNotNull(user);
    }

    @Test
    void testGetAllUsers() {

        List<User> users = userDAO.getAllUsers();

        assertFalse(users.isEmpty());
    }

    @Test
    void testUserExists() {

        assertTrue(userDAO.userExists(1));
    }

}