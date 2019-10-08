package com.eLibraryClient.applicationWebClientbusiness.contract;

import com.eLibraryModel.beans.BookUserWaitingReservationBean;
import org.springframework.http.ResponseEntity;
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
    boolean checkIfUserHaveReservationInProgressForConcernedBook(int userId, int bookId);
    List<BookUserWaitingReservationBean> getBookUserWaitingReservationById(int userId);
    BookUserWaitingReservationBean updateBookUserWaitingReservationWithclosedDateFromToday(BookUserWaitingReservationBean bookUserWaitingReservationToUpdate);
    int standOnWaitingList(int bookId, int pUserId);
    BookUserWaitingReservationBean updateBookUserWaitingReservationWithStandOnWaitingList(BookUserWaitingReservationBean bookUserWaitingReservationToUpdate, int pUserId);
    BookUserWaitingReservationBean getBookUserWaitingReservationByWaitReservationId(int waitReservationId);
    ResponseEntity<?> deleteBookUserWaitingreservation(BookUserWaitingReservationBean bookUserWaitingReservationBean);
    boolean checkIfUserWaitForBook(int bookId, int userSessionId);
    void deleteBookUserWaitingReservationIfUserInSessionMakeReservation (int bookId, int userSessionId);
    }
