package fr.univlyon1.tiw1.metier.base.spec;

import fr.univlyon1.tiw1.metier.spec.Marchandise;
import fr.univlyon1.tiw1.metier.spec.OperationFailedException;
import fr.univlyon1.tiw1.metier.spec.dao.MarchandiseDAO;
import fr.univlyon1.tiw1.metier.spec.dto.MarchandiseDTO;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ecoquery on 04/07/2017.
 */
public abstract class MarchandiseDAOAbstractTest extends GenericDAOAbstractTest<MarchandiseDAO, Marchandise, Integer> {

    @Override
    public void setUpData() {
        data = new ArrayList<>();
        for (int i = 1; i <= 4; ++i) {
            data.add(new MarchandiseDTO(-1, "marchandises" + i, 10.0 + i, "d" + i));
        }
    }

    @Override
    protected Marchandise createOrUpdate(MarchandiseDAO marchandiseDAO, Marchandise marchandise) throws OperationFailedException {
        return marchandiseDAO.createOrUpdate(marchandise);
    }

    @Override
    protected Collection<Marchandise> getAll(MarchandiseDAO marchandiseDAO) throws OperationFailedException {
        return marchandiseDAO.getAllMarchandises();
    }

    @Override
    protected Marchandise findByKey(MarchandiseDAO marchandiseDAO, Integer integer) throws OperationFailedException {
        return marchandiseDAO.findByRef(integer);
    }

    @Override
    protected Integer getKey(Marchandise marchandise) {
        return marchandise.getReference();
    }

    @Override
    protected void remove(MarchandiseDAO marchandiseDAO, Marchandise marchandise) throws OperationFailedException {
        marchandiseDAO.remove(marchandise);
    }
}
