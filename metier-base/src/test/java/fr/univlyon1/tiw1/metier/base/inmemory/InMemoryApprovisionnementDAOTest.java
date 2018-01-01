package fr.univlyon1.tiw1.metier.base.inmemory;

import fr.univlyon1.tiw1.metier.base.spec.ApprovisionnementDAOAbstractTest;
import fr.univlyon1.tiw1.metier.spec.dao.ApprovisionnementDAO;

/**
 * Created by ecoquery on 07/07/2017.
 */
public class InMemoryApprovisionnementDAOTest extends ApprovisionnementDAOAbstractTest {
    @Override
    protected ApprovisionnementDAO getDAO() {
        return new InMemoryApprovionnementDAO();
    }
}
