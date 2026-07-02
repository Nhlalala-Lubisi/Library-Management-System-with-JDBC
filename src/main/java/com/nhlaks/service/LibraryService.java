package com.nhlaks.service;

import com.nhlaks.dao.BookDAO;
import com.nhlaks.dao.TransactionDAO;
import com.nhlaks.dao.UserDAO;
import com.nhlaks.model.Book;
import com.nhlaks.model.User;

import java.util.Scanner;

public class LibraryService {

    private final Scanner scanner = new Scanner(System.in);
    private final BookDAO bookDAO = new BookDAO();
    private final UserDAO userDAO = new UserDAO();
    private final TransactionDAO transactionDAO = new TransactionDAO();

    public void start() {
        int choice;
        do {
            System.out.println("\n===== Library Management System =====");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Update Book");
            System.out.println("4. Delete Book");
            System.out.println("5. Register User");
            System.out.println("6. View Users");
            System.out.println("7. Borrow Book");
            System.out.println("8. Return Book");
            System.out.println("9. View Transactions");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addBook();
                case 2 -> bookDAO.getAllBooks().forEach(System.out::println);
                case 3 -> updateBook();
                case 4 -> deleteBook();
                case 5 -> registerUser();
                case 6 -> userDAO.getAllUsers().forEach(System.out::println);
                case 7 -> borrowBook();
                case 8 -> returnBook();
                case 9 -> transactionDAO.viewTransactions();
                case 0 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private void addBook() {
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Author: ");
        String author = scanner.nextLine();
        boolean ok = bookDAO.addBook(new Book(title, author, true));
        System.out.println(ok ? "Book added." : "Failed.");
    }

    private void updateBook() {
        System.out.print("Book ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        Book b = bookDAO.getBookById(id);
        if (b == null) { System.out.println("Not found."); return; }
        System.out.print("New title: ");
        b.setTitle(scanner.nextLine());
        System.out.print("New author: ");
        b.setAuthor(scanner.nextLine());
        System.out.println(bookDAO.updateBook(b) ? "Updated." : "Failed.");
    }

    private void deleteBook() {
        System.out.print("Book ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println(bookDAO.deleteBook(id) ? "Deleted." : "Failed.");
    }

    private void registerUser() {
        System.out.print("User name: ");
        String name = scanner.nextLine();
        System.out.println(userDAO.addUser(new User(name)) ? "Registered." : "Failed.");
    }

    private void borrowBook() {
        System.out.print("Book ID: ");
        int bookId = Integer.parseInt(scanner.nextLine());
        System.out.print("User ID: ");
        int userId = Integer.parseInt(scanner.nextLine());
        System.out.println(transactionDAO.borrowBook(bookId, userId) ? "Borrowed." : "Failed.");
    }

    private void returnBook() {
        System.out.print("Book ID: ");
        int bookId = Integer.parseInt(scanner.nextLine());
        System.out.println(transactionDAO.returnBook(bookId) ? "Returned." : "Failed.");
    }
}
