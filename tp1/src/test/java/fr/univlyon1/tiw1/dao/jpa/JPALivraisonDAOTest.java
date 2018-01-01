package fr.univlyon1.tiw1.dao.jpa;

import fr.univlyon1.tiw1.dao.jpa.modele.JPAEntrepot;
import fr.univlyon1.tiw1.dao.jpa.modele.JPAMarchandise;
import fr.univlyon1.tiw1.metier.base.spec.LivraisonDAOAbstractTest;
import fr.univlyon1.tiw1.metier.spec.Livraison;
import fr.univlyon1.tiw1.metier.spec.OperationFailedException;
import fr.univlyon1.tiw1.metier.spec.dao.LivraisonDAO;
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
public class JPALivraisonDAOTest extends LivraisonDAOAbstractTest {
    private static final Logger LOG = LoggerFactory.getLogger(JPALivraisonDAOTest.class);

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
    protected LivraisonDAO getDAO() {
        JPAMarchandiseDAO mdao = new JPAMarchandiseDAO();
        mdao.setEntityManager(entityManager);
        JPAEntrepotDAO edao = new JPAEntrepotDAO();
        edao.setEntityManager(entityManager);
        edao.setMarchandiseDAO(mdao);
        JPALivraisonDAO ldao = new JPALivraisonDAO();
        ldao.setEntityManager(entityManager);
        ldao.setMdao(mdao);
        ldao.setEdao(edao);
        try {
            for (Livraison l : data) {
                edao.createOrUpdate(new JPAEntrepot(l.getNomEntrepot(), 10.0));
                mdao.createOrUpdate(new JPAMarchandise(l.getRefMarchandise(), "m" + l.getRefMarchandise(), 1.0, "d" + l.getRefMarchandise()));
            }
        } catch (OperationFailedException e) {
            LOG.error("Failed to setup data", e);
            fail();
        }
        return ldao;
    }
}
