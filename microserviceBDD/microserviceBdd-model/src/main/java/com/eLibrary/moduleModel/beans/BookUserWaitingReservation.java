package com.eLibrary.moduleModel.beans;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "bookuserwaitingreservation")
public class BookUserWaitingReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public.bookuserwaitingreservation_id_seq")
    @SequenceGenerator(name = "public.bookuserwaitingreservation_id_seq", sequenceName = "public.bookuserwaitingreservation_id_seq", allocationSize = 1)
    private int id;

    @Column(name = "anyreservation")
    private boolean anyReservation;

    @Column(name = "book_id")
    private int bookId;

    @Column(name = "userreservation_id")
    private int userReservationId;

    @ManyToOne //many bookUserWaitingReservation for one book
    @JoinColumn(name = "book_id", referencedColumnName = "id", insertable= false, updatable= false) //fk
    private Book book;

    @OneToMany // one bookUserWaitingReservation for many userReservation
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false) //fk
    private Collection<UserReservation> userReservation;

    //Constructor
    public BookUserWaitingReservation() {
    }

    public BookUserWaitingReservation(boolean anyReservation, int bookId, int userReservationId, Book book, Collection<UserReservation> userReservation) {
        this.anyReservation = anyReservation;
        this.bookId = bookId;
        this.userReservationId = userReservationId;
        this.book = book;
        this.userReservation = userReservation;
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAnyReservation() {
        return anyReservation;
    }

    public void setAnyReservation(boolean anyReservation) {
        this.anyReservation = anyReservation;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getUserReservationId() {
        return userReservationId;
    }

    public void setUserReservationId(int userReservationId) {
        this.userReservationId = userReservationId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Collection<UserReservation> getUserReservation() {
        return userReservation;
    }

    public void setUserReservation(Collection<UserReservation> userReservation) {
        this.userReservation = userReservation;
    }
}
