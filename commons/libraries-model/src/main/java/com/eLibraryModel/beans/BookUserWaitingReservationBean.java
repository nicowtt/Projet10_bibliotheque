package com.eLibraryModel.beans;

public class BookUserWaitingReservationBean {

    private int id;
    private int bookId;
    private int libraryUserId;
    private String waitReservationDate;
    private BookBean book;

    //Constructor
    public BookUserWaitingReservationBean() {
    }

    public BookUserWaitingReservationBean(int id, int bookId, int libraryUserId, String waitReservationDate, BookBean book) {
        this.id = id;
        this.bookId = bookId;
        this.libraryUserId = libraryUserId;
        this.waitReservationDate = waitReservationDate;
        this.book = book;
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getLibraryUserId() {
        return libraryUserId;
    }

    public void setLibraryUserId(int libraryUserId) {
        this.libraryUserId = libraryUserId;
    }

    public String getWaitReservationDate() {
        return waitReservationDate;
    }

    public void setWaitReservationDate(String waitReservationDate) {
        this.waitReservationDate = waitReservationDate;
    }

    public BookBean getBook() {
        return book;
    }

    public void setBook(BookBean book) {
        this.book = book;
    }
}
