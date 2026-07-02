package com.nhlaks.model;

public class Book {

    private int id;
    private String title;
    private String author;
    private boolean available;

    // Default constructor
    public Book() {
    }

    // Constructor without ID (for adding a new book)
    public Book(String title, String author, boolean available) {
        this.title = title;
        this.author = author;
        this.available = available;
    }

    // Constructor with ID (for retrieving/updating books)
    public Book(int id, String title, String author, boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = available;
    }

    // Getters

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    // Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "----------------------------------------\n" +
                "Book ID   : " + id + "\n" +
                "Title     : " + title + "\n" +
                "Author    : " + author + "\n" +
                "Available : " + (available ? "Yes" : "No") + "\n" +
                "----------------------------------------";
    }
}