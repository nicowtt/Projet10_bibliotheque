package com.eLibrary.moduleBusiness;

import com.eLibrary.moduleBusiness.impl.BookUserWaitingManagerImpl;
import com.eLibrary.moduleDao.dao.BookUserWaitingReservationDao;
import com.eLibrary.moduleModel.beans.BookUserWaitingReservation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookUserWaitingManagerImplUnitTest {

    @InjectMocks
    BookUserWaitingManagerImpl manager;

    @Mock
    private BookUserWaitingReservationDao mockUserWaitingReservationDao;

    /** Jeu de données */
    private BookUserWaitingReservation bookUserWaitingReservation;
    private List<BookUserWaitingReservation> bookUserWaitingReservationsList;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this.getClass());
        bookUserWaitingReservationsList = new ArrayList<>();
        //create list of bookuserWaitingreservationInProgress with stand 1 and 2
        for (int i = 0; i < 2; i++) {
            bookUserWaitingReservation = new BookUserWaitingReservation();
            bookUserWaitingReservation.setStandOnWaitingList(i + 1);
            bookUserWaitingReservationsList.add(bookUserWaitingReservation);
        }
    }


    @Test
    public void testUpdateUsersStand() {
        when(mockUserWaitingReservationDao.getBookUserWaitingReservationByBookId(1)).thenReturn(bookUserWaitingReservationsList);
        List<BookUserWaitingReservation> listStandUpdated = manager.updateUsersStand(1);

        //test if only stand 2 is taken by method updateUsersStand
        Assert.assertTrue("",listStandUpdated.get(0).getStandOnWaitingList() == 1);
    }
}
