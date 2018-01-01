package fr.univlyon1.tiw1.metier.base.spec;

import fr.univlyon1.tiw1.metier.spec.Livraison;
import fr.univlyon1.tiw1.metier.spec.OperationFailedException;
import fr.univlyon1.tiw1.metier.spec.dao.LivraisonDAO;
import fr.univlyon1.tiw1.metier.spec.dto.LivraisonDTO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

/**
 * Created by ecoquery on 04/07/2017.
 */
public abstract class LivraisonDAOAbstractTest extends GenericDAOAbstractTest<LivraisonDAO, Livraison, Integer> {

    @Override
    public void setUpData() {
        data = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            int refM = i % 2;
            String nomE = Integer.toString(i % 3);
            String nomM = Integer.toString(i % 5);
            Calendar gc = GregorianCalendar.getInstance();
            gc.set(2017, 01, (i + 2) % 28);
            LivraisonDTO livraison = new LivraisonDTO(-1, refM, nomE, nomM,
                    i + 1, null, gc.getTime());
            data.add(livraison);
        }
    }

    @Override
    protected Livraison createOrUpdate(LivraisonDAO livraisonDAO, Livraison livraison) throws OperationFailedException {
        return livraisonDAO.createOrUpdate(livraison);
    }

    @Override
    protected Collection<Livraison> getAll(LivraisonDAO livraisonDAO) throws OperationFailedException {
        return livraisonDAO.getAllLivraisons();
    }

    @Override
    protected Livraison findByKey(LivraisonDAO livraisonDAO, Integer integer) throws OperationFailedException {
        return livraisonDAO.findById(integer);
    }

    @Override
    protected Integer getKey(Livraison livraison) {
        return livraison.getId();
    }

    @Override
    protected void remove(LivraisonDAO livraisonDAO, Livraison livraison) throws OperationFailedException {
        livraisonDAO.remove(livraison);
    }
}
