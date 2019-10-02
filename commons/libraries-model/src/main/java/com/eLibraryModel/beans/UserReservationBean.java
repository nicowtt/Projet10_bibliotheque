package com.eLibraryModel.beans;


public class UserReservationBean {

    private int id;
    private boolean userReservationInProgress;
    private LibraryUserBean libraryUser;

    //Constructor
    public UserReservationBean() {
    }

    public UserReservationBean(int id, boolean userReservationInProgress, LibraryUserBean libraryUser) {
        this.id = id;
        this.userReservationInProgress = userReservationInProgress;
        this.libraryUser = libraryUser;
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isUserReservationInProgress() {
        return userReservationInProgress;
    }

    public void setUserReservationInProgress(boolean userReservationInProgress) {
        this.userReservationInProgress = userReservationInProgress;
    }

    public LibraryUserBean getLibraryUser() {
        return libraryUser;
    }

    public void setLibraryUser(LibraryUserBean libraryUser) {
        this.libraryUser = libraryUser;
    }
}
