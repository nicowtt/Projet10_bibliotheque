package com.eLibraryClient.applicationWebClientweb.controller;

import com.eLibraryClient.applicationWebClientbusiness.contract.BookReservationManager;
import com.eLibraryClient.applicationWebClientbusiness.contract.BookUserWaitingReservationManager;
import com.eLibraryClient.applicationWebClientbusiness.contract.LibraryUserManager;
import com.eLibraryModel.beans.BookBean;
import com.eLibraryModel.beans.BookReservationBean;
import com.eLibraryModel.beans.BookUserWaitingReservationBean;
import com.eLibraryModel.beans.LibraryUserBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import java.util.List;

@Controller
public class PersonalSpaceController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BookReservationManager bookReservationManager;
    @Autowired
    LibraryUserManager libraryUserManager;
    @Autowired
    BookUserWaitingReservationManager bookUserWaitingReservationManager;

    /**
     * display personalSpace
     * @param userSession
     * @param model
     * @return
     */
    @GetMapping(value = "/PersonalSpace")
    public String personalSpace(@SessionAttribute(value = "userSession", required = false)LibraryUserBean userSession,
                                Model model) {
        LibraryUserBean userOnSession = libraryUserManager.getOneUser(userSession.getUserEmail());
        //display list of reservation in progress
        List<BookReservationBean> bookReservationListForOneUser = bookReservationManager.bookReservationListForOneUser(userOnSession.getId());
        // todo update closedDateBack
        List<BookUserWaitingReservationBean> bookwaitingReservationForUserIsSession =
                bookUserWaitingReservationManager.getBookUserWaitingReservationById(userOnSession.getId());
        for (int i = 0; i < bookwaitingReservationForUserIsSession.size(); i++) {
            String closedDateback = bookReservationManager.getTheoricalEndReservationDateClosedThanToday(bookwaitingReservationForUserIsSession.get(i).getBookId());
            bookwaitingReservationForUserIsSession.get(i).setClosedDateBack(closedDateback);
            ResponseEntity responseEntity = bookUserWaitingReservationManager.updateBookUserWaitingReservation(bookwaitingReservationForUserIsSession.get(i));
            if (responseEntity.getStatusCodeValue() == 202) {
                logger.info("La date de retour la plus proche pour la reservation en liste d'attente d'ID: "
                        + bookwaitingReservationForUserIsSession.get(i).getId() + " à été mise à jour.");
            }
        }
        //display list of waiting reservation
        List<BookUserWaitingReservationBean> bookUserWaitingReservationList =
                bookUserWaitingReservationManager.getBookUserWaitingReservationById(userOnSession.getId());

        model.addAttribute("reservation", bookReservationListForOneUser);
        model.addAttribute("waitReservation", bookUserWaitingReservationList);
        model.addAttribute("log", userSession);
        model.addAttribute("bookName", new BookBean());

        return "PersonalSpace";
    }

}
