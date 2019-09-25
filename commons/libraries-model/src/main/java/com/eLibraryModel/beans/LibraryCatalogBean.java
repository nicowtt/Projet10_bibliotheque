package com.eLibraryModel.beans;

public class LibraryCatalogBean {

    private LibraryCatalogIdBean id;
    private LibraryBean library;
    private BookBean book;
    private int bookIteration;

    //constructor

    public LibraryCatalogBean() {
    }

    public LibraryCatalogBean(LibraryCatalogIdBean id, LibraryBean library, BookBean book, int bookIteration) {
        this.id = id;
        this.library = library;
        this.book = book;
        this.bookIteration = bookIteration;
    }

    //getters and setters
    public LibraryCatalogIdBean getId() {
        return id;
    }

    public void setId(LibraryCatalogIdBean id) {
        this.id = id;
    }

    public LibraryBean getLibrary() {
        return library;
    }

    public void setLibrary(LibraryBean library) {
        this.library = library;
    }

    public BookBean getBook() {
        return book;
    }

    public void setBook(BookBean book) {
        this.book = book;
    }

    public int getBookIteration() {
        return bookIteration;
    }

    public void setBookIteration(int bookIteration) {
        this.bookIteration = bookIteration;
    }
}
