package com.kwetter.dao.interfaces;

import com.kwetter.dao.memory.UserDAOMEMImpl;
import org.junit.After;
import org.junit.Before;

public class UserDAOMEMImplTest extends UserDAOTest{
    @Before
    public void setup(){
        UserDAO userDAO = new UserDAOMEMImpl();
        setUserDAO(userDAO);
        super.setup();
    }

    @After
    public void teardown(){
        super.teardown();
    }
}
