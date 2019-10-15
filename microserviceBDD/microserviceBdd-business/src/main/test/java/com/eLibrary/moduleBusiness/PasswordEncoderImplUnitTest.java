package com.eLibrary.moduleBusiness;

import com.eLibrary.moduleBusiness.impl.PasswordEncoderImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PasswordEncoderImplUnitTest {

    @InjectMocks
    PasswordEncoderImpl manager;

    @Test
    public void testHashPassword() {
        String password = "mdp";
        String hashingPassword = manager.hashPassword(password);
        boolean passwordToTest = manager.checkPassword(password, hashingPassword);
        Assert.assertTrue(passwordToTest);
    }

    @Test
    public void testCheckPassword() {
        boolean testPassword = manager.checkPassword("mdp",
                "$2a$10$ZrNev/FCEyfKp3.Zc/irx.OrtFuqL7X6t.tJytIOiYLQ458k2jasO");
        Assert.assertTrue(testPassword);
    }
}
