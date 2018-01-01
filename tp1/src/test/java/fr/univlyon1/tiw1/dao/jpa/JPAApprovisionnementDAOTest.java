package fr.univlyon1.tiw1.dao.jpa;

import fr.univlyon1.tiw1.dao.jpa.modele.JPAEntrepot;
import fr.univlyon1.tiw1.dao.jpa.modele.JPAMarchandise;
import fr.univlyon1.tiw1.metier.base.spec.ApprovisionnementDAOAbstractTest;
import fr.univlyon1.tiw1.metier.spec.Approvisionnement;
import fr.univlyon1.tiw1.metier.spec.Livraison;
import fr.univlyon1.tiw1.metier.spec.OperationFailedException;
import fr.univlyon1.tiw1.metier.spec.dao.ApprovisionnementDAO;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.fail;

/**
 * Created by ecoquery on 13/07/2017.
 */
public class JPAApprovisionnementDAOTest extends ApprovisionnementDAOAbstractTest {
    private static final Logger LOG = LoggerFactory.getLogger(JPAApprovisionnementDAOTest.class);

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
    protected ApprovisionnementDAO getDAO() {
        JPAMarchandiseDAO mdao = new JPAMarchandiseDAO();
        mdao.setEntityManager(entityManager);
        JPAEntrepotDAO edao = new JPAEntrepotDAO();
        edao.setEntityManager(entityManager);
        edao.setMarchandiseDAO(mdao);
        JPAApprovisionnementDAO adao = new JPAApprovisionnementDAO();
        adao.setEntityManager(entityManager);
        adao.setMdao(mdao);
        adao.setEdao(edao);
        try {
            for (Approvisionnement a : data) {
                edao.createOrUpdate(new JPAEntrepot(a.getNomEntrepot(), 10.0));
                mdao.createOrUpdate(new JPAMarchandise(a.getRefMarchandise(), "m" + a.getRefMarchandise(), 1.0, "d" + a.getRefMarchandise()));
            }
        } catch (OperationFailedException e) {
            LOG.error("Failed to setup data", e);
            fail();
        }
        return adao;
    }
}
