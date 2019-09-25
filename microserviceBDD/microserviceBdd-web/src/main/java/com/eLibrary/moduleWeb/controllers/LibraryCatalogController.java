package com.eLibrary.moduleWeb.controllers;

import com.eLibrary.moduleDao.dao.LibraryCatalogDao;
import com.eLibrary.moduleModel.beans.LibraryCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LibraryCatalogController {

    @Autowired
    private LibraryCatalogDao libraryCatalogDao;

    /**
     * To get a list of LibraryCatalog for all books
     * @return
     */
    @GetMapping(value = "/LibrariesCatalog")
    public List<LibraryCatalog> getLibrariesCatalog() {
        List<LibraryCatalog> librariescatalogList = libraryCatalogDao.findAll();

        return librariescatalogList;
    }

    /**
     * to get a list of Librarycatalog for one book
     * @param bookId
     * @return
     */
    @GetMapping(value = "/Librariescatalog/{bookId}")
    public List<LibraryCatalog> libraryCatalogForOneBook(@PathVariable Integer bookId) {
        List<LibraryCatalog> libraryCatalogForOneBookList = libraryCatalogDao.findByBookId(bookId);

        return libraryCatalogForOneBookList;
    }
}
