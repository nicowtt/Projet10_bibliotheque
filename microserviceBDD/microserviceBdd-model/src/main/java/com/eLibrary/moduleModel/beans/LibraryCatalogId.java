package com.eLibrary.moduleModel.beans;

import javax.persistence.*;
import java.io.Serializable;


@Embeddable
public class LibraryCatalogId implements Serializable {

    @Column(name = "library_id")
    private int libraryId;

    @Column(name = "book_id")
    private int bookId;

    @Column(name = "bookiteration")
    private int bookIteration;

    //contructor
    public LibraryCatalogId() {
    }

    public LibraryCatalogId(int libraryId, int bookId, int bookIteration) {
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
