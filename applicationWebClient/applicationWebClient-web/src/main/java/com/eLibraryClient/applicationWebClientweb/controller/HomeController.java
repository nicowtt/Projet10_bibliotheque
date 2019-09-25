package com.eLibraryClient.applicationWebClientweb.controller;

import com.eLibraryClient.applicationWebClientbusiness.contract.BookManager;
import com.eLibraryClient.applicationWebClientbusiness.contract.LibraryCatalogManager;
import com.eLibraryModel.beans.BookBean;
import com.eLibraryModel.beans.LibraryCatalogBean;
import com.eLibraryModel.beans.LibraryUserBean;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BookManager bookManager;

    /**
     * For display home page
     *
     * @param model
     * @return
     */
    @RequestMapping("/home")
    public String accueil(Model model, @SessionAttribute(value = "userSession", required = false)LibraryUserBean userSession){

        List<BookBean> books = bookManager.getListAllBooks();

        model.addAttribute("books", books);
        model.addAttribute("bookName", new BookBean());
        //for display user on session
        if (userSession != null) {
            model.addAttribute("log", userSession);
        }

        return "home";
    }

    /**
     * For display autocomplete on rapid search form
     * @param term
     * @return
     */
    @RequestMapping(value = "/bookNameAutocomplete")
    @ResponseBody
    public List<String> bookNameAutocomplete(@RequestParam(value="term", required = false, defaultValue="") String term) {

        String beginWriteCharacters;
        term = term.toLowerCase();
        List<String> suggestionsFiltre = new ArrayList<>();
        // get all book names
        List<String> suggestionOfNameBook = bookManager.getListOfBookName();

        try {
            // only update when term is three characters.
            if (term.length() == 1) {
                beginWriteCharacters = term;

                for (int i = 0; i < suggestionOfNameBook.size(); i++) {
                    if (suggestionOfNameBook.get(i).substring(0, 1).equals(term)) {
                        suggestionsFiltre.add(suggestionOfNameBook.get(i));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception in book name autocomplete");
        }
        return suggestionsFiltre;
    }
}
