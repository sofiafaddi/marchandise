package fr.univlyon1.tiw1.metier.base.inmemory;

import fr.univlyon1.tiw1.metier.base.spec.LivraisonDAOAbstractTest;
import fr.univlyon1.tiw1.metier.spec.dao.LivraisonDAO;

/**
 * Created by ecoquery on 06/07/2017.
 */
public class InMemoryLivraisonDAOTest extends LivraisonDAOAbstractTest {
    @Override
    protected LivraisonDAO getDAO() {
        return new InMemoryLivraisonDAO();
    }
}
