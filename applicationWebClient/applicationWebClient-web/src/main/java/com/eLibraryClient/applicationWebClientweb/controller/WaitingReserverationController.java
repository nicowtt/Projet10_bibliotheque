package com.eLibraryClient.applicationWebClientweb.controller;

import com.eLibraryClient.applicationWebClientbusiness.contract.BookReservationManager;
import com.eLibraryClient.applicationWebClientbusiness.contract.BookUserWaitingReservationManager;
import com.eLibraryClient.applicationWebClientbusiness.contract.WaitingReservationRulesManager;
import com.eLibraryModel.beans.BookBean;
import com.eLibraryModel.beans.BookReservationBean;
import com.eLibraryModel.beans.BookUserWaitingReservationBean;
import com.eLibraryModel.beans.LibraryUserBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
public class WaitingReserverationController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BookReservationManager bookReservationManager;
    @Autowired
    private BookUserWaitingReservationManager bookUserWaitingReservationManager;
    @Autowired
    private WaitingReservationRulesManager waintingReservationRules;


    @RequestMapping(value = "/reservation/{bookId}")
    public String reservationForOneBook(Model model, @PathVariable Integer bookId,
                                        @SessionAttribute(value = "userSession", required = false)LibraryUserBean userSession) {
        BookReservationBean bookReservationToDisplay = new BookReservationBean();
        List<BookUserWaitingReservationBean> bookUserWaitingReservationListToDisplay;
        Integer countUserWaiting = 0;

        if (userSession != null) {
            //for display book reservation information
            List<BookReservationBean> bookReservationInProgressList = bookReservationManager.bookReservationInProgressList();
            for (BookReservationBean book: bookReservationInProgressList) {
                if (book.getBookId() == bookId) {bookReservationToDisplay = book;}
            }
            //for display user waiting for reservation on this book
            bookUserWaitingReservationListToDisplay = bookUserWaitingReservationManager.getBookUserWaitingReservation(bookId);
            //count user Waiting
            if (bookUserWaitingReservationListToDisplay != null) {
                for (int i = 0; i < bookUserWaitingReservationListToDisplay.size(); i++) {
                    countUserWaiting++;
                }
            }
            //check WaitingReservationRules
            // todo 1 check if waiting Reservation is possible (max = nbr of book * 2) -> create method on waitingReservationRulesManger -> business module

            model.addAttribute("waitReservation", bookReservationToDisplay);
            model.addAttribute("userWaitReservation", bookUserWaitingReservationListToDisplay);
            model.addAttribute("bookName", new BookBean());
            model.addAttribute("nbrUserWaiting", countUserWaiting);
            model.addAttribute("log", userSession);
        } else {
            model.addAttribute("bookName", new BookBean());
            return "errorHtml/errorMissAuth";
        }

        return "/UserReservation";
    }
}
