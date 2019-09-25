package com.eLibrary.moduleDao.dao;

import com.eLibrary.moduleModel.beans.BookReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public interface BookReservationDao extends JpaRepository<BookReservation, Integer> {

    List<BookReservation> getBookReservationsByUserId(int userId);

    @Modifying
    @Query(value = "UPDATE bookreservation  SET bookback = true WHERE id = ?1", nativeQuery = true)
    void changeBookBackToTrueForOneReservation(int reservationId);

    List<BookReservation> getBookReservationByBookBackEquals(boolean booleanStatus);

}
