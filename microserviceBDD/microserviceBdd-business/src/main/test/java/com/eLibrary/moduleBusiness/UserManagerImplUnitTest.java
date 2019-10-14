package com.eLibrary.moduleBusiness;

import com.eLibrary.moduleBusiness.impl.UserManagerImpl;
import com.eLibrary.moduleDao.dao.LibraryUserDao;
import com.eLibrary.moduleModel.beans.LibraryUser;
import javafx.beans.binding.When;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserManagerImplUnitTest {

    @InjectMocks
    UserManagerImpl manager;

    @Mock
    LibraryUserDao mockLibraryUserDao;

    @Mock
    UserManagerImpl mockManager;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testCheckIfMailExist() {
        LibraryUser oneUser = Mockito.mock(LibraryUser.class);
        oneUser.setId(1);
        oneUser.setUserEmail("unTest@gmail.com");

        when(mockLibraryUserDao.findByUserEmail(anyString())).thenReturn(oneUser);
        boolean exist = manager.checkIfMailExist("unTest@Gmail.com");

        Assert.assertTrue(exist);
    }

    @Test
    public void testCheckIfUserMailAndPassIsOk() {
        boolean mailExist = true;
        LibraryUser oneUser = Mockito.mock(LibraryUser.class);
        oneUser.setId(1);
        oneUser.setUserEmail("unTest@gmail.com");

        when(mockLibraryUserDao.findByUserEmail(anyString())).thenReturn(oneUser);
        //todo mock cette methode: mailExist = this.checkIfMailExist(userBeanInput.getUserEmail());
//        doReturn(true).when(mockManager.checkIfMailExist(anyString()));
//        when(mockManager.checkIfMailExist(anyString())).thenReturn(true);

        manager.checkIfUserMailAndPassIsOk(oneUser);
    }

}
