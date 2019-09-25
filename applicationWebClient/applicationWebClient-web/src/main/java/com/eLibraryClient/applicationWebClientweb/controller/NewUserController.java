package com.eLibraryClient.applicationWebClientweb.controller;

import com.eLibraryClient.applicationWebClientbusiness.contract.LibraryUserManager;
import com.eLibraryModel.beans.BookBean;
import com.eLibraryModel.beans.LibraryUserBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@Controller
public class NewUserController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LibraryUserManager libraryUserManager;

    /**
     * For display new User page
     * @param model
     * @return
     */
    @GetMapping(value = "/newUser")
    public String newUser(Model model) {
        model.addAttribute("newUser", new LibraryUserBean());
        model.addAttribute("bookName", new BookBean());

        return "/newUser";
    }

    /**
     * save new user
     * @param libraryNewUserBean
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping(value = "/newUserPost")
    public String newUserPost(@Valid @ModelAttribute("newUser") LibraryUserBean libraryNewUserBean,
                              BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("newUser", libraryNewUserBean);
            model.addAttribute("bookName", new BookBean());
            logger.info("*********");
            logger.info("erreur lors du remplissage formulaire enregistrement nouvel utilisateur");
            logger.debug("debug");

            return "/newUser";
        } else {
            // write new user on bdd
            LibraryUserBean newUserWrited = libraryUserManager.addNewUserOnBDD(libraryNewUserBean);
            if (newUserWrited.getId() == 0) {
                model.addAttribute("bookName", new BookBean());
                logger.info("Nouvel utilisateur non enregistré, l'email: " + libraryNewUserBean.getUserEmail() + " est déja présent en BDD.");
                return "errorHtml/errorEmailAlreadyExist";
            } else {
                model.addAttribute("bookName", new BookBean());
                logger.info("Nouvel utilisateur: " + libraryNewUserBean.getUserEmail() + " enregistré.");
                return "confirmationhtml/userWrittingOk";
            }
        }
    }
}

