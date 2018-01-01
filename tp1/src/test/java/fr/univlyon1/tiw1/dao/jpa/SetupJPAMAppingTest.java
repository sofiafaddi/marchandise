package fr.univlyon1.tiw1.dao.jpa;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;

/**
 * Created by ecoquery on 12/07/2017.
 */
public class SetupJPAMAppingTest {

    @Test
    public void testSetupJPA() throws SQLException {
        Connection connection = DBUtils.create(DBUtils.DEFAULT_URL);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DBUtils.PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();
        assertNotNull(em);
        em.close();
        connection.close();
    }

}
