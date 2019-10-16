package com.eLibraryBatch.batchMailproxies;


import com.eLibraryModel.beans.BookReservationBean;
import com.eLibraryModel.beans.BookUserWaitingReservationBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
@FeignClient(name = "microserviceBdd", url = "http://localhost:9001")
public interface MicroserviceBDDProxy {

    //*******************************************//
    //************ Book Reservation *************//
    //*******************************************//

    /**
     * Get all late book reservation
     */
    @GetMapping(value = "/BookReservationLate")
    List<BookReservationBean> getBookReservationLate();

    /**
     * get all book reservation ended today
     * @return
     */
    @GetMapping(value = "/BookReservationEndedForToday")
    List<BookReservationBean> getBookreservationEndedToday();

    //*******************************************//
    //************ Wait book reservation ********//
    //*******************************************//

    /**
     * get wait reservation by book Id
     * @param bookId
     * @return
     */
    @GetMapping(value = "/UserWaitingReservationByBookId/{bookId}")
    List<BookUserWaitingReservationBean> getWaitReservationByBookId(@PathVariable("bookId") Integer bookId);

    /**
     * Get a list of waiting user for future reservation
     * @return
     */
    @GetMapping(value = "/UserWaitingReservation")
    List<BookUserWaitingReservationBean> getBookUserWaitingReservation();

    /**
     * update new wait reservation on microserviceBDD
     * @param bookUserWaitingReservationBean
     * @return
     */
    @PostMapping(value = "/UpdateWaitingReservation")
    BookUserWaitingReservationBean updateWaitReservation(@RequestBody BookUserWaitingReservationBean bookUserWaitingReservationBean);

    /**
     * To delete one Waiting Reservation
     * @param bookUserWaitingReservationBean
     * @return
     */
    @PostMapping(value = "/DeleteUserWaitingReservation")
    ResponseEntity<?> deleteUserWaitingReservation(@RequestBody BookUserWaitingReservationBean bookUserWaitingReservationBean);

    //*******************************************//
    //************ user *************************//
    //*******************************************//

    /**
     * get user Email with UserId
     * @param userId
     * @return
     */
    @GetMapping(value = "/userMail/{userId}")
    String getUserEmailByUserId(@PathVariable("userId") Integer userId);
}
