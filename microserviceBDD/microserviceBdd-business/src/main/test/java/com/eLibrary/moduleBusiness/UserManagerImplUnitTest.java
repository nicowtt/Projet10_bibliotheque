package com.eLibrary.moduleBusiness;

import com.eLibrary.moduleBusiness.impl.PasswordEncoderImpl;
import com.eLibrary.moduleBusiness.impl.UserManagerImpl;
import com.eLibrary.moduleDao.dao.LibraryUserDao;
import com.eLibrary.moduleModel.beans.LibraryUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserManagerImplUnitTest {

    @InjectMocks
    UserManagerImpl manager;

    @Mock
    LibraryUserDao mockLibraryUserDao;

    @Mock
    PasswordEncoderImpl mockPasswordEncoder;

    @InjectMocks
    PasswordEncoderImpl passwordEncoder;

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
        userPresentOnBDD.setUserPassword("notGoodHash");

        when(mockLibraryUserDao.findByUserEmail("jean-claude.vandamme@Gmail.com")).thenReturn(oneUser, userPresentOnBDD);

        //for checkIfMailExist method
        when(oneUser.getUserEmail()).thenReturn("jean-claude.vandamme@Gmail.com");
        //for checkPassord method
        when(oneUser.getUserPassword()).thenReturn("mdp");
        when(userPresentOnBDD.getUserPassword()).thenReturn("notGoodHash");

        boolean valid = manager.checkIfUserMailAndPassIsOk(oneUser);
        Assert.assertFalse("test user and password", valid);
    }
}
