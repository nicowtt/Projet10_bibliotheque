package com.eLibrary.moduleWeb.controllers;

import com.eLibrary.moduleBusiness.contract.BookReservationManager;
import com.eLibrary.moduleBusiness.contract.DateManager;
import com.eLibrary.moduleBusiness.enums.ComparisonDate;
import com.eLibrary.moduleDao.dao.BookReservationDao;
import com.eLibrary.moduleModel.beans.BookReservation;
import com.eLibrary.moduleModel.beans.BookUserWaitingReservation;
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
    @Autowired
    private DateManager dateManager;

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
     * For update bookReservation : attribute -> endOfReservationDate.
     * @param bookReservation -> bean to update
     * @param nbrOfDay -> nbr of to add on endOfReservationDate
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/UpdateBookReservation/{nbrOfDay}", consumes = "application/json")
    public HttpStatus updateBookReservation(@RequestBody BookReservation bookReservation,
                                                                 @PathVariable Integer nbrOfDay) {
        ComparisonDate comparisonDate = dateManager.compareDateWithToday(bookReservation.getEndOfReservationDate());
        if (comparisonDate == ComparisonDate.ISBEFORE) {
            String extendDate = dateManager.addDaysOnOneDate(bookReservation.getEndOfReservationDate(), nbrOfDay);
            bookReservation.setExtensionOfReservation(true);
            bookReservation.setEndOfReservationDate(extendDate);
            bookReservationDao.save(bookReservation);
            return HttpStatus.CREATED;
          } else {
            return HttpStatus.FORBIDDEN;
        }
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

    /**
     * get book reservation in progress by bookId in progress
     * @param bookId
     * @return
     */
    @GetMapping(value = "/BookReservationInProgressByBookId/{bookId}")
    public List<BookReservation> bookReservationInProgressByBookId(@PathVariable Integer bookId) {

        List<BookReservation> bookReservationList = bookReservationDao.getBookReservationByBookIdAndBookBackEquals(bookId, false);

        return bookReservationList;
    }

    /**
     * get book reservation ended by bookId
     * @param bookId
     * @return
     */
    @GetMapping(value = "/BookReservationEndedByBookId/{bookId}")
    public List<BookReservation> bookReservationEndedByBookId(@PathVariable Integer bookId) {

        List<BookReservation> bookReservationList = bookReservationDao.getBookReservationByBookIdAndBookBackEquals(bookId, true);

        return bookReservationList;
    }

    /**
     * get book Reservation ended for today
     * @return
     */
    @GetMapping(value = "/BookReservationEndedForToday")
    public List<BookReservation>  bookReservationEndedForToday() {
        String todayDate = dateManager.todayDate();
        String todaydateForSqlRequest = todayDate.substring(0,10) + "%";
        List<BookReservation>bookReservationList = bookReservationDao.getEndBookReservationForToday(true, todaydateForSqlRequest);

        return bookReservationList;
    }
}
