package cn.itsource.domain;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigDecimal;


public class EmployeeTest {

    @Test
    public void test() throws Exception {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa02");
        EntityManager manager = factory.createEntityManager();
    }

}