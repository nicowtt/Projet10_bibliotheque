package com.eLibrary.moduleModel.beans;


import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "library")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public.library_id_seq")
    @SequenceGenerator(name = "public.library_id_seq", sequenceName = "public.library_id_seq", allocationSize = 1)
    private int id;
    @Column(name = "libraryname")
    private String libraryName;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "librarycatalog",
            joinColumns = @JoinColumn(name = "library_id"),
            inverseJoinColumns = @JoinColumn(name ="book_id"))
    private Set<Book> books;

    //contructor

    public Library() {
    }

    public Library(String libraryName, Set<LibraryCatalog> libraryCatalogList, Set<Book> books) {
        this.libraryName = libraryName;
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

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
