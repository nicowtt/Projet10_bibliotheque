package com.eLibraryClient.applicationWebClientweb.controller;

import com.eLibraryClient.applicationWebClientbusiness.contract.BookReservationManager;
import com.eLibraryClient.applicationWebClientbusiness.contract.LibraryUserManager;
import com.eLibraryModel.beans.BookBean;
import com.eLibraryModel.beans.BookReservationBean;
import com.eLibraryModel.beans.LibraryUserBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
        List<BookReservationBean> bookReservationListForOneUser = bookReservationManager.bookReservationListForOneUser(userOnSession.getId());

        model.addAttribute("reservation", bookReservationListForOneUser);
        model.addAttribute("log", userSession);
        model.addAttribute("bookName", new BookBean());

        return "PersonalSpace";
    }

}
