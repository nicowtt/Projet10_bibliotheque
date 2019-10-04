package com.eLibraryClient.applicationWebClientweb.controller;

import com.eLibraryClient.applicationWebClientbusiness.contract.*;
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
    private BookManager bookManager;
    @Autowired
    private LibraryUserManager libraryUserManager;
    @Autowired
    private DateManager dateManager;


    /**
     * For display information about book for add user on waiting list
     * @param model
     * @param bookId
     * @param userSession
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/waitingReservation/{bookId}")
    public String waitingReservationForOneBook(Model model, @PathVariable Integer bookId,
                                        @SessionAttribute(value = "userSession", required = false)LibraryUserBean userSession) throws Exception {
        BookReservationBean bookReservationToDisplay = new BookReservationBean();
        BookBean bookToDisplay;
        Integer countUserWaiting;

        if (userSession != null) {
            //check if waiting reservation list is full
            bookToDisplay = bookManager.getOneBook(bookId);
            if (bookToDisplay.getWaitReservationFull()) {
                model.addAttribute("log", userSession);
                model.addAttribute("bookName", new BookBean());
                return "errorHtml/errorWaitingReservationFull";
            } else {
                //for display book reservation information
                List<BookReservationBean> bookReservationInProgressList = bookReservationManager.bookReservationInProgressList();
                for (BookReservationBean book: bookReservationInProgressList) {
                    if (book.getBookId() == bookId) {bookReservationToDisplay = book;}
                }
                //for display number of user waiting
                countUserWaiting = bookUserWaitingReservationManager.getNbrOfUserwaitingForReservation(bookId);
                //model
                model.addAttribute("waitReservation", bookReservationToDisplay);
                model.addAttribute("bookName", new BookBean());
                model.addAttribute("nbrUserWaiting", countUserWaiting);
                model.addAttribute("log", userSession);
            }
        } else {
            model.addAttribute("bookName", new BookBean());
            return "/errorHtml/errorMissAuth";
        }
        return "/UserReservation";
    }


    /**
     * to add user on book waiting List
     * @param model
     * @param bookId
     * @param userSession
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addUserReservation/{bookId}")
    public String addUserWaitingReservation(Model model, @PathVariable Integer bookId,
                                            @SessionAttribute(value = "userSession", required = false)LibraryUserBean userSession) throws Exception {
        BookUserWaitingReservationBean newBookUserWaitingReservation = new BookUserWaitingReservationBean();
        LibraryUserBean beanUserOnSession = libraryUserManager.getOneUser(userSession.getUserEmail());

        // check if user has a reservation in progress on book
        boolean bookAlreadyReserved = bookUserWaitingReservationManager.checkIfUserHaveReservationInProgressForConcernedBook(beanUserOnSession.getId(), bookId);
        if (bookAlreadyReserved) {
            model.addAttribute("log", userSession);
            model.addAttribute("bookName", new BookBean());
            return "errorHtml/errorBookAlreadyReserved";
        } else {
            //Add waiting reservation
            newBookUserWaitingReservation.setBookId(bookId);
            newBookUserWaitingReservation.setLibraryUserId(beanUserOnSession.getId());
            newBookUserWaitingReservation.setWaitReservationDate(dateManager.todayDate());
            bookUserWaitingReservationManager.addBookUserWaitingReservation(newBookUserWaitingReservation);
            //change waitReservationFull boolean on book if needed
            bookUserWaitingReservationManager.changeBooleanWaitReservationFullIfNeeded(bookId);
            logger.info("L'utilisateur" + userSession.getUserEmail() + " ajouté à la liste d'attente pour la reservation du livre d'id: " + bookId);
        }
        model.addAttribute("log", userSession);
        model.addAttribute("bookName", new BookBean());
        return "confirmationhtml/addUserOnWaitingListOk";
    }
}
