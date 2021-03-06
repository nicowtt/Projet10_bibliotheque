package com.eLibraryClient.applicationWebClientbusiness.contract;

import com.eLibraryClient.applicationWebClientbusiness.Enums.CompareDate;
import org.springframework.stereotype.Service;

@Service
public interface DateManager {
    String todayDate();
    String addDaysOnTodayDate(int pNbrOfDay);
    CompareDate compareDateWithToday(String pDate);
    CompareDate compareTwoDate(String pFirstDate, String pSecondDate);
    int countNbrOfDayBetweenTwoDates(String date1, String date2);
}
