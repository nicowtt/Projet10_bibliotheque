package com.eLibraryModel.beans;

public class LibraryBean {

    private int id;
    private String libraryName;


    private BookReservationBean bookReservations;

    private BookBean books;

    //contructor
    public LibraryBean() {
    }

    public LibraryBean(int id, String libraryName, BookReservationBean bookReservations, BookBean books) {
        this.id = id;
        this.libraryName = libraryName;
        this.bookReservations = bookReservations;
        this.books = books;
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public BookReservationBean getBookReservations() {
        return bookReservations;
    }

    public void setBookReservations(BookReservationBean bookReservations) {
        this.bookReservations = bookReservations;
    }

    public BookBean getBooks() {
        return books;
    }

    public void setBooks(BookBean books) {
        this.books = books;
    }
}
