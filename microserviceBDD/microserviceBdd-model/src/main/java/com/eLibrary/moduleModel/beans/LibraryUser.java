package com.eLibrary.moduleModel.beans;

import javax.persistence.*;

@Entity
@Table(name = "libraryuser")
public class LibraryUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public.libraryuser_id_seq")
    @SequenceGenerator(name = "public.libraryuser_id_seq", sequenceName = "public.libraryuser_id_seq", allocationSize = 1)
    private int id;
    @Column(name = "userfirstname")
    private String userFirstName;

    @Column(name = "username")
    private String userName;

    @Column(name = "userpassword")
    private String userPassword;

    @Column(name = "useremail")
    private String userEmail;

    //constructor
    public LibraryUser() {
    }

    public LibraryUser(String userFirstName, String userName, String userPassword, String userEmail) {
        this.userFirstName = userFirstName;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
    }

    //getter and setter
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
}
