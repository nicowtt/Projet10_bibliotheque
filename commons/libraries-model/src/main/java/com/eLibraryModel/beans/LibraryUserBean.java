package com.eLibraryModel.beans;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class LibraryUserBean {

    private int id;

    @NotBlank
    @Size(max=50)
    private String userFirstName;

    @NotBlank
    @Size(max=50)
    private String userName;

    @NotBlank
    @Size(min=2, max = 15, message = "La taille doit être comprise entre 2 et 15 caractères")
    private String userPassword;

    @NotBlank
    private String userEmail;

    private Set<BookReservationBean> bookReservations = new HashSet<>();

    // Constructor
    public LibraryUserBean() {
    }

    public LibraryUserBean(int id, @NotBlank @Size(max = 50) String userFirstName, @NotBlank @Size(max = 50) String userName, @NotBlank @Size(min = 2, max = 15) String userPassword, @NotBlank String userEmail, Set<BookReservationBean> bookReservations) {
        this.id = id;
        this.userFirstName = userFirstName;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.bookReservations = bookReservations;
    }

    //Getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Set<BookReservationBean> getBookReservations() {
        return bookReservations;
    }

    public void setBookReservations(Set<BookReservationBean> bookReservations) {
        this.bookReservations = bookReservations;
    }


    // toString


    @Override
    public String toString() {
        return "LibraryUserBean{" +
                "id=" + id +
                ", userFirstName='" + userFirstName + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", bookReservations=" + bookReservations +
                '}';
    }
}
