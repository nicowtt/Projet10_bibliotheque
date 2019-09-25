package com.eLibraryClient.applicationWebClientbusiness.impl;

import com.eLibraryClient.applicationWebClientbusiness.contract.LibraryManager;
import com.eLibraryModel.beans.LibraryBean;
import com.eLibraryClient.applicationWebClientproxies.proxies.MicroserviceBDDProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LibraryManagerImpl implements LibraryManager {

    @Autowired
    MicroserviceBDDProxy microserviceBDDProxy;

    /**
     * To get one library bean
     * @param libraryName
     * @return
     */
    @Override
    public LibraryBean getOneLibrary(String libraryName) {
        LibraryBean oneLibrary = microserviceBDDProxy.getOneLibrary(libraryName);
        return oneLibrary;
    }

    /**
     * To get all libraries list
     * @return
     */
    @Override
    public List<LibraryBean> getAllLibraries() {
        List<LibraryBean> allLibrarieslist = microserviceBDDProxy.getAllLibraries();
        return allLibrarieslist;
    }
}
