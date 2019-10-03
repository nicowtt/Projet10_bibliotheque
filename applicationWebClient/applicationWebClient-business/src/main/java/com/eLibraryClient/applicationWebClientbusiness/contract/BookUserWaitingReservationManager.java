package com.eLibraryClient.applicationWebClientbusiness.contract;

import com.eLibraryModel.beans.BookUserWaitingReservationBean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookUserWaitingReservationManager {

    List<BookUserWaitingReservationBean> getBookUserWaitingReservation(int bookId);
    int getNbrOfUserwaitingForReservation(int bookId);
    void changeBooleanWaitReservationFullIfNeeded(int bookId) throws Exception;
    int getMaxOfWaitingReservation(int bookId) throws Exception;
    void changeWaitReservationDisponibility(int bookId, boolean booleanStatus);
    void addBookUserWaitingReservation(BookUserWaitingReservationBean bookUserWaitingReservationBean);
}
