package com.eLibrary.moduleBusiness.impl;

import com.eLibrary.moduleBusiness.contract.DateManager;
import com.eLibrary.moduleBusiness.enums.ComparisonDate;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class DateManagerImpl implements DateManager {

    /**
     * for today date (dd/MM/yyyy)
     *
     * @return
     */
    @Override
    public String todayDate() {
        Calendar calendar = Calendar.getInstance();

        //template
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");

        return simpleDate.format(calendar.getTime());
    }

    /**
     * For compare one date with today date
     * @param pDate -> date for compare
     * @return-> enum CompareDate
     */
    @Override
    public ComparisonDate compareDateWithToday(String pDate) {
        Date newDate = null;
        ComparisonDate CompareDateEnum = ComparisonDate.ISBEFORE;

        Calendar todayDate = Calendar.getInstance(); // 1st calendar is current date/time
        Calendar dateToCompare = Calendar.getInstance(); // 2eme calendar is date to compare

        //template
        DateFormat dateFormated = new SimpleDateFormat("dd/MM/yyyy");

        // convert input on Date
        try {
            newDate = dateFormated.parse(pDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //set 2em calendar
        dateToCompare.setTime(newDate);

        //for display(check)
        String todayDateString = dateFormated.format(todayDate.getTime());
        String dateToCompareString = dateFormated.format(dateToCompare.getTime());

        //Compare dates
        if (todayDate.equals(dateToCompare)) {CompareDateEnum = ComparisonDate.ISTODAY;}
        if (todayDate.after(dateToCompare)) {CompareDateEnum = ComparisonDate.ISAFTER;}
        if (todayDate.before(dateToCompare)) {CompareDateEnum = ComparisonDate.ISBEFORE;}

        return CompareDateEnum;

    }
}
