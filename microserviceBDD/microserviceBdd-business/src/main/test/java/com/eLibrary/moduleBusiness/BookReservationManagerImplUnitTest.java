package com.eLibrary.moduleBusiness;
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
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
//@SpringBootTest
public class BookReservationManagerImplUnitTest {

    @InjectMocks
    BookReservationManagerImpl bookReservationManager;

    @InjectMocks
    DateManagerImpl dateManager;

    @Mock
    private BookReservationDao mockReservationDao;

    @Mock
    private DateManagerImpl mockDateManager;

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
        // create a lst of two late book reservation
        for (int i = 0; i < 2; i++) {
            endOfReservationCal.add(Calendar.DATE, - i -1);
            endOfReservation = dateFormated.format(endOfReservationCal.getTime());
            BookReservation bookReservation = Mockito.mock(BookReservation.class);
            when(bookReservation.getEndOfReservationDate()).thenReturn(endOfReservation);
            testListBookReservation.add(bookReservation);
        }

        when(mockReservationDao.getBookReservationByBookBackEquals(false)).thenReturn(testListBookReservation);
        //todo  mock for: comparisonDateEnum = dateManager.compareDateWithToday(allBookReservation.get(i).getEndOfReservationDate());

        List<BookReservation> bookLateList = bookReservationManager.getBookReservationLate();

        //test
        Assert.assertTrue("La methode ne donne plus les livre en retard", bookLateList.size() == 2 );
    }
}
