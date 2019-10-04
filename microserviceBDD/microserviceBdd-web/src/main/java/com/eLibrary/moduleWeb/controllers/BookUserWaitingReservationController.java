package com.eLibrary.moduleWeb.controllers;

import com.eLibrary.moduleDao.dao.BookUserWaitingReservationDao;
import com.eLibrary.moduleModel.beans.BookReservation;
import com.eLibrary.moduleModel.beans.BookUserWaitingReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping(value = "/UserWaitingReservationByBookId/{bookId}")
    public List<BookUserWaitingReservation> getBookUserWaitingReservationByBookId(@PathVariable Integer bookId) {

        List<BookUserWaitingReservation> bookUserWaitingReservationList =
                bookUserWaitingReservationDao.getBookUserWaitingReservationByBookId(bookId);

        return bookUserWaitingReservationList;
    }

    /**
     * Write new waiting reservation
     * @param bookUserWaitingReservation
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/NewWaitingReservation", consumes="application/json")
    public ResponseEntity<BookUserWaitingReservation> addWaitReservation(@RequestBody BookUserWaitingReservation bookUserWaitingReservation) {

        //save newBookUserWaitingReservation
        BookUserWaitingReservation newBookUserWaitingReservation = bookUserWaitingReservationDao.save(bookUserWaitingReservation);

        //send 201 CREATED for confirm new reservation is saved
        return new ResponseEntity<BookUserWaitingReservation>(newBookUserWaitingReservation, HttpStatus.CREATED);
    }

    /**
     * Get a list of waiting user for future reservation
     * @param userId
     * @return
     */
    @GetMapping(value = "/UserWaitingReservationbyUserId/{userId}")
    public List<BookUserWaitingReservation> getBookUserWaitingReservationByUser(@PathVariable Integer userId) {

        List<BookUserWaitingReservation> bookUserWaitingReservationList =
                bookUserWaitingReservationDao.getBookUserWaitingReservationByLibraryUserId(userId);

        return bookUserWaitingReservationList;
    }

    /**
     * Write new waiting reservation
     * @param bookUserWaitingReservation
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/UpdateWaitingReservation", consumes="application/json")
    public ResponseEntity<BookUserWaitingReservation> updateWaitReservation(@RequestBody BookUserWaitingReservation bookUserWaitingReservation) {

        //update newBookUserWaitingReservation
        BookUserWaitingReservation bookUserWaitingReservationToUpdate = bookUserWaitingReservationDao.save(bookUserWaitingReservation);

        //send 201 CREATED for confirm new reservation is saved
        return new ResponseEntity<BookUserWaitingReservation>(bookUserWaitingReservationToUpdate, HttpStatus.ACCEPTED);
    }

}
