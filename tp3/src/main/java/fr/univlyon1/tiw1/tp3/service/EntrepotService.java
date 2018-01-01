package fr.univlyon1.tiw1.tp3.service;

import fr.univlyon1.tiw1.metier.base.EntrepotOperationsImpl;
import fr.univlyon1.tiw1.metier.spec.Entrepot;
import fr.univlyon1.tiw1.metier.spec.OperationFailedException;
import fr.univlyon1.tiw1.metier.spec.dao.ApprovisionnementDAO;
import fr.univlyon1.tiw1.metier.spec.dao.EntrepotDAO;
import fr.univlyon1.tiw1.metier.spec.dao.LivraisonDAO;
import fr.univlyon1.tiw1.metier.spec.dao.MarchandiseDAO;
import fr.univlyon1.tiw1.tp3.service.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author Amaia Nazábal
 * @version 1.0
 * @since 1.0 11/15/17.
 *
 * Layer of service for the warehouse.
 */
@Service
public class EntrepotService extends EntrepotOperationsImpl {

    @Autowired
    public EntrepotService(EntrepotDAO edao, LivraisonDAO ldao,
                           ApprovisionnementDAO adao, MarchandiseDAO mdao) {
        super(edao, ldao, adao, mdao);
    }

    /**
     * Retrieve the warehouse by name
     * @param nom the name of the warehouse
     * @return the warehouse entity
     *      @see fr.univlyon1.tiw1.tp3.modele.EntrepotEntity
     * @throws DataNotFoundException if any warehouse with this name exists.
     */
    public Entrepot getByName(String nom) throws DataNotFoundException {
        Entrepot entrepot = super.getByNom(nom);
        if (entrepot == null)
            throw new DataNotFoundException("L'entrepot n'existe pas.");

        return entrepot;
    }

    /**
     * Retrieve all the warehouses
     * @return the list of the warehouses.
     */
    public Collection<Entrepot> getAll() {
        return this.edao.getAllEntrepots();
    }

    /**
     * Remove a specific ware house by name
     *
     * @param nom the name of the warehouse
     * @throws DataNotFoundException if any warehouse with this name exists.
     * @throws OperationFailedException if it's not possible remove the entity.
     */
    public void remove(String nom) throws DataNotFoundException, OperationFailedException {
        Entrepot entrepot = super.getByNom(nom);
        if (entrepot == null)
            throw new DataNotFoundException("L'entrepot n'existe pas");

        super.edao.remove(entrepot);
    }

}
