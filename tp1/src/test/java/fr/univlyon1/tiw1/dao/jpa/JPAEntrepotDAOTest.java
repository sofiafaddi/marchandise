package fr.univlyon1.tiw1.dao.jpa;

import fr.univlyon1.tiw1.metier.base.spec.EntrepotDAOAbstractTest;
import fr.univlyon1.tiw1.metier.spec.dao.EntrepotDAO;
import org.junit.After;
import org.junit.Before;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by ecoquery on 12/07/2017.
 */
public class JPAEntrepotDAOTest extends EntrepotDAOAbstractTest {

    protected EntityManager entityManager;
    protected Connection connection;

    @Before
    public void setUpDB() throws SQLException {
        connection = DBUtils.create(DBUtils.DEFAULT_URL);
        entityManager = Persistence.createEntityManagerFactory(DBUtils.PERSISTENCE_UNIT_NAME).createEntityManager();
        entityManager.getTransaction().begin();
    }

    @After
    public void tearDown() throws SQLException {
        entityManager.getTransaction().commit();
        entityManager.close();
        DBUtils.clear(connection);
        connection.close();
    }

    @Override
    protected EntrepotDAO getDAO() {
        JPAEntrepotDAO dao = new JPAEntrepotDAO();
        dao.setEntityManager(entityManager);
        JPAMarchandiseDAO mdao = new JPAMarchandiseDAO();
        mdao.setEntityManager(entityManager);
        dao.setMarchandiseDAO(mdao);
        return dao;
    }
}
