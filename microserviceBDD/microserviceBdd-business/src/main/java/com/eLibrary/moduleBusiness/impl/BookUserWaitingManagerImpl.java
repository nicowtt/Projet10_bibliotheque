package com.eLibrary.moduleBusiness.impl;

import com.eLibrary.moduleBusiness.contract.BookUserWaitingManager;
import com.eLibrary.moduleDao.dao.BookUserWaitingReservationDao;
import com.eLibrary.moduleModel.beans.BookUserWaitingReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookUserWaitingManagerImpl implements BookUserWaitingManager {

    @Autowired
    private BookUserWaitingReservationDao bookUserWaitingReservationDao;

    /**
     * update users stand if needed (if there is someone on waiting list), other stand decrease of 1
     *
     * @param bookId
     */
    @Override
    public List<Integer> updateUsersStand(int bookId) {
        List<Integer> listStandUpdated = new ArrayList<>();
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
                    listStandUpdated.add(standToUpdate);
                    //update on BDD
                    bookUserWaitingReservationDao.save(bookUserWaitingInProgressListForOneBook.get(j));
                }
            }
        }
        return listStandUpdated;
    }
}
