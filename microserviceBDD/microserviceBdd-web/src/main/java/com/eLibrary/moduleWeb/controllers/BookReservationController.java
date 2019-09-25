package com.eLibrary.moduleWeb.controllers;

import com.eLibrary.moduleBusiness.contract.BookReservationManager;
import com.eLibrary.moduleDao.dao.BookReservationDao;
import com.eLibrary.moduleModel.beans.BookReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookReservationController {

    @Autowired
    private BookReservationDao bookReservationDao;
    @Autowired
    private BookReservationManager bookReservationManager;

    /**
     * get list all reservation
     * @return
     */
    @GetMapping(value = "/BookReservation")
    public List<BookReservation> getListReservation() {

        List<BookReservation> bookReservationList = bookReservationDao.findAll();

        return bookReservationList;
    }

    /**
     * get list all reservation for one user
     * @return
     */
    @GetMapping(value = "/UserBookReservation/{userId}")
    public List<BookReservation> getListReservationForOneUser(@PathVariable Integer userId) {

        List<BookReservation> bookReservationList = bookReservationDao.getBookReservationsByUserId(userId);

        return bookReservationList;
    }


    /**
     * Write new reservation
     * @param bookReservation
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/NewBookReservation", consumes="application/json")
    public ResponseEntity<BookReservation> addBookReservation(@RequestBody BookReservation bookReservation) {

        //save newBookReservation
        BookReservation newBookReservation = bookReservationDao.save(bookReservation);

        //send 201 CREATED for confirm new reservation is saved
        return new ResponseEntity<BookReservation>(newBookReservation, HttpStatus.CREATED);
    }

    /**
     * get One Book reservation
     * @param reservationId
     * @return
     */
    @GetMapping(value = "/OneBookReservation/{reservationId}")
    public BookReservation getOneBookReservation(@PathVariable Integer reservationId) {

        BookReservation oneBookReservation = bookReservationDao.getOne(reservationId);

        return oneBookReservation;
    }

    /**
     * For update Book Reservation ( extend endtime and one boolean for avoid another extend to true)
     * @param bookReservation
     */
    @RequestMapping(method = RequestMethod.POST, value = "/UpdateBookReservation", consumes = "application/json")
    public void updateBookReservation(@RequestBody BookReservation bookReservation) {

        bookReservationDao.save(bookReservation);

    }

    /**
     * For change bookback boolean to true (reservation is end)
     * @param reservationId
     */
    @GetMapping(value = "/BookBack/{reservationId}")
    public void bookBack(@PathVariable Integer reservationId) {

        bookReservationDao.changeBookBackToTrueForOneReservation(reservationId);
    }

    /**
     * Get book reservation late
     * @return
     */
    @GetMapping(value = "/BookReservationLate")
    public List<BookReservation> bookReservationLate() {

        List<BookReservation> bookReservationListLate = bookReservationManager.getBookReservationLate();

        return bookReservationListLate;
    }


}
