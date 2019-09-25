package com.eLibraryClient.applicationWebClientproxies.proxies;


import com.eLibraryModel.beans.*;
import org.springframework.cloud.openfeign.FeignClient;
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
    //************ BOOK *************************//
    //*******************************************//

    /**
     * asking list of all books on microserviceBDD
     * @return
     */
    @GetMapping(value = "/Books")
    List<BookBean> getBooksList();


    /**
     * Asking list of book (only version of one book)
     * -> user will choice library
     * @return
     */
    @GetMapping(value = "/Book/{id}")
    BookBean getOneBook(@PathVariable("id") Integer id);

    /**
     * For change book status (disponibilty)
     * @param bookId
     * @param booleanStatus
     */
    @GetMapping(value = "/ChangeBookDisponibility/{bookId}/{booleanStatus}")
    void changeBookDisponibility(@PathVariable("bookId") Integer bookId,
                                 @PathVariable("booleanStatus") boolean booleanStatus);

    /**
     * get list of distinct book labels
     * @return
     */
    @GetMapping(value = "/BooksLabel")
    List<String> getListOfDistinctBooksLabel();

    /**
     * get one book by book name
     *
     * @param name
     * @return
     */
    @GetMapping(value = "/BookName/{name}")
    BookBean getOneBook(@PathVariable("name") String name);

    /**
     * get list of book name
     * @return
     */
    @GetMapping(value = "/BooksNameList")
    List<String> getListOfBookName();


    //*******************************************//
    //************ USER *************************//
    //*******************************************//

    /**
     * Asking list of all users on microserviceBDD
     * @return
     */
    @GetMapping(value = "/Users")
    List<LibraryUserBean> getListOfUsers();

    /**
     * Asking one user bean
     * @param pEmail
     * @return
     */
    @GetMapping(value = "/User/{email}")
    LibraryUserBean getOneUser(@PathVariable("email") String pEmail);

    /**
     * Write new user on microserviceBDD
     * @param libraryUserBean -> new user bean
     * @return
     */
    @PostMapping(value = "/NewUser")
    LibraryUserBean addUser(@RequestBody LibraryUserBean libraryUserBean);

    /**
     * For check if user mail and password exist
     * @param libraryUserBean
     * @return
     */
    @PostMapping(value = "/CheckUser")
    boolean checkUser(@RequestBody LibraryUserBean libraryUserBean);

    //*******************************************//
    //************ Library **********************//
    //*******************************************//


    /**
     * get one library
     * @param name
     * @return
     */
    @GetMapping(value = "/Library/{name}")
    LibraryBean getOneLibrary(@PathVariable("name") String name);

    /**
     * Get list of all libraries
     * @return
     */
    @GetMapping(value = "/Libraries")
    List<LibraryBean> getAllLibraries();

    //*******************************************//
    //************ LibraryCatalog ***************//
    //*******************************************//


    /**
     * get libraries Catalog
     * @return
     */
    @GetMapping(value = "/LibrariesCatalog")
    List<LibraryCatalogBean> getLibrariesCatalog();

    /**
     * get libraries Catalog for one book
     * @return
     */
    @GetMapping(value = "/Librariescatalog/{bookId}")
    List<LibraryCatalogBean> getLibrariesCatalogForOneBook(@PathVariable("bookId") Integer bookId);



    //*******************************************//
    //************ Book Reservation ******************//
    //*******************************************//


    /**
     * write new reservation on microserviceBDD
     * @param bookReservationBean
     * @return
     */
    @PostMapping(value = "/NewBookReservation")
    BookReservationBean addReservation(@RequestBody BookReservationBean bookReservationBean);

    /**
     * get all reservations
     * @return
     */
    @GetMapping(value = "/BookReservation")
    List<BookReservationBean> getAllReservation();

    /**
     * get all reservations for one user
     * @param userId
     * @return
     */
    @GetMapping(value = "/UserBookReservation/{userId}")
    List<BookReservationBean> getbookReservationForOneUserList(@PathVariable("userId") Integer userId);

    /**
     * get one reservation
     * @param reservationId
     * @return
     */
    @GetMapping(value = "/OneBookReservation/{reservationId}")
    BookReservationBean getOneBookReservation(@PathVariable("reservationId") Integer reservationId);


    /**
     * for update book Reservation
     * @param bookReservationBean
     * @return
     */
    @PostMapping(value = "/UpdateBookReservation")
    BookReservationBean updateReservation(@RequestBody BookReservationBean bookReservationBean);

    /**
     * For reservation bookBack
     * @param reservationId
     */
    @GetMapping(value = "/BookBack/{reservationId}")
    void bookBack(@PathVariable("reservationId") Integer reservationId);
}
