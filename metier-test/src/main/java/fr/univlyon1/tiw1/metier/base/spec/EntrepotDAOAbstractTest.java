package fr.univlyon1.tiw1.metier.base.spec;

import fr.univlyon1.tiw1.metier.spec.Entrepot;
import fr.univlyon1.tiw1.metier.spec.OperationFailedException;
import fr.univlyon1.tiw1.metier.spec.dao.EntrepotDAO;
import fr.univlyon1.tiw1.metier.spec.dto.EntrepotDTO;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ecoquery on 04/07/2017.
 */
public abstract class EntrepotDAOAbstractTest extends GenericDAOAbstractTest<EntrepotDAO, Entrepot, String> {

    @Override
    public void setUpData() {
        data = new ArrayList<>();
        for (int i = 1; i <= 4; ++i) {
            data.add(new EntrepotDTO("entrepots" + i, 20.0 + i));
        }
    }

    @Override
    protected Entrepot createOrUpdate(EntrepotDAO entrepotDAO, Entrepot entrepot) throws OperationFailedException {
        return entrepotDAO.createOrUpdate(entrepot);
    }

    @Override
    protected Collection<Entrepot> getAll(EntrepotDAO entrepotDAO) throws OperationFailedException {
        return entrepotDAO.getAllEntrepots();
    }

    @Override
    protected Entrepot findByKey(EntrepotDAO entrepotDAO, String s) throws OperationFailedException {
        return entrepotDAO.findByNom(s);
    }

    @Override
    protected String getKey(Entrepot entrepot) {
        return entrepot.getNom();
    }

    @Override
    protected void remove(EntrepotDAO entrepotDAO, Entrepot entrepot) throws OperationFailedException {
        entrepotDAO.remove(entrepot);
    }
}
