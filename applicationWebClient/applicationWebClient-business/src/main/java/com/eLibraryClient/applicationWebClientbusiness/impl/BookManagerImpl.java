package com.eLibraryClient.applicationWebClientbusiness.impl;

import com.eLibraryClient.applicationWebClientbusiness.contract.BookManager;
import com.eLibraryClient.applicationWebClientbusiness.contract.BookReservationManager;
import com.eLibraryClient.applicationWebClientbusiness.contract.LibraryCatalogManager;
import com.eLibraryModel.beans.BookBean;
import com.eLibraryModel.beans.LibraryCatalogBean;
import com.eLibraryClient.applicationWebClientproxies.proxies.MicroserviceBDDProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookManagerImpl implements BookManager {

    @Autowired
    private MicroserviceBDDProxy microserviceBDDProxy;
    @Autowired
    private LibraryCatalogManager libraryCatalogManager;
    @Autowired
    private BookReservationManager bookReservationManager;

    static final Log logger = LogFactory.getLog(BookManagerImpl.class);

    /**
     * Ask all books from BDD
     * @return -> list of all books
     */
    @Override
    public List<BookBean> getListAllBooks() {
        List<BookBean> booksList = microserviceBDDProxy.getBooksList();
        return booksList;
    }

    /**
     * Ask one book from BDD
     * @param pBookId
     * @return
     */
    @Override
    public BookBean getOneBook(int pBookId) {
        BookBean oneBook = microserviceBDDProxy.getOneBook(pBookId);
        return oneBook;
    }

    /**
     * Find number of iteration of one book in the city
     * @param bookId
     * @return
     */
    @Override
    public int getNbrOfIterationForOneBook(int bookId) {
        int nbrIteration = 0;

        List<LibraryCatalogBean> librariescatalogForOneBook = libraryCatalogManager.getLibrariesCatalogForOneBook(bookId);
        for (int i = 0; i < librariescatalogForOneBook.size(); i++) {
            nbrIteration = nbrIteration + librariescatalogForOneBook.get(i).getBookIteration();
        }
        return nbrIteration;
    }

    /**
     * For change disponibility for one book
     * @param bookId
     */
    @Override
    public void changeDisponibilityForOneBook(int bookId) {
        boolean booleanStatus = false;

        int nbrIterationBook = getNbrOfIterationForOneBook(bookId);
        int countReservationForOneBookInProgress = bookReservationManager.countReservationInProgressForOneBook(bookId);
        if (countReservationForOneBookInProgress == nbrIterationBook) {
            booleanStatus = true;
            microserviceBDDProxy.changeBookDisponibility(bookId,booleanStatus);
            logger.info("Le livre d'ID:" + bookId +" n'est plus reservable" );
        } else {
            microserviceBDDProxy.changeBookDisponibility(bookId, booleanStatus);
            logger.info("Il y a " + countReservationForOneBookInProgress + " reservation(s) sur " +
            nbrIterationBook + " possible du livre d'ID " + bookId + " en cours.");
        }
    }

    /**
     * get list of distinct book labels
     * @return
     */
    @Override
    public List<BookBean> getDistinctListOfBookLabel() {
        List<BookBean> bookBeanList = new ArrayList<>();

        List<String>listOfDistinctBookLabels = microserviceBDDProxy.getListOfDistinctBooksLabel();

        for (int i = 0; i < listOfDistinctBookLabels.size(); i++) {
            BookBean bookBean = new BookBean();
            bookBean.setBookLabel(listOfDistinctBookLabels.get(i));
            bookBeanList.add(bookBean);
        }
        return bookBeanList;
    }

    /**
     * get one book by bookname
     * @param name
     * @return
     */
    @Override
    public BookBean getOneBook(String name) {
        BookBean oneBook = microserviceBDDProxy.getOneBook(name);
        return  oneBook;
    }

    /**
     * get list of book name
     * @return
     */
    @Override
    public List<String> getListOfBookName() {
        List<String> listOfBookName = microserviceBDDProxy.getListOfBookName();
        return listOfBookName;
    }
}
