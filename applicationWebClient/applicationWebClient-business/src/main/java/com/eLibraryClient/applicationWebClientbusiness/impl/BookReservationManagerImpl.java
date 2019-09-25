package com.eLibraryClient.applicationWebClientbusiness.impl;

import com.eLibraryClient.applicationWebClientbusiness.Enums.CompareDate;
import com.eLibraryClient.applicationWebClientbusiness.contract.BookReservationManager;
import com.eLibraryClient.applicationWebClientbusiness.contract.DateManager;
import com.eLibraryModel.beans.BookReservationBean;
import com.eLibraryClient.applicationWebClientproxies.proxies.MicroserviceBDDProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.eLibraryClient.applicationWebClientbusiness.Enums.CompareDate.ISAFTER;
import static com.eLibraryClient.applicationWebClientbusiness.Enums.CompareDate.ISBEFORE;
import static com.eLibraryClient.applicationWebClientbusiness.Enums.CompareDate.ISTODAY;

@Component
public class BookReservationManagerImpl implements BookReservationManager {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MicroserviceBDDProxy microserviceBDDProxy;
    @Autowired
    private DateManager dateManager;

    /**
     * For complete date of reservation
     * @param bookReservation
     */
    @Override
    public void completeWithDate(BookReservationBean bookReservation) {
        String todayDate = dateManager.todayDate();
        String endOfReservationDate = dateManager.addDaysOnTodayDate(28);
        bookReservation.setBeginOfReservationDate(todayDate);
        bookReservation.setEndOfReservationDate(endOfReservationDate);
        bookReservation.setExtensionOfReservation(false);
        microserviceBDDProxy.addReservation(bookReservation);
    }

    /**
     * Get a list of reservation in progress
     * @return
     */
    @Override
    public List<BookReservationBean> bookReservationInProgressList() {
        CompareDate beginCompareEnum = ISBEFORE;
        List<BookReservationBean> allReservations = microserviceBDDProxy.getAllReservation();
        List<BookReservationBean> reservationInProgress = new ArrayList<>();

        for (int i = 0; i < allReservations.size(); i++) {
            String beginOfReservationDate = allReservations.get(i).getBeginOfReservationDate();
            beginCompareEnum = dateManager.compareDateWithToday(beginOfReservationDate);

            if ((beginCompareEnum == ISTODAY || beginCompareEnum == ISAFTER)
                    && !allReservations.get(i).isBookBack()) {
                reservationInProgress.add(allReservations.get(i));
            }
        }
        return reservationInProgress;

    }

    /**
     * Count Reservation in progress for one book
     * @param bookId
     * @return
     */
    @Override
    public int countReservationInProgressForOneBook(int bookId) {
        int bookReservationInProgress = 0;
        List<BookReservationBean> reservationInProgress = bookReservationInProgressList();

        for (int i = 0; i < reservationInProgress.size(); i++) {
            if (reservationInProgress.get(i).getBookId() == bookId) {
                bookReservationInProgress++;
            }
        }
        return bookReservationInProgress;
    }

    /**
     * Get a number of reservation in progress on a library and for one book
     * @return
     */
    @Override
    public int nbrBookReservationInProgressForOneLibraryAndOneBookList(int libraryId, int bookId) {
        int count = 0;
        List<BookReservationBean> bookReservationInProgress = bookReservationInProgressList();
        List<BookReservationBean> bookReservationListKeep = new ArrayList<>();

        for (int i = 0; i < bookReservationInProgress.size(); i++) {
            if (bookReservationInProgress.get(i).getBookId() == bookId && bookReservationInProgress.get(i).getLibraryId() == libraryId) {
                bookReservationListKeep.add(bookReservationInProgress.get(i));
            }
        }
        for (int j = 0; j < bookReservationListKeep.size(); j++) {
            count++;
        }
        return count;
    }

    /**
     * get all reservations list for one user
     * @param userId
     * @return
     */
    @Override
    public List<BookReservationBean> bookReservationListForOneUser(int userId) {
        List<BookReservationBean> bookReservationListForOneUser =
                microserviceBDDProxy.getbookReservationForOneUserList(userId);
        return bookReservationListForOneUser;
    }

    /**
     * get one Book Reservation
     * @param reservationId
     * @return
     */
    @Override
    public BookReservationBean getOneBookReservation(int reservationId) {
        BookReservationBean oneBookReservation =
                microserviceBDDProxy.getOneBookReservation(reservationId);
        return oneBookReservation;
    }

    /**
     * For update a reservation (exemple: extend endtime)
     * @param bookReservationBean
     */
    @Override
    public void updateBookReservation(BookReservationBean bookReservationBean) {
        microserviceBDDProxy.updateReservation(bookReservationBean);
    }

    /**
     * For reservation book back (when user bring back book)
     * @param reservationId
     */
    @Override
    public void bookBack(int reservationId) {
        microserviceBDDProxy.bookBack(reservationId);
    }

}
