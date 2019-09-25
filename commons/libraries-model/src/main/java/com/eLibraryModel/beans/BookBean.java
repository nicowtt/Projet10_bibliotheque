package com.eLibraryModel.beans;

import java.util.HashSet;
import java.util.Set;

public class BookBean {

    private int id;
    private String bookName;
    private String bookAuthor;
    private String bookPictureUrl;
    private String bookDescription;
    private String bookLabel;
    private Boolean allBookReserved;

    // LibraryBean
    private Set<LibraryBean> libraries = new HashSet<>();

    //Constructor
    public BookBean() {
    }

    public BookBean(int id, String bookName, String bookAuthor, String bookPictureUrl, String bookDescription, String bookLabel, Boolean allBookReserved, Set<LibraryBean> libraries) {
        this.id = id;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookPictureUrl = bookPictureUrl;
        this.bookDescription = bookDescription;
        this.bookLabel = bookLabel;
        this.allBookReserved = allBookReserved;
        this.libraries = libraries;
    }

    //getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookPictureUrl() {
        return bookPictureUrl;
    }

    public void setBookPictureUrl(String bookPictureUrl) {
        this.bookPictureUrl = bookPictureUrl;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getBookLabel() {
        return bookLabel;
    }

    public void setBookLabel(String bookLabel) {
        this.bookLabel = bookLabel;
    }

    public Boolean getAllBookReserved() {
        return allBookReserved;
    }

    public void setAllBookReserved(Boolean allBookReserved) {
        this.allBookReserved = allBookReserved;
    }

    public Set<LibraryBean> getLibraries() {
        return libraries;
    }

    public void setLibraries(Set<LibraryBean> libraries) {
        this.libraries = libraries;
    }
}
