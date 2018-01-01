package fr.univlyon1.tiw1.metier.base.inmemory;

import fr.univlyon1.tiw1.metier.base.spec.MarchandiseDAOAbstractTest;
import fr.univlyon1.tiw1.metier.spec.dao.MarchandiseDAO;

/**
 * Created by ecoquery on 04/07/2017.
 */
public class InMemoryMarchandiseDAOTest extends MarchandiseDAOAbstractTest {
    @Override
    protected MarchandiseDAO getDAO() {
        return new InMemoryMarchandiseDAO();
    }
}
