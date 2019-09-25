package com.eLibraryClient.applicationWebClientbusiness.contract;

import com.eLibraryModel.beans.BookBean;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public interface BookManager {
    List<BookBean> getListAllBooks();
    BookBean getOneBook(int pBookId);
    int getNbrOfIterationForOneBook(int bookId);
    void changeDisponibilityForOneBook(int bookId);
    List<BookBean> getDistinctListOfBookLabel();
    BookBean getOneBook(String name);
    List<String> getListOfBookName();
}
