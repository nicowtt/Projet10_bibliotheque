package com.eLibraryBatch.batchMailproxies;


import com.eLibraryModel.beans.BookReservationBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

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

}
