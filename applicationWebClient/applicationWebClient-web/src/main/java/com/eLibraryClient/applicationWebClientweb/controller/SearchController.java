package com.eLibraryClient.applicationWebClientweb.controller;

import com.eLibraryClient.applicationWebClientbusiness.contract.BookManager;
import com.eLibraryClient.applicationWebClientbusiness.contract.LibraryCatalogManager;
import com.eLibraryClient.applicationWebClientbusiness.contract.LibraryManager;
import com.eLibraryModel.beans.BookBean;
import com.eLibraryModel.beans.LibraryBean;
import com.eLibraryModel.beans.LibraryCatalogBean;
import com.eLibraryModel.beans.LibraryUserBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class SearchController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BookManager bookManager;
    @Autowired
    LibraryManager libraryManager;
    @Autowired
    LibraryCatalogManager libraryCatalogManager;

    /**
     * For display filters (by library or/and by book label)
     * @param userSession
     * @param model
     * @return
     */
    @GetMapping(value = "/Search")
    public String search(@SessionAttribute(value = "userSession", required = false)LibraryUserBean userSession,
                         Model model) {
        BookBean newBook = new BookBean();
        List<LibraryBean> allLibrariesList = libraryManager.getAllLibraries();
        List<BookBean> bookslabelList = bookManager.getDistinctListOfBookLabel();

        model.addAttribute("libraries", allLibrariesList);
        model.addAttribute("library", new LibraryBean());
        model.addAttribute("books", bookslabelList);
        model.addAttribute("book", newBook);
        model.addAttribute("bookName", new BookBean());

        if (userSession != null) {
            model.addAttribute("log", userSession);
        }
        return "/Search";
    }

    /**
     * For display books with filter (library name and book label(ex:fantastique))
     * @param libraryBean
     * @param bookBean
     * @param userSession
     * @param model
     * @return
     */
    @PostMapping(value = "/SearchPost")
    public String searchPost(@Valid @ModelAttribute("library") LibraryBean libraryBean,
                             @Valid @ModelAttribute("book") BookBean bookBean,
                             @SessionAttribute(value = "userSession", required = false)LibraryUserBean userSession,
                             Model model) {
        String libraryName = "";
        String label = "";

        List<LibraryCatalogBean> LibraryCatalogListWithFilter = libraryCatalogManager.getListOfLibraryCatalogWithLibraryNameAndBookLabelFilter(libraryBean.getLibraryName(), bookBean.getBookLabel());
        //set library information for display filter
        libraryName = libraryBean.getLibraryName();
        if (libraryName.equals("")) {
            libraryName = null;
        }
        //set label information for display filter
        label = bookBean.getBookLabel();
        if (label.equals("")) {
            label = null;
        }

        model.addAttribute("libraryCatalog", LibraryCatalogListWithFilter);
        model.addAttribute("bookName", new BookBean());
        model.addAttribute("log", userSession);
        model.addAttribute("library", libraryName);
        model.addAttribute("label", label);
        return "/filterResult";
    }

    /**
     * For display book asking by user
     * @param bookBeanToComplete
     * @param userSession
     * @param model
     * @return
     */
    @PostMapping(value = "/SearchByBookName")
    public String searchByBookName(@Valid @ModelAttribute("bookName") BookBean bookBeanToComplete,
                                   @SessionAttribute(value = "userSession", required = false)LibraryUserBean userSession,
                                   Model model) {
        boolean bookIsOnBdd = false;
        if (userSession != null) {
            model.addAttribute("log", userSession);
        }
        String inputBookName = bookBeanToComplete.getBookName();
        List<String> allbookNames = bookManager.getListOfBookName();
        //compare input with all book name
        for (int i = 0; i < allbookNames.size(); i++) {
            if (inputBookName.equals(allbookNames.get(i))) {
                bookIsOnBdd = true;
            }
        }
        // complete bookBean if ok
        if (bookIsOnBdd) {
            BookBean bookBeanCompleted = bookManager.getOneBook(bookBeanToComplete.getBookName());
            List<LibraryCatalogBean> libraryCatalogListWithFilter = libraryCatalogManager.getLibrariesCatalogForOneBook(bookBeanCompleted.getId());
            //remove repetition
            List<LibraryCatalogBean> listWithoutRepetition = libraryCatalogManager.removingBookRepetitionOnLibrariesCatalogBeanList(libraryCatalogListWithFilter);
            model.addAttribute("libraryCatalog", listWithoutRepetition);
        } else {
            logger.info("L'utilisateur à demandé un livre inexistant.");
            model.addAttribute("bookName", new BookBean());
            return "errorHtml/errorBookSearch";
        }
        model.addAttribute("bookName", new BookBean());
        return "/filterResult";
    }
}
