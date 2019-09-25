package com.eLibrary.moduleModel.beans;

import javax.persistence.*;

@Entity
@Table(name = "librarycatalog")
public class LibraryCatalog {

    @EmbeddedId
    private LibraryCatalogId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("libraryId")
    private Library library;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("bookId")
    private Book book;

    @Column(name = "bookiteration",updatable = false, insertable = false)
    private int bookIteration;

    //contructor
    public LibraryCatalog() {
    }

    public LibraryCatalog(LibraryCatalogId id, Library library, Book book, int bookIteration) {
        this.id = id;
        this.library = library;
        this.book = book;
        this.bookIteration = bookIteration;
    }

    //getter and setters
    public LibraryCatalogId getId() {
        return id;
    }

    public void setId(LibraryCatalogId id) {
        this.id = id;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getBookIteration() {
        return bookIteration;
    }

    public void setBookIteration(int bookIteration) {
        this.bookIteration = bookIteration;
    }
}


