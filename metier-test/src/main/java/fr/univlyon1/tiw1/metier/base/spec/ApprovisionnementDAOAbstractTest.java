package fr.univlyon1.tiw1.metier.base.spec;

import fr.univlyon1.tiw1.metier.spec.Approvisionnement;
import fr.univlyon1.tiw1.metier.spec.OperationFailedException;
import fr.univlyon1.tiw1.metier.spec.dao.ApprovisionnementDAO;
import fr.univlyon1.tiw1.metier.spec.dto.ApprovisionnementDTO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

/**
 * Created by ecoquery on 06/07/2017.
 */
public abstract class ApprovisionnementDAOAbstractTest extends GenericDAOAbstractTest<ApprovisionnementDAO, Approvisionnement, Integer> {
    @Override
    public void setUpData() {
        data = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            int refM = i % 2;
            String nomE = Integer.toString(i % 3);
            String fournisseur = Integer.toString(i % 5);
            Calendar gc = GregorianCalendar.getInstance();
            gc.set(2017, 01, (i + 2) % 28);
            ApprovisionnementDTO approvisionnement = new ApprovisionnementDTO(-1, refM, nomE, fournisseur,
                    i + 1, null, gc.getTime());
            data.add(approvisionnement);
        }

    }

    @Override
    protected Approvisionnement createOrUpdate(ApprovisionnementDAO approvisionnementDAO, Approvisionnement approvisionnement) throws OperationFailedException {
        return approvisionnementDAO.createOrUpdate(approvisionnement);
    }

    @Override
    protected Collection<Approvisionnement> getAll(ApprovisionnementDAO approvisionnementDAO) throws OperationFailedException {
        return approvisionnementDAO.getAllApprovisionnements();
    }

    @Override
    protected Approvisionnement findByKey(ApprovisionnementDAO approvisionnementDAO, Integer integer) {
        return approvisionnementDAO.findById(integer);
    }

    @Override
    protected Integer getKey(Approvisionnement approvisionnement) {
        return approvisionnement.getId();
    }

    @Override
    protected void remove(ApprovisionnementDAO approvisionnementDAO, Approvisionnement approvisionnement) throws OperationFailedException {
        approvisionnementDAO.remove(approvisionnement);
    }
}
