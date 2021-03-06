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
     * for today date (dd/MM/yyyy HH:mm:ss)
     *
     * @return
     */
    @Override
    public String todayDate() {
        Calendar calendar = Calendar.getInstance();

        //template
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

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
        DateFormat dateFormated = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

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

    /**
     * For add day on input date (String -> dd/MM/yyyy)
     * @param inputDate
     * @param nbrOfDay
     * @return
     */
    @Override
    public String addDaysOnOneDate(String inputDate, int nbrOfDay) {
        Date newDate = new Date();
        DateFormat formatter;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            newDate = (Date) formatter.parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(newDate);

        calendar.add(Calendar.DATE, nbrOfDay);
        return simpleDate.format(calendar.getTime());
    }
}
