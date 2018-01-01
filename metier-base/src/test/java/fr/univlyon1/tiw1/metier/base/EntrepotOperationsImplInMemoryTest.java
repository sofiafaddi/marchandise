package fr.univlyon1.tiw1.metier.base;

import fr.univlyon1.tiw1.metier.base.inmemory.InMemoryApprovionnementDAO;
import fr.univlyon1.tiw1.metier.base.inmemory.InMemoryEntrepotDAO;
import fr.univlyon1.tiw1.metier.base.inmemory.InMemoryLivraisonDAO;
import fr.univlyon1.tiw1.metier.base.inmemory.InMemoryMarchandiseDAO;
import fr.univlyon1.tiw1.metier.base.spec.AbstractEntrepotOperationsTest;
import fr.univlyon1.tiw1.metier.spec.EntrepotOperations;
import fr.univlyon1.tiw1.metier.spec.dao.ApprovisionnementDAO;
import fr.univlyon1.tiw1.metier.spec.dao.EntrepotDAO;
import fr.univlyon1.tiw1.metier.spec.dao.LivraisonDAO;
import fr.univlyon1.tiw1.metier.spec.dao.MarchandiseDAO;

/**
 * Created by ecoquery on 07/07/2017.
 */
public class EntrepotOperationsImplInMemoryTest extends AbstractEntrepotOperationsTest {
    @Override
    protected MarchandiseDAO getMarchandiseDAO() {
        return new InMemoryMarchandiseDAO();
    }

    @Override
    protected ApprovisionnementDAO getApprovionnementDAO() {
        return new InMemoryApprovionnementDAO();
    }

    @Override
    protected LivraisonDAO getLivraisonDAO() {
        return new InMemoryLivraisonDAO();
    }

    @Override
    protected EntrepotDAO getEntrepotDAO() {
        return new InMemoryEntrepotDAO();
    }

    @Override
    protected EntrepotOperations getEntrepotOperations(EntrepotDAO edao, LivraisonDAO ldao, ApprovisionnementDAO adao, MarchandiseDAO mdao) {
        return new EntrepotOperationsImpl(edao, ldao, adao, mdao);
    }
}
