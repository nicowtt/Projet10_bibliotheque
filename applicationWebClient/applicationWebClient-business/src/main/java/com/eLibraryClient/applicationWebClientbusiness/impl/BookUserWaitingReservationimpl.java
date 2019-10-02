package com.eLibraryClient.applicationWebClientbusiness.impl;

import com.eLibraryClient.applicationWebClientbusiness.contract.BookUserWaitingReservationManager;
import com.eLibraryClient.applicationWebClientproxies.proxies.MicroserviceBDDProxy;
import com.eLibraryModel.beans.BookUserWaitingReservationBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookUserWaitingReservationimpl implements BookUserWaitingReservationManager {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MicroserviceBDDProxy microserviceBDDProxy;

    public List<BookUserWaitingReservationBean> getBookUserWaitingReservation(int bookId) {
        List<BookUserWaitingReservationBean> bookUserWaitingReservationBean = microserviceBDDProxy.getUserWaitingReservation(bookId);

        return bookUserWaitingReservationBean;
    }

}
