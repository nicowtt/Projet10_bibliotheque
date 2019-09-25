package com.eLibraryClient.applicationWebClientweb.controller;

import com.eLibraryClient.applicationWebClientbusiness.contract.*;
import com.eLibraryModel.beans.BookBean;
import com.eLibraryModel.beans.BookReservationBean;
import com.eLibraryModel.beans.LibraryCatalogBean;
import com.eLibraryModel.beans.LibraryUserBean;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.LoggerFactory;

import java.util.List;


@Controller
public class BookReservationController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BookManager bookManager;
    @Autowired
    private LibraryUserManager libraryUserManager;
    @Autowired
    private DateManager dateManager;
    @Autowired
    private BookReservationManager bookReservationManager;
    @Autowired
    private LibraryCatalogManager libraryCatalogManager;

    /**
     * When user choose a book
     * -> need choose library to reserve this book
     * @param bookId
     * @param userSession
     * @param model
     * @return -> choose page
     */
    @RequestMapping(value = "/book/{bookId}/library/{library}", method = RequestMethod.GET)
    public String bookReservationLibraryChoice(@PathVariable Integer bookId,
                                               @PathVariable String library,
                                               @SessionAttribute(value = "userSession", required = false) LibraryUserBean userSession,
                                               Model model) {

        if (userSession != null) {
            List<LibraryCatalogBean> libraryCatalogOnOneBookList = libraryCatalogManager.getLibrariesCatalogForOneBook(bookId);
            List<LibraryCatalogBean> refineLibraryCatalogOnOneBookList =
                    libraryCatalogManager.refineDisponibilityWithBookReservationInProgress(bookId, libraryCatalogOnOneBookList );

            //filter by library if needed
            if (library.equals("null")) {
            } else {
                libraryCatalogManager.keepOnlyOneLibrary(refineLibraryCatalogOnOneBookList, library);
            }

            model.addAttribute("books", refineLibraryCatalogOnOneBookList);
            model.addAttribute("book", new BookBean());
            model.addAttribute("log", userSession);
            model.addAttribute("bookName", new BookBean());
        } else {
            model.addAttribute("bookName", new BookBean());
            return "errorHtml/errorMissAuth";
        }

        return "reservationLibraryChoiceOneBook";

    }

    /**
     * For create a book reservation
     * @param bookId
     * @param userSession
     * @param model
     * @return
     */
    @RequestMapping(value = "/bookReservation/{bookId}/{libraryId}", method = RequestMethod.GET)
    public String bookReservation(@PathVariable Integer bookId,@PathVariable Integer libraryId,
                                  @SessionAttribute(value = "userSession", required = false) LibraryUserBean userSession,
                                  Model model) {
        BookReservationBean newBookReservation = new BookReservationBean();

        LibraryUserBean beanUserOnSession = libraryUserManager.getOneUser(userSession.getUserEmail());
        newBookReservation.setBookId(bookId);
        newBookReservation.setUserId(beanUserOnSession.getId());
        newBookReservation.setLibraryId(libraryId);
        bookReservationManager.completeWithDate(newBookReservation);
        logger.info("nouvelle reservation sur livre d'ID: " + bookId + " dans la bibliotheque d'Id " + libraryId);
        // check book disponibility if needed
        bookManager.changeDisponibilityForOneBook(bookId);

        model.addAttribute("log", userSession);
        model.addAttribute("bookName", new BookBean());

        return "/confirmationhtml/bookReservationOk";
    }

    /**
     * For extend book back end time (only one time)
     * @param reservationId
     * @param userSession
     * @param model
     * @return
     */
    @RequestMapping(value = "/ExtendTime/{reservationId}", method = RequestMethod.GET)
    public String extendReservationTime(@PathVariable Integer reservationId,
                                        @SessionAttribute(value = "userSession", required = false) LibraryUserBean userSession,
                                        Model model) {
        LibraryUserBean beanUserOnSession = libraryUserManager.getOneUser(userSession.getUserEmail());

        BookReservationBean bookReservationBeanToUpdate = bookReservationManager.getOneBookReservation(reservationId);
        String extendDate = dateManager.addDaysOnOneDate(bookReservationBeanToUpdate.getEndOfReservationDate(), 28);
        bookReservationBeanToUpdate.setExtensionOfReservation(true);
        bookReservationBeanToUpdate.setEndOfReservationDate(extendDate);
        bookReservationManager.updateBookReservation(bookReservationBeanToUpdate);

        List<BookReservationBean> bookReservationListForOneUser = bookReservationManager.bookReservationListForOneUser(beanUserOnSession.getId());
        model.addAttribute("reservation", bookReservationListForOneUser);
        model.addAttribute("log", userSession);
        model.addAttribute("bookName", new BookBean());

        logger.info("L'utilisateur " + beanUserOnSession.getUserEmail() + " à prolongé la reservation du livre:"
        + bookReservationBeanToUpdate.getBook().getBookName() + " dans la bibliothèque:"
        + bookReservationBeanToUpdate.getLibrary().getLibraryName() + ".");

        return "/PersonalSpace";
    }

    /**
     * For book bring back
     * @param reservationId
     * @param userSession
     * @param model
     * @return
     */
    @RequestMapping(value = "/BookBack/{reservationId}", method = RequestMethod.GET)
    public String bookBack(@PathVariable Integer reservationId,
                           @SessionAttribute(value = "userSession", required = false)LibraryUserBean userSession,
                           Model model) {
        BookReservationBean bookReservationBeanToUpdate = bookReservationManager.getOneBookReservation(reservationId);

        bookReservationManager.bookBack(reservationId);
        // check book disponibility
        bookManager.changeDisponibilityForOneBook(bookReservationBeanToUpdate.getBook().getId());

        model.addAttribute("log", userSession);
        model.addAttribute("bookName", new BookBean());

        logger.info("L'utilisateur " + userSession.getUserEmail() + " a rendu le livre de la reservation d'id: " + reservationId);

        return "/Confirmationhtml/bookBackOk";
    }


}
