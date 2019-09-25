package com.eLibrary.moduleBusiness.contract;

import com.eLibrary.moduleModel.beans.LibraryUser;
import org.springframework.stereotype.Service;

@Service
public interface UserManager {
    boolean checkIfMailExist(String mail);
    boolean checkIfUserMailAndPassIsOk(LibraryUser userBean);
    LibraryUser addNewUser(LibraryUser userBean);
}
