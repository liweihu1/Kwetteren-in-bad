package com.kwetter.service;

import com.kwetter.dao.JPA.KweetDAOJPAImpl;
import com.kwetter.dao.JPA.UserDAOJPAImpl;
import com.kwetter.dao.database.MemoryDatabase;
import com.kwetter.dao.interfaces.KweetDAO;
import com.kwetter.dao.interfaces.UserDAO;
import com.kwetter.dao.memory.KweetDAOMEMImpl;
import com.kwetter.dao.memory.UserDAOMEMImpl;
import com.kwetter.domain.Kweet;
import com.kwetter.domain.Role;
import com.kwetter.domain.Trend;
import com.kwetter.domain.User;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class UserServiceTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClasses(KweetService.class, Kweet.class, KweetDAO.class, UserDAO.class, User.class, Trend.class, Role.class, KweetDAOMEMImpl.class, KweetDAOJPAImpl.class, UserDAOMEMImpl.class, UserDAOJPAImpl.class, MemoryDatabase.class, UserService.class)
                .addPackages(true, KweetService.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
}