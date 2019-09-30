package com.eLibrary.moduleWeb.controllers;

import com.eLibrary.moduleDao.dao.BookUserWaitingReservationDao;
import com.eLibrary.moduleModel.beans.BookUserWaitingReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookUserWaitingReservationController {

    @Autowired
    private BookUserWaitingReservationDao bookUserWaitingReservationDao;

    /**
     * Get a list of waiting user for future reservation
     * @param bookId
     * @return
     */
    @GetMapping(value = "/UserWaitingReservation/{bookId}")
    public List<BookUserWaitingReservation> getBookUserWaitingReservation(@PathVariable Integer bookId) {

        List<BookUserWaitingReservation> bookUserWaitingReservationList =
                bookUserWaitingReservationDao.getBookUserWaitingReservationByBookId(bookId);

        return bookUserWaitingReservationList;
    }
}
