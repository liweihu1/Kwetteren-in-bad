package com.kwetter.dao.interfaces;

import com.kwetter.dao.memory.KweetDAOMEMImpl;
import org.junit.After;
import org.junit.Before;

public class KweetDAOMEMImplTest extends KweetDAOTest {
    @Before
    public void setup(){
        KweetDAO kweetDAO = new KweetDAOMEMImpl();
        setKweetDAO(kweetDAO);
        super.setup();
    }

    @After
    public void teardown(){
        super.teardown();
    }
}
