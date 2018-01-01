package fr.univlyon1.tiw1.metier.base.inmemory;

import fr.univlyon1.tiw1.metier.base.spec.EntrepotDAOAbstractTest;
import fr.univlyon1.tiw1.metier.spec.dao.EntrepotDAO;

/**
 * Created by ecoquery on 04/07/2017.
 */
public class InMemoryEntrepotDAOTest extends EntrepotDAOAbstractTest {
    @Override
    protected EntrepotDAO getDAO() {
        return new InMemoryEntrepotDAO();
    }
}
