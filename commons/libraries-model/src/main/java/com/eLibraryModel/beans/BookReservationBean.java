package com.eLibraryModel.beans;

public class BookReservationBean {

    private int id;
    private String beginOfReservationDate;
    private String endOfReservationDate;
    private boolean extensionOfReservation;
    private boolean bookBack;
    private int userId;
    private int bookId;
    private int libraryId;

    //manytoOne relation
    private LibraryUserBean libraryUser;
    private BookBean book;
    private LibraryBean library;

    //constructor
    public BookReservationBean() {
    }

    public BookReservationBean(int id, String beginOfReservationDate, String endOfReservationDate, boolean extensionOfReservation, boolean bookBack, int userId, int bookId, int libraryId, LibraryUserBean libraryUser, BookBean book, LibraryBean library) {
        this.id = id;
        this.beginOfReservationDate = beginOfReservationDate;
        this.endOfReservationDate = endOfReservationDate;
        this.extensionOfReservation = extensionOfReservation;
        this.bookBack = bookBack;
        this.userId = userId;
        this.bookId = bookId;
        this.libraryId = libraryId;
        this.libraryUser = libraryUser;
        this.book = book;
        this.library = library;
    }

    //getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBeginOfReservationDate() {
        return beginOfReservationDate;
    }

    public void setBeginOfReservationDate(String beginOfReservationDate) {
        this.beginOfReservationDate = beginOfReservationDate;
    }

    public String getEndOfReservationDate() {
        return endOfReservationDate;
    }

    public void setEndOfReservationDate(String endOfReservationDate) {
        this.endOfReservationDate = endOfReservationDate;
    }

    public boolean isExtensionOfReservation() {
        return extensionOfReservation;
    }

    public void setExtensionOfReservation(boolean extensionOfReservation) {
        this.extensionOfReservation = extensionOfReservation;
    }

    public boolean isBookBack() {
        return bookBack;
    }

    public void setBookBack(boolean bookBack) {
        this.bookBack = bookBack;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(int libraryId) {
        this.libraryId = libraryId;
    }

    public LibraryUserBean getLibraryUser() {
        return libraryUser;
    }

    public void setLibraryUser(LibraryUserBean libraryUser) {
        this.libraryUser = libraryUser;
    }

    public BookBean getBook() {
        return book;
    }

    public void setBook(BookBean book) {
        this.book = book;
    }

    public LibraryBean getLibrary() {
        return library;
    }

    public void setLibrary(LibraryBean library) {
        this.library = library;
    }
}
