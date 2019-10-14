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
    public void testhashPassword() {
        String password = "mdp";

        String hashingPassword = manager.hashPassword(password);
        boolean passwordToTest = manager.checkPassword(password, hashingPassword);

        Assert.assertTrue(passwordToTest);
    }
}
