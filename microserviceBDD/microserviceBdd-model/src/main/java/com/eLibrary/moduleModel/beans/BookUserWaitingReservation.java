package com.eLibrary.moduleModel.beans;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "bookuserwaitingreservation")
public class BookUserWaitingReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public.bookuserwaitingreservation_id_seq")
    @SequenceGenerator(name = "public.bookuserwaitingreservation_id_seq", sequenceName = "public.bookuserwaitingreservation_id_seq", allocationSize = 1)
    private int id;

    @Column(name = "book_id")
    private int bookId;

    @Column(name = "libraryuser_id")
    private int libraryUserId;

    @Column(name = "waitreservationdate")
    private String waitReservationDate;

    @ManyToOne //many bookUserWaitingReservation for one book
    @JoinColumn(name = "book_id", referencedColumnName = "id", insertable= false, updatable= false) //fk
    private Book book;

    //Constructor
    public BookUserWaitingReservation() {
    }

    public BookUserWaitingReservation(int bookId, int libraryUserId, String waitReservationDate, Book book) {
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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
