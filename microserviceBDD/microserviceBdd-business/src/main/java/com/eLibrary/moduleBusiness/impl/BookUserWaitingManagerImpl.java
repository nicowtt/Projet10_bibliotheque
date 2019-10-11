package com.eLibrary.moduleBusiness.impl;

import com.eLibrary.moduleBusiness.contract.BookUserWaitingManager;
import com.eLibrary.moduleDao.dao.BookUserWaitingReservationDao;
import com.eLibrary.moduleModel.beans.BookUserWaitingReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookUserWaitingManagerImpl implements BookUserWaitingManager {

    @Autowired
    private BookUserWaitingReservationDao bookUserWaitingReservationDao;

    /**
     * update users stand if needed
     *
     * @param bookId
     */
    @Override
    public void updateUsersStand(int bookId) {
        boolean toUpdate = false;
        List<BookUserWaitingReservation> bookUserWaitingInProgressListForOneBook =
                bookUserWaitingReservationDao.getBookUserWaitingReservationByBookId(bookId);
        int standToUpdate;
        int standUpdated;

        //check if update is needed
        for (int i = 0; i < bookUserWaitingInProgressListForOneBook.size(); i++) {
            if (bookUserWaitingInProgressListForOneBook.get(i).getStandOnWaitingList() == 1) {
                toUpdate = true;
            }
        }
        // update
        if (toUpdate) {
            for (int j = 0; j < bookUserWaitingInProgressListForOneBook.size(); j++) {
                standToUpdate = bookUserWaitingInProgressListForOneBook.get(j).getStandOnWaitingList();
                if (standToUpdate > 1) {
                    standUpdated = standToUpdate - 1;
                    bookUserWaitingInProgressListForOneBook.get(j).setStandOnWaitingList(standUpdated);
                    //update on BDD
                    bookUserWaitingReservationDao.save(bookUserWaitingInProgressListForOneBook.get(j));
                }
            }
        }
    }
}
