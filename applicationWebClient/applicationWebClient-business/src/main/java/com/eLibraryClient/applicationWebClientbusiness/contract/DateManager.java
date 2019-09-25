package com.eLibraryClient.applicationWebClientbusiness.contract;

import com.eLibraryClient.applicationWebClientbusiness.Enums.CompareDate;
import org.springframework.stereotype.Service;

@Service
public interface DateManager {
    String todayDate();
    String addDaysOnTodayDate(int pNbrOfDay);
    CompareDate compareDateWithToday(String pDate);
    String addDaysOnOneDate(String date, int nbrOfDay);
}
