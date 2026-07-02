package com.nhlaks;

import com.nhlaks.dao.BookDAO;
import com.nhlaks.model.Book;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookDAOTest {

    private final BookDAO bookDAO = new BookDAO();

    @Test
    void testAddBook() {

        Book book = new Book(
                "JUnit Testing",
                "OpenAI",
                true
        );

        assertTrue(bookDAO.addBook(book));
    }

    @Test
    void testGetBookById() {

        Book book = bookDAO.getBookById(1);

        assertNotNull(book);
    }

    @Test
    void testGetAllBooks() {

        List<Book> books = bookDAO.getAllBooks();

        assertFalse(books.isEmpty());
    }

    @Test
    void testUpdateBook() {

        Book book = bookDAO.getBookById(1);

        if (book != null) {

            book.setTitle("Updated Title");

            assertTrue(bookDAO.updateBook(book));

        }
    }

    @Test
    void testDeleteBook() {

        Book book = new Book(
                "Temporary Book",
                "Tester",
                true
        );

        bookDAO.addBook(book);

        List<Book> books = bookDAO.getAllBooks();

        Book lastBook = books.get(books.size() - 1);

        assertTrue(bookDAO.deleteBook(lastBook.getId()));
    }

}