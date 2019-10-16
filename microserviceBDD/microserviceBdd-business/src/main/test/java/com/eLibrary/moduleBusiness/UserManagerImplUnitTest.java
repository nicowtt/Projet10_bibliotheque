package com.eLibrary.moduleBusiness;

import com.eLibrary.moduleBusiness.contract.PasswordEncoder;
import com.eLibrary.moduleBusiness.impl.UserManagerImpl;
import com.eLibrary.moduleDao.dao.LibraryUserDao;
import com.eLibrary.moduleModel.beans.LibraryUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserManagerImplUnitTest {

    @InjectMocks
    UserManagerImpl manager;

    @Mock
    LibraryUserDao mockLibraryUserDao;

    @Mock
    PasswordEncoder mockPasswordEncoder;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testCheckIfMailExist() {
        LibraryUser oneUser = Mockito.mock(LibraryUser.class);

        when(mockLibraryUserDao.findByUserEmail("jean-claude.vandamme@Gmail.com")).thenReturn(oneUser);
        boolean exist = manager.checkIfMailExist("jean-claude.vandamme@Gmail.com");
        Assert.assertTrue("test mail present",exist);

        exist = manager.checkIfMailExist("bruce.lee@gmail.com");
        Assert.assertTrue("test mail not present",!exist);

    }

    @Test
    public void testCheckIfUserMailAndPassIsOk() {
        boolean mailExist = true;
        LibraryUser oneUser = Mockito.mock(LibraryUser.class);
        oneUser.setUserEmail("jean-claude.vandamme@Gmail.com");
        oneUser.setUserPassword("mdp");

        LibraryUser userPresentOnBDD = Mockito.mock(LibraryUser.class);
        userPresentOnBDD.setUserEmail("jean-claude.vandamme@Gmail.com");
        userPresentOnBDD.setUserPassword("$2a$10$izLvX7nRBB6qohlBCiGEzOHwlCLRoUwAJ0hChn.JAnhXDZp2MT3P.");

        when(mockLibraryUserDao.findByUserEmail("jean-claude.vandamme@Gmail.com")).thenReturn(oneUser, userPresentOnBDD);

        //for checkIfMailExist method
        when(oneUser.getUserEmail()).thenReturn("jean-claude.vandamme@Gmail.com");
        //for checkPassord method
        when(oneUser.getUserPassword()).thenReturn("mdp");
        when(userPresentOnBDD.getUserPassword()).thenReturn("$2a$10$izLvX7nRBB6qohlBCiGEzOHwlCLRoUwAJ0hChn.JAnhXDZp2MT3P.");
        when(mockPasswordEncoder.checkPassword(oneUser.getUserPassword(), userPresentOnBDD.getUserPassword())).thenReturn(true);

        boolean valid = manager.checkIfUserMailAndPassIsOk(oneUser);
        Assert.assertTrue("user or password NOK", valid);
    }
}
