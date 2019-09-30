package com.eLibrary.moduleModel.beans;

import javax.persistence.*;

@Entity
@Table(name = "userreservation")
public class UserReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public.userreservation_id_seq")
    @SequenceGenerator(name = "public.userreservation_id_seq", sequenceName = "public.userreservation_id_seq", allocationSize = 1)
    private int id;

    @Column(name = "userreservationinprogress")
    private boolean userReservationInProgress;

    @OneToOne //one libraryUser for one userReservation
    @JoinColumn(name = "user_id")
    private LibraryUser libraryUser;

    //Constructor
    public UserReservation() {
    }

    public UserReservation(boolean userReservationInProgress, LibraryUser libraryUser) {
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

    public LibraryUser getLibraryUser() {
        return libraryUser;
    }

    public void setLibraryUser(LibraryUser libraryUser) {
        this.libraryUser = libraryUser;
    }
}
