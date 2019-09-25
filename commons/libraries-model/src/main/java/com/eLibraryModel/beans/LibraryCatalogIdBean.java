package com.eLibraryModel.beans;

public class LibraryCatalogIdBean {

    private int libraryId;
    private int bookId;
    private int bookIteration;

    //constructor

    public LibraryCatalogIdBean() {
    }

    public LibraryCatalogIdBean(int libraryId, int bookId, int bookIteration) {
        this.libraryId = libraryId;
        this.bookId = bookId;
        this.bookIteration = bookIteration;
    }

    //getters and setters
    public int getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(int libraryId) {
        this.libraryId = libraryId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getBookIteration() {
        return bookIteration;
    }

    public void setBookIteration(int bookIteration) {
        this.bookIteration = bookIteration;
    }
}
