package com.eLibraryClient.applicationWebClientbusiness.impl;

import com.eLibraryClient.applicationWebClientbusiness.contract.BookUserWaitingReservationManager;
import com.eLibraryClient.applicationWebClientbusiness.exceptions.NotFoundException;
import com.eLibraryClient.applicationWebClientproxies.proxies.MicroserviceBDDProxy;
import com.eLibraryModel.beans.BookUserWaitingReservationBean;
import com.eLibraryModel.beans.LibraryCatalogBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookUserWaitingReservationimpl implements BookUserWaitingReservationManager {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MicroserviceBDDProxy microserviceBDDProxy;
    @Autowired
    private BookUserWaitingReservationManager bookUserWaitingReservationManager;

    /**
     * get book user waiting Reservation
     * @param bookId
     * @return
     */
    public List<BookUserWaitingReservationBean> getBookUserWaitingReservation(int bookId) {
        List<BookUserWaitingReservationBean> bookUserWaitingReservationBean = microserviceBDDProxy.getUserWaitingReservation(bookId);

        return bookUserWaitingReservationBean;
    }

    /**
     * get number of user waiting for Reservation
     * @param bookId
     * @return
     */
    public int getNbrOfUserwaitingForReservation(int bookId) {
        List<BookUserWaitingReservationBean> bookUserWaitingReservationListToDisplay;
        Integer countUserWaiting = 0;

        //for display user waiting for reservation on this book
        bookUserWaitingReservationListToDisplay = bookUserWaitingReservationManager.getBookUserWaitingReservation(bookId);
        //count user Waiting
        if (bookUserWaitingReservationListToDisplay != null) {
            for (int i = 0; i < bookUserWaitingReservationListToDisplay.size(); i++) {
                countUserWaiting++;
            }
        }
        return countUserWaiting;
    }

    /**
     * Rules *
     * User can't add himself to user waiting list if waiting list is already full
     * @param bookId
     * @return
     */
    public void changeBooleanWaitReservationFullIfNeeded(int bookId) throws Exception {
        List<BookUserWaitingReservationBean> bookUserWaitingReservationBean = new ArrayList<>();
        int reservationMax = 0;
        int countReservationInProgress = 0;
        boolean isValid = false;

        // check user waiting reservation in progres
        bookUserWaitingReservationBean = microserviceBDDProxy.getUserWaitingReservation(bookId);
        for (BookUserWaitingReservationBean reservation: bookUserWaitingReservationBean) {
            countReservationInProgress++;
        }
        // check
        reservationMax = this.getMaxOfWaitingReservation(bookId);
        if (countReservationInProgress < reservationMax) {
        } else {
            // change boolean waitReservationFull on Book
            isValid = true;
            this.changeWaitReservationDisponibility(bookId, isValid);
        }
    }

    /**
     * get max number of user who can waiting reservation
     * @param bookId
     * @return
     */
    public int getMaxOfWaitingReservation(int bookId) throws Exception {
        int maxWaitingReservation = 0;
        List<LibraryCatalogBean> libraryCatalogBean = new ArrayList<>();

        libraryCatalogBean = microserviceBDDProxy.getLibrariesCatalogForOneBook(bookId);
        if (libraryCatalogBean != null) {
            // Rules1: Max Waiting user Reservation = 2 x number of book iteration
            maxWaitingReservation = libraryCatalogBean.get(0).getBookIteration() * 2;
        } else {
            throw new NotFoundException("Impossible de trouver ce livre");
        }
        return maxWaitingReservation;
    }

    /**
     * For change waiting Reservation disponibility
     * @param bookId
     * @param booleanStatus
     */
    public void changeWaitReservationDisponibility(int bookId, boolean booleanStatus) {
        microserviceBDDProxy.changeWaitReservationDisponibility(bookId, booleanStatus);
    }

    /**
     * Add book user waiting reservation bean
     * @param bookUserWaitingReservationBean
     * @return
     */
    public void addBookUserWaitingReservation(BookUserWaitingReservationBean bookUserWaitingReservationBean) {
        microserviceBDDProxy.addWaitReservation(bookUserWaitingReservationBean);
    }

}
