package com.eLibraryClient.applicationWebClientbusiness.impl;

import com.eLibraryClient.applicationWebClientbusiness.contract.BookUserWaitingReservationManager;
import com.eLibraryClient.applicationWebClientproxies.proxies.MicroserviceBDDProxy;
import com.eLibraryModel.beans.BookUserWaitingReservationBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

}
