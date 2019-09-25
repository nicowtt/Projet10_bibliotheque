package com.eLibrary.moduleWeb.controllers;

import com.eLibrary.moduleDao.dao.LibraryDao;
import com.eLibrary.moduleModel.beans.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LibraryController {

    @Autowired
    private LibraryDao libraryDao;

    /**
     * Get list of all libraries
     * @return
     */
    @GetMapping(value = "/Libraries" )
    public List<Library> getLibraryList() {

        List<Library> librariesList = libraryDao.findAll();

        return librariesList;
    }

    /**
     * get one library
     * @param name
     * @return
     */
    @GetMapping(value = "/Library/{name}")
    public Library getOneLibrary(@PathVariable String name) {

        Library oneLibrary = libraryDao.findByLibraryName(name);

        return oneLibrary;
    }


}
