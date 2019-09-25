package com.eLibrary.moduleBusiness.impl;

import com.eLibrary.moduleBusiness.contract.BookReservationManager;
import com.eLibrary.moduleBusiness.contract.DateManager;
import com.eLibrary.moduleBusiness.enums.ComparisonDate;
import com.eLibrary.moduleDao.dao.BookReservationDao;
import com.eLibrary.moduleModel.beans.BookReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.eLibrary.moduleBusiness.enums.ComparisonDate.ISAFTER;
import static com.eLibrary.moduleBusiness.enums.ComparisonDate.ISBEFORE;

@Component
public class BookReservationManagerImpl implements BookReservationManager {

    @Autowired
    private BookReservationDao bookReservationDao;
    @Autowired
    private DateManager dateManager;


    /**
     * Get late book reservation list
     * @return
     */
    @Override
    public List<BookReservation> getBookReservationLate() {

        List<BookReservation> bookReservationListLate = new ArrayList<>();
        Enum<ComparisonDate> comparisonDateEnum = ISBEFORE;

        // get book reservation list (only in progress)
        List<BookReservation> allBookReservation = bookReservationDao.getBookReservationByBookBackEquals(false);
        // filter (keep only if end reservation date is after today)
        for (int i = 0; i < allBookReservation.size(); i++) {
            //comparaison
            comparisonDateEnum = dateManager.compareDateWithToday(allBookReservation.get(i).getEndOfReservationDate());
            if (comparisonDateEnum == ISAFTER) {
                bookReservationListLate.add(allBookReservation.get(i));
            }
        }
        return bookReservationListLate;
    }

}
