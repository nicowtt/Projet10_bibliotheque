package com.eLibraryClient.applicationWebClientbusiness.impl;

import com.eLibraryClient.applicationWebClientbusiness.contract.BookReservationManager;
import com.eLibraryClient.applicationWebClientbusiness.contract.LibraryCatalogManager;
import com.eLibraryModel.beans.LibraryCatalogBean;
import com.eLibraryClient.applicationWebClientproxies.proxies.MicroserviceBDDProxy;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Component
public class LibraryCatalogManagerImpl implements LibraryCatalogManager {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MicroserviceBDDProxy microserviceBDDProxy;
    @Autowired
    private BookReservationManager bookReservationManager;

    /**
     * get libraries Catalog for all book
     * @return
     */
    @Override
    public List<LibraryCatalogBean> getLibrariesCatalog() {
        List<LibraryCatalogBean> librariesCatalogList = microserviceBDDProxy.getLibrariesCatalog();
        return librariesCatalogList;
    }

    /**
     * Get libraries Catalog for one book
     * @param
     * @return
     */
    @Override
    public List<LibraryCatalogBean> getLibrariesCatalogForOneBook(int bookId) {
        List<LibraryCatalogBean> librariescatalogForOneBook = microserviceBDDProxy.getLibrariesCatalogForOneBook(bookId);
        return librariescatalogForOneBook;
    }

    /**
     * For refine Disponibility with book reservation in progress
     * @param bookId
     * @param libraryCatalogIOneBookList
     * @return
     */
    @Override
    public List<LibraryCatalogBean> refineDisponibilityWithBookReservationInProgress(int bookId, List<LibraryCatalogBean> libraryCatalogIOneBookList) {
        int reservationInProgress = 0;
        int result = 0;

        for (int i = 0; i < libraryCatalogIOneBookList.size(); i++) {
            reservationInProgress = bookReservationManager.nbrBookReservationInProgressForOneLibraryAndOneBookList(libraryCatalogIOneBookList.get(i).getLibrary().getId(), bookId);
            int iterationOfBook = libraryCatalogIOneBookList.get(i).getId().getBookIteration();
            result = iterationOfBook - reservationInProgress;
            libraryCatalogIOneBookList.get(i).getId().setBookIteration(result);
            logger.info("iteration du livre pour la bibliotheque: " + libraryCatalogIOneBookList.get(i).getLibrary().getLibraryName() + "-> " + iterationOfBook);
            logger.info("Reservation en cours: " + reservationInProgress);
            logger.info("Nouvelle disponibilité :" + result);
            logger.info("************************************************");
        }
        return libraryCatalogIOneBookList;
    }

    /**
     * get list of books with library name and book label filter (multi criteria)
     * @param libraryName
     * @param bookLabel
     * @return
     */
    @Override
    public List<LibraryCatalogBean> getListOfLibraryCatalogWithLibraryNameAndBookLabelFilter(String libraryName, String bookLabel) {
        List<LibraryCatalogBean> libraryCatalogListWithFilters = new ArrayList<>();
        List<LibraryCatalogBean> libraryCatalogBeans = microserviceBDDProxy.getLibrariesCatalog();

        if (libraryName.equals("") && bookLabel.equals("")) { // no filter asking by user
        } else {
            // only library filter
            if (bookLabel.equals("")) {
                for (int i = 0; i < libraryCatalogBeans.size(); i++) {
                    if (libraryCatalogBeans.get(i).getLibrary().getLibraryName().equals(libraryName)) {
                        libraryCatalogListWithFilters.add(libraryCatalogBeans.get(i));
                    }
                }
            }
            // only book label filter
            else if (libraryName.equals("")) {
                for (int i = 0; i < libraryCatalogBeans.size(); i++) {
                    if (libraryCatalogBeans.get(i).getBook().getBookLabel().equals(bookLabel)) {
                        libraryCatalogListWithFilters.add(libraryCatalogBeans.get(i));
                    }
                }
                libraryCatalogListWithFilters = removingBookRepetitionOnLibrariesCatalogBeanList(libraryCatalogListWithFilters);
            }
            // library name and book label
            else {
                for (int i = 0; i < libraryCatalogBeans.size(); i++) {
                    if (libraryCatalogBeans.get(i).getBook().getBookLabel().equals(bookLabel) &&
                            libraryCatalogBeans.get(i).getLibrary().getLibraryName().equals(libraryName) ) {
                        libraryCatalogListWithFilters.add(libraryCatalogBeans.get(i));
                    }
                }
            }
        }
        return libraryCatalogListWithFilters;
    }

    /**
     * For remove book repetition (iteration)
     * @param libraryCatalogListWithFilters
     * @return
     */
    @Override
    public List<LibraryCatalogBean> removingBookRepetitionOnLibrariesCatalogBeanList (List<LibraryCatalogBean> libraryCatalogListWithFilters) {
        String bookName = "";
        int count = 0;
        List<LibraryCatalogBean> listWithoutDouble;
        listWithoutDouble = libraryCatalogListWithFilters;

        for (int i = 0; i < libraryCatalogListWithFilters.size(); i++) {
            bookName = libraryCatalogListWithFilters.get(i).getBook().getBookName();
            count = 0;
            for (int j = 0; j < listWithoutDouble.size(); j++) {
                if (listWithoutDouble.get(j).getBook().getBookName().equals(bookName)) {
                    count++;
                    if (count > 1) {listWithoutDouble.remove(j);}
                }
            }
        }
        return listWithoutDouble;
    }

    /**
     * For keep only one library
     *
     * @param libraryCatalogBeanList
     * @param libraryName
     * @return
     */
    @Override
    public List<LibraryCatalogBean> keepOnlyOneLibrary (List<LibraryCatalogBean> libraryCatalogBeanList, String libraryName) {

        List<LibraryCatalogBean>libraryCatalogBeanListFiltered = libraryCatalogBeanList;

        for (int i = 0; i <  libraryCatalogBeanList.size(); i++) {
            if (libraryCatalogBeanList.get(i).getLibrary().getLibraryName().equals(libraryName)) {
            } else {
                libraryCatalogBeanListFiltered.remove(i);
            }
        }
        return libraryCatalogBeanListFiltered;
    }

}


