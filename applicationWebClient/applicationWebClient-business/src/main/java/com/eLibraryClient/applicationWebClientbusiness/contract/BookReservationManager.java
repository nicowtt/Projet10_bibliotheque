package com.eLibraryClient.applicationWebClientbusiness.contract;

import com.eLibraryModel.beans.BookReservationBean;
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
    void updateBookReservation(BookReservationBean bookReservationBean);
    void bookBack(int reservationId);
}
