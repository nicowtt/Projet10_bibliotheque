package com.eLibraryClient.applicationWebClientbusiness.impl;

import com.eLibraryClient.applicationWebClientbusiness.Enums.CompareDate;
import com.eLibraryClient.applicationWebClientbusiness.contract.BookReservationManager;
import com.eLibraryClient.applicationWebClientbusiness.contract.BookUserWaitingReservationManager;
import com.eLibraryClient.applicationWebClientbusiness.contract.DateManager;
import com.eLibraryClient.applicationWebClientbusiness.exceptions.NotFoundException;
import com.eLibraryClient.applicationWebClientproxies.proxies.MicroserviceBDDProxy;
import com.eLibraryModel.beans.BookReservationBean;
import com.eLibraryModel.beans.BookUserWaitingReservationBean;
import com.eLibraryModel.beans.LibraryCatalogBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookUserWaitingReservationimpl implements BookUserWaitingReservationManager {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MicroserviceBDDProxy microserviceBDDProxy;
    @Autowired
    private BookReservationManager bookReservationManager;
    @Autowired
    private DateManager dateManager;

    /**
     * get book user waiting Reservation
     * @param bookId
     * @return
     */
    @Override
    public List<BookUserWaitingReservationBean> getBookUserWaitingReservation(int bookId) {
        List<BookUserWaitingReservationBean> bookUserWaitingReservationBean = microserviceBDDProxy.getUserWaitingReservationByBook(bookId);

        return bookUserWaitingReservationBean;
    }

    /**
     * get number of user waiting for Reservation
     * @param bookId
     * @return
     */
    @Override
    public int getNbrOfUserwaitingForReservation(int bookId) {
        List<BookUserWaitingReservationBean> bookUserWaitingReservationListToDisplay;
        Integer countUserWaiting = 0;

        //for display user waiting for reservation on this book
        bookUserWaitingReservationListToDisplay = this.getBookUserWaitingReservation(bookId);
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
    @Override
    public void changeBooleanWaitReservationFullIfNeeded(int bookId) throws Exception {
        int countReservationInProgress = 0;

        // check user waiting reservation in progres
        List<BookUserWaitingReservationBean> bookUserWaitingReservationBean =
                microserviceBDDProxy.getUserWaitingReservationByBook(bookId);
        for (BookUserWaitingReservationBean reservation: bookUserWaitingReservationBean) {
            countReservationInProgress++;
        }
        int reservationMax = this.getMaxOfWaitingReservation(bookId);
        if (countReservationInProgress < reservationMax) {
            // change boolean attribute waitReservationFull on Book
            this.changeWaitReservationDisponibility(bookId, false);
        } else {
            // change boolean attribute waitReservationFull on Book
            this.changeWaitReservationDisponibility(bookId, true);
        }
    }

    /**
     * get max number of user who can waiting reservation
     * @param bookId
     * @return
     */
    @Override
    public int getMaxOfWaitingReservation(int bookId) throws Exception {
        int waitReservation = 0;
        int maxWaitingReservation = 0;
        List<LibraryCatalogBean> libraryCatalogList = new ArrayList<>();

        libraryCatalogList = microserviceBDDProxy.getLibrariesCatalogForOneBook(bookId);
        if (libraryCatalogList != null) {
            // Rules1: Max Waiting user Reservation = 2 x number of book iteration
            for (int i = 0; i < libraryCatalogList.size(); i++) {
                waitReservation = libraryCatalogList.get(i).getBookIteration();
                maxWaitingReservation = maxWaitingReservation + (waitReservation * 2);
            }

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
    @Override
    public void changeWaitReservationDisponibility(int bookId, boolean booleanStatus) {
        microserviceBDDProxy.changeWaitReservationDisponibility(bookId, booleanStatus);
    }

    /**
     * Add book user waiting reservation bean
     * @param bookUserWaitingReservationBean
     * @return
     */
    @Override
    public void addBookUserWaitingReservation(BookUserWaitingReservationBean bookUserWaitingReservationBean) {
        microserviceBDDProxy.addWaitReservation(bookUserWaitingReservationBean);
    }

    /**
     * rules*
     * user can't add on wainting list of book if book is already reserved by himself.
     * @param userId
     * @param bookId
     * @return
     */
    @Override
    public boolean checkIfUserHaveReservationInProgressForConcernedBook(int userId, int bookId) {
        boolean bookAlreadyReserved = false;
        List<BookReservationBean> bookReservationListForOneUser = microserviceBDDProxy.getbookReservationForOneUserList(userId);
        List<BookUserWaitingReservationBean> bookUserWaitingReservationList = microserviceBDDProxy.getUserWaitingReservationByUser(userId);

        //check if bookId is on list for reservation
        for (BookReservationBean listOfReservation: bookReservationListForOneUser) {
            if (bookId == listOfReservation.getBookId()) {
                bookAlreadyReserved = true;
            }
        }
        //check if bookId is on wait reservation list
        for (BookUserWaitingReservationBean listOfWaitReservation: bookUserWaitingReservationList) {
            if (bookId == listOfWaitReservation.getBookId()) {
                bookAlreadyReserved = true;
            }
        }
        return bookAlreadyReserved;
    }

    /**
     * get book user waiting Reservation
     * @param userId
     * @return
     */
    @Override
    public List<BookUserWaitingReservationBean> getBookUserWaitingReservationById(int userId) {
        List<BookUserWaitingReservationBean> bookUserWaitingReservationBean = microserviceBDDProxy.getUserWaitingReservationByUser(userId);

        return bookUserWaitingReservationBean;
    }

    /**
     * For update book User Waiting reservation bean -> add closedDateBack
     * @param bookUserWaitingReservationToUpdate
     * @return
     */
    @Override
    public BookUserWaitingReservationBean updateBookUserWaitingReservationWithclosedDateFromToday(BookUserWaitingReservationBean bookUserWaitingReservationToUpdate) {

        String closedDateFromToday = bookReservationManager.getTheoricalEndReservationDateClosedThanToday(bookUserWaitingReservationToUpdate.getBookId());
        bookUserWaitingReservationToUpdate.setClosedDateBack(closedDateFromToday);
        //update BDD
        microserviceBDDProxy.updateWaitReservation(bookUserWaitingReservationToUpdate);

        return bookUserWaitingReservationToUpdate;
    }


    /**
     * update book User Waiting reservation bean -> add standOnWaitingList attribute
     * @param bookUserWaitingReservationToUpdate
     * @param pUserId
     * @return
     */
    @Override
    public BookUserWaitingReservationBean updateBookUserWaitingReservationWithStandOnWaitingList(BookUserWaitingReservationBean bookUserWaitingReservationToUpdate, int pUserId) {

        int standOnWaitingList = this.standOnWaitingList(bookUserWaitingReservationToUpdate.getBookId(), pUserId);
        bookUserWaitingReservationToUpdate.setStandOnWaitingList(standOnWaitingList);
        //update BDD
        microserviceBDDProxy.updateWaitReservation(bookUserWaitingReservationToUpdate);

        return bookUserWaitingReservationToUpdate;
    }

    /**
     * for find stand on waiting List
     * @param bookId
     * @param pUserId
     * @return
     */
    @Override
    public int standOnWaitingList(int bookId, int pUserId) {
        int standOnWaitingList = 1;
        String dateWaitingReservationForPuserId;
        List<BookUserWaitingReservationBean> bookUserWaitingReservationForPuserIdList = new ArrayList<>();
        CompareDate compareDateEnum = CompareDate.ISAFTER;

        // keep pUserId
        bookUserWaitingReservationForPuserIdList = microserviceBDDProxy.getUserWaitingReservationByUser(pUserId);
        if (bookUserWaitingReservationForPuserIdList != null) {
            dateWaitingReservationForPuserId = bookUserWaitingReservationForPuserIdList.get(0).getWaitReservationDate();
            // list of bookUserWaitingList for book concerned
            List<BookUserWaitingReservationBean> listForOneBook = microserviceBDDProxy.getUserWaitingReservationByBook(bookId);
            for (int i = 0; i < listForOneBook.size(); i++) {
                // comparaison des dates
                compareDateEnum = dateManager.compareTwoDate(listForOneBook.get(i).getWaitReservationDate(), dateWaitingReservationForPuserId);
                if (compareDateEnum == CompareDate.ISBEFORE) {
                    standOnWaitingList++;
                }
            }
        }
        return standOnWaitingList;
    }

    /**
     * get book user Waiting Reservation by waiting Reservation id
     * @param waitReservationId
     * @return
     */
    @Override
    public BookUserWaitingReservationBean getBookUserWaitingReservationByWaitReservationId(int waitReservationId) {
        BookUserWaitingReservationBean bookUserWaitingReservation = new BookUserWaitingReservationBean();
        bookUserWaitingReservation = microserviceBDDProxy.getUserWaitingReservationByWaitingReservationId(waitReservationId);
        return bookUserWaitingReservation;
    }

    /**
     * to delete a waiting reservation
     * @param bookUserWaitingReservationBean
     * @return
     */
    @Override
    public ResponseEntity<?> deleteBookUserWaitingreservation(BookUserWaitingReservationBean bookUserWaitingReservationBean) {
        ResponseEntity responseEntity = microserviceBDDProxy.deleteUserWaitingReservationByWaitingReservationId(bookUserWaitingReservationBean);
        return responseEntity;
    }

    /**
     * check if user(s) wait for book
     * @param bookId
     * @param userSessionId
     * @return
     */
    @Override
    public boolean checkIfUserWaitForBook(int bookId, int userSessionId) {
        boolean userWaitBook;
        List<BookUserWaitingReservationBean> bookUserWaitingReservationList =
                microserviceBDDProxy.getUserWaitingReservationByBook(bookId);
        if (bookUserWaitingReservationList.size() > 0) {
            //check si userInSession is the next in waiting list-> only this user can reserve book
            if (bookUserWaitingReservationList.get(0).getLibraryUserId() == userSessionId) {
                userWaitBook = false;
            } else {
                userWaitBook = true;
            }
        } else {
            userWaitBook = false;
        }
        return userWaitBook;
    }

    /**
     * delete book user waiting reservation if user in session make Reservation
     * @param bookId
     * @param userSessionId
     */
    @Override
    public void deleteBookUserWaitingReservationIfUserInSessionMakeReservation (int bookId, int userSessionId) {
        List<BookUserWaitingReservationBean> bookUserWaitingReservationList =
                microserviceBDDProxy.getUserWaitingReservationByBook(bookId);
        if (bookUserWaitingReservationList.size() != 0) {
            if (bookUserWaitingReservationList.get(0).getLibraryUserId() == userSessionId) {
                microserviceBDDProxy.deleteUserWaitingReservationByWaitingReservationId(bookUserWaitingReservationList.get(0));
            }
        }
    }
}
