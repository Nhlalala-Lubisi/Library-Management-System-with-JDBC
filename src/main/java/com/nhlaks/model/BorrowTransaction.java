package com.nhlaks.model;

import java.sql.Date;

public class BorrowTransaction {

    private int id;
    private int bookId;
    private int userId;
    private Date borrowDate;
    private Date returnDate;

    // Default constructor
    public BorrowTransaction() {
    }

    // Constructor without ID
    public BorrowTransaction(int bookId, int userId, Date borrowDate, Date returnDate) {
        this.bookId = bookId;
        this.userId = userId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    // Constructor with ID
    public BorrowTransaction(int id, int bookId, int userId, Date borrowDate, Date returnDate) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    // Getters

    public int getId() {
        return id;
    }

    public int getBookId() {
        return bookId;
    }

    public int getUserId() {
        return userId;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    // Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * Returns true if the book has not yet been returned.
     */
    public boolean isBorrowed() {
        return returnDate == null;
    }

    @Override
    public String toString() {
        return "BorrowTransaction{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", userId=" + userId +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + (returnDate == null ? "Not Returned" : returnDate) +
                '}';
    }
}