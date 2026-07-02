package com.nhlaks;

import com.nhlaks.service.LibraryService;

public class Main {

    public static void main(String[] args) {

        try {
            LibraryService libraryService = new LibraryService();
            libraryService.start();
        } catch (Exception e) {
            System.out.println("Failed to start the Library Management System.");
            System.err.println("Database Error: " + e.getMessage());
        }

    }
}