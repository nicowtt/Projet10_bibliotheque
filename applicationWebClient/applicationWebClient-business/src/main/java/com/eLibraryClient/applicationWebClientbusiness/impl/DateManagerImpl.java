package com.eLibraryClient.applicationWebClientbusiness.impl;

import com.eLibraryClient.applicationWebClientbusiness.Enums.CompareDate;
import com.eLibraryClient.applicationWebClientbusiness.contract.DateManager;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class DateManagerImpl implements DateManager {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * today date (dd/MM/yyyy)
     *
     * @return
     */
    @Override
    public String todayDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        return simpleDate.format(calendar.getTime());
    }

    /**
     * add days to today date (dd/MM/yyyy)
     *
     * @param pNbrOfDay -> number of days to add
     * @return
     */
    @Override
    public String addDaysOnTodayDate(int pNbrOfDay) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        calendar.add(Calendar.DATE, pNbrOfDay);
        return simpleDate.format(calendar.getTime());
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
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        try {
            newDate = (Date) formatter.parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(newDate);

        calendar.add(Calendar.DATE, nbrOfDay);
        return simpleDate.format(calendar.getTime());
    }

    /**
     * For compare one date with today date
     * @param pDate -> date for compare
     * @return-> enum CompareDate
     */
    @Override
    public CompareDate compareDateWithToday(String pDate) {
        Date newDate = null;
        CompareDate CompareDateEnum = CompareDate.ISBEFORE;
        Calendar todayDate = Calendar.getInstance();
        Calendar dateToCompare = Calendar.getInstance();
        DateFormat dateFormated = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        try {
            newDate = dateFormated.parse(pDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dateToCompare.setTime(newDate);
        String todayDateString = dateFormated.format(todayDate.getTime());
        String dateToCompareString = dateFormated.format(dateToCompare.getTime());

        if (todayDate.equals(dateToCompare)) {
            logger.debug(todayDateString + " is the same as " + dateToCompareString);
            CompareDateEnum = CompareDate.ISTODAY;
        }
        if (todayDate.after(dateToCompare)) {
            logger.debug(todayDateString + " is after " + dateToCompareString);
            CompareDateEnum = CompareDate.ISAFTER;
        }
        if (todayDate.before(dateToCompare)) {
            logger.debug(todayDateString + " is before " + dateToCompareString);
            CompareDateEnum = CompareDate.ISBEFORE;
        }
        return CompareDateEnum;

    }

    /**
     * For compare two dates
     * @param pFirstDate
     * @param pSecondDate
     * @return
     */
    @Override
    public CompareDate compareTwoDate(String pFirstDate, String pSecondDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        CompareDate CompareDateEnum = CompareDate.ISBEFORE;
        Date firstDate = new Date();
        Date secondDate = new Date();

        try {
            firstDate = simpleDateFormat.parse(pFirstDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            secondDate = simpleDateFormat.parse(pSecondDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (pFirstDate.equals(pSecondDate)) {
            logger.debug(pFirstDate + " is the same as " + pSecondDate);
            CompareDateEnum = CompareDate.ISTODAY;
        }

        if (firstDate.after(secondDate)) {
            logger.debug(pFirstDate + " is after " + pSecondDate);
            CompareDateEnum = CompareDate.ISAFTER;
        }
        if (firstDate.before(secondDate)) {
            logger.debug(firstDate + " is before " + secondDate);
            CompareDateEnum = CompareDate.ISBEFORE;
        }
        return CompareDateEnum;
    }

    @Override
    public int countNbrOfDayBetweenTwoDates(String date1, String date2) {
        int CONST_DURATION_OF_DAY = 1000 * 60 * 60 * 24;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        Date firstDate = new Date();
        Date secondDate = new Date();

        try {
            firstDate = simpleDateFormat.parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            secondDate = simpleDateFormat.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Différence
        long diff = Math.abs(secondDate.getTime() - firstDate.getTime());
        int numberOfDay = (int)diff/CONST_DURATION_OF_DAY;
        System.err.println("Le nombre de jour est : " + numberOfDay);

        return numberOfDay;
    }
}
