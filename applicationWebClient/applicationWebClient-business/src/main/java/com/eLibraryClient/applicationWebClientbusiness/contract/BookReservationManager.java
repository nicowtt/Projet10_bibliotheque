package com.eLibraryClient.applicationWebClientbusiness.contract;

import com.eLibraryModel.beans.BookReservationBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface BookReservationManager {
    void completeWithDate(BookReservationBean bookReservation);
    List<BookReservationBean> bookReservationInProgressList();
    int countReservationInProgressForOneBook(int bookId);
    int nbrBookReservationInProgressForOneLibraryAndOneBookList(int libraryId, int bookId);
    List<BookReservationBean> bookReservationListForOneUser(int userId);
    BookReservationBean getOneBookReservation(int reservationId);
    HttpStatus updateBookReservation(BookReservationBean bookReservationBean, int nbrOfDay);
    void bookBack(int reservationId);
    List<BookReservationBean> getBookReservationInProgressByBookId(int bookId);
    String getTheoricalEndReservationDateClosedThanToday(int bookId);
}
