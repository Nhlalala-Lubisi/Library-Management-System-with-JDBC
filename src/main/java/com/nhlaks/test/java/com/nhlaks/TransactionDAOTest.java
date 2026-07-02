package com.nhlaks;

import com.nhlaks.dao.TransactionDAO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionDAOTest {

    private final TransactionDAO transactionDAO =
            new TransactionDAO();

    @Test
    void testBorrowBook() {

        assertTrue(transactionDAO.borrowBook(1,1));

    }

    @Test
    void testReturnBook() {

        assertTrue(transactionDAO.returnBook(1));

    }

}