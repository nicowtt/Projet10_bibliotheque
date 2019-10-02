package com.eLibraryModel.beans;

import java.awt.print.Book;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class BookUserWaitingReservationBean {

    private int id;
    private boolean anyReservation;
    private int bookId;
    private int userReservationId;
    private BookBean book;
    private Set<UserReservationBean> userReservation = new HashSet<>();

    //Constructor
    public BookUserWaitingReservationBean() {
    }

    public BookUserWaitingReservationBean(int id, boolean anyReservation, int bookId, int userReservationId, BookBean book, Set<UserReservationBean> userReservation) {
        this.id = id;
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

    public BookBean getBook() {
        return book;
    }

    public void setBook(BookBean book) {
        this.book = book;
    }

    public Set<UserReservationBean> getUserReservation() {
        return userReservation;
    }

    public void setUserReservation(Set<UserReservationBean> userReservation) {
        this.userReservation = userReservation;
    }
}
