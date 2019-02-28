package com.kwetter.dao.interfaces;

import com.kwetter.dao.memory.KweetDAOMEMImpl;
import com.kwetter.dao.memory.UserDAOMEMImpl;
import org.junit.After;
import org.junit.Before;

public class KweetDAOMEMImplTest extends KweetDAOTest {
    @Before
    public void setup(){
        KweetDAO kweetDAO = new KweetDAOMEMImpl();
        UserDAO userDAO = new UserDAOMEMImpl();
        setKweetDAO(kweetDAO);
        setUserDAO(userDAO);
        super.setup();
    }

    @After
    public void teardown(){
        super.teardown();
    }
}
