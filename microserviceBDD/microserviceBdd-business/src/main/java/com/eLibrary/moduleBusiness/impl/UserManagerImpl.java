package com.eLibrary.moduleBusiness.impl;

import com.eLibrary.moduleBusiness.contract.PasswordEncoder;
import com.eLibrary.moduleBusiness.contract.UserManager;
import com.eLibrary.moduleDao.dao.LibraryUserDao;
import com.eLibrary.moduleModel.beans.LibraryUser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Component
public class UserManagerImpl implements UserManager {

    @Autowired
    private LibraryUserDao libraryUserDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    static final Log logger = LogFactory.getLog(UserManagerImpl.class);

    @Override
    public boolean checkIfMailExist(String mail) {
        boolean mailExist = false;

        LibraryUser oneUser = libraryUserDao.findByUserEmail(mail);
        if (oneUser != null) {
            mailExist = true;
        } else {
            logger.info("L'utilisateur: " + mail + " n'existe pas en BDD.");
        }
        return mailExist;
    }

    /**
     * For check if user mail and password is ok
     *
     * @param userBeanInput
     * @return
     */
    @Override
    public boolean checkIfUserMailAndPassIsOk(LibraryUser userBeanInput) {
        LibraryUser userOnBdd = new LibraryUser();
        boolean mailExist = false;
        boolean passwordIsValid = false;
        boolean mailAndUserExist = false;

        // check if email exist on bdd
        mailExist = this.checkIfMailExist(userBeanInput.getUserEmail());

        if (mailExist) {
            // get user bean on BDD
            userOnBdd = libraryUserDao.findByUserEmail(userBeanInput.getUserEmail());
            //compare password
            passwordIsValid = passwordEncoder.checkPassword(userBeanInput.getUserPassword(), userOnBdd.getUserPassword());
            if (passwordIsValid) {
                mailAndUserExist = true;
                logger.info("L'utilisateur " + userBeanInput.getUserEmail() + " est validé.");
            } else {
                logger.info("L'utilisateur " + userBeanInput.getUserEmail() + " n'a pas rentré le bon mot de passe.");
            }
        }
        return mailAndUserExist;
    }

    /**
     * For write new user on BDD
     * @param userBean
     */
    @Override
    public LibraryUser addNewUser(LibraryUser userBean) {
        LibraryUser newUser = new LibraryUser();

        //encrypt password
        String hashedPassword = passwordEncoder.hashPassword(userBean.getUserPassword());
        userBean.setUserPassword(hashedPassword);
        //write new user on bdd
        try {
            newUser = libraryUserDao.save(userBean);
        } catch ( DataIntegrityViolationException e) {
            logger.info("L'enregistrement du nouvel utilisateur à échoué: l'email existe déjà en BDD");
        }
        return newUser;
    }
}
