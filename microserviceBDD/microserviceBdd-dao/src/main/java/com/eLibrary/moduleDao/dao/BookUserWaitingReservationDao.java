package com.eLibrary.moduleDao.dao;

import com.eLibrary.moduleModel.beans.BookUserWaitingReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookUserWaitingReservationDao extends JpaRepository<BookUserWaitingReservation, Integer> {

    List<BookUserWaitingReservation> getBookUserWaitingReservationByBookId(int bookId);
    List<BookUserWaitingReservation> getBookUserWaitingReservationByLibraryUserId(int userId);
}
