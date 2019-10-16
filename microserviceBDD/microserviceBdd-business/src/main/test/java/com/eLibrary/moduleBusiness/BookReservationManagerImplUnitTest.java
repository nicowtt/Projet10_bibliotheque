package com.eLibrary.moduleBusiness;
import com.eLibrary.moduleBusiness.contract.DateManager;
import com.eLibrary.moduleBusiness.enums.ComparisonDate;
import com.eLibrary.moduleBusiness.impl.BookReservationManagerImpl;
import com.eLibrary.moduleBusiness.impl.DateManagerImpl;
import com.eLibrary.moduleDao.dao.BookReservationDao;
import com.eLibrary.moduleModel.beans.BookReservation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookReservationManagerImplUnitTest {

    @InjectMocks
    BookReservationManagerImpl manager;

    @Mock
    private BookReservationDao mockReservationDao;

    @Mock
    private DateManager mockDateManager;

    /** Jeu de données */
    private List<BookReservation> testListBookReservation;
    private Date testTodayDate;

    @Before
    public void setup() {
        testListBookReservation = new ArrayList<>();
        testTodayDate = new Date();
    }

    @Test
    public void testGetBookReservationLate() {
        String endOfReservation = "";
        //template
        DateFormat dateFormated = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        // calendar
        Calendar beginReservationCal = Calendar.getInstance();
        beginReservationCal.add(Calendar.MONTH,-1);
        Calendar endOfReservationCal = Calendar.getInstance();
        // create a list with two book reservation -> late reservation
        for (int i = 0; i < 2; i++) {
            endOfReservationCal.add(Calendar.DATE, i - 1);
            endOfReservation = dateFormated.format(endOfReservationCal.getTime());
            BookReservation bookReservation = Mockito.mock(BookReservation.class);
            when(bookReservation.getEndOfReservationDate()).thenReturn(endOfReservation);
            testListBookReservation.add(bookReservation);
        }
        when(mockDateManager.compareDateWithToday(anyString())).thenReturn(ComparisonDate.ISAFTER);
        when(mockReservationDao.getBookReservationByBookBackEquals(false)).thenReturn(testListBookReservation);
        List<BookReservation> bookLateList = manager.getBookReservationLate();

        //test when a list with no late reservation is given
        Assert.assertTrue("getBookReservationLate method don't given late reservation", bookLateList.size() == 2 );
    }
}
