package com.eLibraryClient.applicationWebClientbusiness.contract;

import com.eLibraryModel.beans.LibraryCatalogBean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LibraryCatalogManager {

    List<LibraryCatalogBean> getLibrariesCatalog();
    List<LibraryCatalogBean> getLibrariesCatalogForOneBook(int bookId);
    List<LibraryCatalogBean> refineDisponibilityWithBookReservationInProgress(int bookId, List<LibraryCatalogBean> libraryCatalogBeanList);
    List<LibraryCatalogBean> getListOfLibraryCatalogWithLibraryNameAndBookLabelFilter(String libraryName, String bookLabel);
    List<LibraryCatalogBean> removingBookRepetitionOnLibrariesCatalogBeanList (List<LibraryCatalogBean> libraryCatalogListWithFilters);
    List<LibraryCatalogBean> keepOnlyOneLibrary (List<LibraryCatalogBean> libraryCatalogBeanList, String libraryName);
}
