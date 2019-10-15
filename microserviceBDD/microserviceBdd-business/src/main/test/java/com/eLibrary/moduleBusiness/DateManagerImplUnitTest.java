package com.eLibrary.moduleBusiness;

import com.eLibrary.moduleBusiness.enums.ComparisonDate;
import com.eLibrary.moduleBusiness.impl.DateManagerImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.eLibrary.moduleBusiness.enums.ComparisonDate.ISAFTER;
import static com.eLibrary.moduleBusiness.enums.ComparisonDate.ISBEFORE;

@RunWith(MockitoJUnitRunner.class)
public class DateManagerImplUnitTest {

    @InjectMocks
    DateManagerImpl manager;

    @Test
    public void testTodayDate() {
        Calendar calendar = Calendar.getInstance();
        //template
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        //result
        String todayDate = simpleDate.format(calendar.getTime());
        // test for todayDate
        Assert.assertEquals(manager.todayDate(), todayDate);
    }

    @Test
    public void testCompareDateWithToday() {
        Enum<ComparisonDate> todayEnum = ISBEFORE;
        Calendar calendar = Calendar.getInstance();
        //template
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        //Yesterday test
        calendar.add(Calendar.DATE, -1);
        String yesterday = simpleDate.format(calendar.getTime());
        todayEnum = manager.compareDateWithToday(yesterday);

        Assert.assertTrue("test yesterday NOK", todayEnum == ISAFTER);

        //Tomorrow test
        calendar.add(Calendar.DATE, 2);
        String tomorrow = simpleDate.format(calendar.getTime());
        todayEnum = manager.compareDateWithToday(tomorrow);

        Assert.assertTrue("test tomorrow NOK", todayEnum == ISBEFORE);
    }
}
