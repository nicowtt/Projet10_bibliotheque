package com.eLibrary.moduleWeb.controllers;

import com.eLibrary.moduleDao.dao.BookDao;
import com.eLibrary.moduleModel.beans.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookDao bookDao;

    /**
     * Get list of all books from BDD
     *
     * @return
     */
    @GetMapping(value = "/Books", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Book> booksList() {

        List<Book> booksList = bookDao.findAll();

        return booksList;
    }

    /**
     * Get information about one book
     * @param id -> id of book
     * @return
     */
    @GetMapping(value = "/Book/{id}")
    public Book OneBook(@PathVariable int id) {
        Book oneBook = bookDao.getOne(id);

        return oneBook;
    }

    /**
     * change book disponibility
     * @param bookId
     * @return
     */
    @GetMapping(value = "/ChangeBookDisponibility/{bookId}/{booleanStatus}")
    public HttpStatus changeBookDisponibilityForOneBook(@PathVariable int bookId,
                                                        @PathVariable boolean booleanStatus) {
        bookDao.changeDisponibilityForOneBook(bookId, booleanStatus);

        //send httpStatus -> ok
        return HttpStatus.OK;
    }

    /**
     * get list without book label repetition
     * @return
     */
    @GetMapping(value = "/BooksLabel")
    public List<String> listWithoutLabelRepetition() {
        List<String> listWithoutLabelRepetition = bookDao.findDistinctByBookLabel();

        return listWithoutLabelRepetition;
    }

    /**
     * get one book by book name
     * @param name
     * @return
     */
    @GetMapping(value = "/BookName/{name}")
    public Book oneBook(@PathVariable String name) {
        Book oneBook = bookDao.getByBookName(name);

        return oneBook;
    }

    /**
     * Get list of book name
     * @return
     */
    @GetMapping(value = "/BooksNameList")
    public List<String> listBooksName() {
        List<String> listBooksName = bookDao.findDistinctByBookName();

        return listBooksName;
    }

}
