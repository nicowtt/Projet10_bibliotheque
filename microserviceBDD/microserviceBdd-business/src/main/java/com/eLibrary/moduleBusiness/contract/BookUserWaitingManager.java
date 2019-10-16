package com.eLibrary.moduleBusiness.contract;

import com.eLibrary.moduleModel.beans.BookUserWaitingReservation;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BookUserWaitingManager {
    List<BookUserWaitingReservation> updateUsersStand(int bookId);
}
