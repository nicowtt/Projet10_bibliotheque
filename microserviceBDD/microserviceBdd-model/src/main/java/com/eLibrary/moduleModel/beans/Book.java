package com.eLibrary.moduleModel.beans;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public.book_id_seq")
    @SequenceGenerator(name = "public.book_id_seq", sequenceName = "public.book_id_seq", allocationSize = 1)
    private int id;
    @Column(name="bookname")
    private String bookName;

    @Column(name="bookauthor")
    private String bookAuthor;

    @Column(name="bookpictureurl")
    private String bookPictureUrl;

    @Column(name="bookdescription")
    private String bookDescription;

    @Column(name="booklabel")
    private String bookLabel;

    @Column(name="allbookreserved")
    private Boolean allBookReserved;

    @ManyToMany(mappedBy = "books", fetch = FetchType.EAGER) //attribut Book books from library
    private Set<Library> libraries = new HashSet<>();

    //Constructor
    public Book() {
    }

    public Book(String bookName, String bookAuthor, String bookPictureUrl, String bookDescription, String bookLabel, Boolean allBookReserved, Set<Library> libraries) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookPictureUrl = bookPictureUrl;
        this.bookDescription = bookDescription;
        this.bookLabel = bookLabel;
        this.allBookReserved = allBookReserved;
        this.libraries = libraries;
    }

    //getters and setters
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

    public Set<Library> getLibraries() {
        return libraries;
    }

    public void setLibraries(Set<Library> libraries) {
        this.libraries = libraries;
    }
}
