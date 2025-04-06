package com.biblioteca.biblioteca.model;

// This class represents a loan of a book to a user
public class Loan {
    private String id;
    private String user;
    private String book;
    private String loanDate;
    private String returnDate;

    // Constructor
    public Loan(){}

    public Loan(String id, String book, String user, String loanDate, String returnDate) {
        this.id = id;
        this.book = book;
        this.user = user;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    // Getters and Setters (loan ID)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
// user ID or name of the person who did the loan
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
// returns the book ID or title of the loan
    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }
// when the loan was made
    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }
// expected return date
    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

}

