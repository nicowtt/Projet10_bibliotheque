package com.eLibrary.moduleBusiness.contract;

import com.eLibrary.moduleModel.beans.BookReservation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookReservationManager {
    List<BookReservation> getBookReservationLate();
}
