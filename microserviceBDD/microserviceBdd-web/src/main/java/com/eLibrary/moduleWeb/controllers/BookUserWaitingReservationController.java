package com.eLibrary.moduleWeb.controllers;

import com.eLibrary.moduleBusiness.contract.BookUserWaitingManager;
import com.eLibrary.moduleDao.dao.BookUserWaitingReservationDao;
import com.eLibrary.moduleModel.beans.BookReservation;
import com.eLibrary.moduleModel.beans.BookUserWaitingReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.security.provider.certpath.OCSPResponse;

import java.util.List;

@RestController
public class BookUserWaitingReservationController {

    @Autowired
    private BookUserWaitingReservationDao bookUserWaitingReservationDao;

    @Autowired
    private BookUserWaitingManager bookUserWaitingManager;

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
     * Get a list of waiting user for future reservation
     * @return
     */
    @GetMapping(value = "/UserWaitingReservation")
    public List<BookUserWaitingReservation> getBookUserWaitingReservation() {

        List<BookUserWaitingReservation> bookWaitingReservationList =
                bookUserWaitingReservationDao.findAll();

        return bookWaitingReservationList;
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

    /**
     * Get a list of waiting user for future reservation
     * @param waitingReservationId
     * @return
     */
    @GetMapping(value = "/UserWaitingReservationByWaitingReservationId/{waitingReservationId}")
    public BookUserWaitingReservation getBookUserWaitingReservationByWaitingReservationId(@PathVariable Integer waitingReservationId) {

        BookUserWaitingReservation bookUserWaitingReservationList =
                bookUserWaitingReservationDao.getBookUserWaitingReservationById(waitingReservationId);

        return bookUserWaitingReservationList;
    }

    /**
     * Delete a wait reservation and update stand of other user on waiting list
     * @param bookUserWaitingReservation
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/DeleteUserWaitingReservation", consumes="application/json")
    public ResponseEntity<?> deleteBookUserWaitingReservation(@RequestBody BookUserWaitingReservation bookUserWaitingReservation) {

        // update stand of other user
        bookUserWaitingManager.updateUsersStand(bookUserWaitingReservation.getBookId());
        // delete wait reservation
        bookUserWaitingReservationDao.delete(bookUserWaitingReservation);

        //send 201 Accepted for confirm new Reservation is deleted
        return (new ResponseEntity<>(HttpStatus.ACCEPTED));
    }

}
