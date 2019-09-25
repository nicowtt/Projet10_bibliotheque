package com.eLibraryClient.applicationWebClientbusiness.impl;

import com.eLibraryClient.applicationWebClientbusiness.contract.LibraryUserManager;
import com.eLibraryModel.beans.LibraryUserBean;
import com.eLibraryClient.applicationWebClientproxies.proxies.MicroserviceBDDProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class LibraryUserManagerImpl implements LibraryUserManager {

    @Autowired
    private MicroserviceBDDProxy microserviceBDDProxy;

    /**
     * For add new user on microservice BDD
     * @param libraryUserBean
     */
    @Override
    public LibraryUserBean addNewUserOnBDD(LibraryUserBean libraryUserBean) {
        LibraryUserBean newUserWrited= microserviceBDDProxy.addUser(libraryUserBean);

        return newUserWrited;
    }

    /**
     * get bean with one user mail
     * @param userEmail
     * @return
     */
    @Override
    public LibraryUserBean getOneUser(String userEmail) {
        LibraryUserBean getOneUser = microserviceBDDProxy.getOneUser(userEmail);
        return getOneUser;
    }

    /**
     * To check is user mail and password exist on microserviceBDD
     * @param userBean
     * @return
     */
    @Override
    public boolean checkIfMailAndPassIsOk(LibraryUserBean userBean) {
        Boolean isValid = false;
        isValid = microserviceBDDProxy.checkUser(userBean);
        return  isValid;
    }
}
