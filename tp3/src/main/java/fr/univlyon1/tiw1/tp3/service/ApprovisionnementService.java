package fr.univlyon1.tiw1.tp3.service;

import fr.univlyon1.tiw1.metier.spec.Approvisionnement;
import fr.univlyon1.tiw1.metier.spec.OperationFailedException;
import fr.univlyon1.tiw1.metier.spec.dao.ApprovisionnementDAO;
import fr.univlyon1.tiw1.tp3.service.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author Amaia Nazábal
 * @version 1.0
 * @since 1.0 11/30/17.
 *
 * The utility class for the service layer for provisions.
 */
@Service
public class ApprovisionnementService {
    private ApprovisionnementDAO adao;

    @Autowired
    public ApprovisionnementService(ApprovisionnementDAO adao) {
        this.adao = adao;
    }

    /**
     * Retrieve the provision by id.
     * @param id the identifier of the provision
     * @return the provision entity
     * @throws DataNotFoundException propagation of the exception to the controller
     */
    public Approvisionnement getById(int id) throws DataNotFoundException {
        Approvisionnement approvisionnement = adao.findById(id);

        if (approvisionnement == null) {
            throw new DataNotFoundException("L'approvisionnement n'a pas été trouvé");
        }

        return approvisionnement;
    }

    /**
     * Retrieve the list of the provisions.
     *
     * @return the list of the provisions
     * @throws OperationFailedException propagation of the exception to the controller.
     */
    public Collection<Approvisionnement> getAll() throws OperationFailedException {
        return adao.getAllApprovisionnements();
    }

    /**
     * Service for delete the provision
     * @param id the identifier
     * @throws OperationFailedException propagation of the exception to the controller
     * @throws DataNotFoundException propagation of the exception to the controller
     */
    public void remove(int id) throws OperationFailedException, DataNotFoundException {
        Approvisionnement approvisionnement = adao.findById(id);

        if (approvisionnement == null) {
            throw new DataNotFoundException("L'approvisionnement n'a pas été trouvé");
        }
        adao.remove(approvisionnement);
    }

}
