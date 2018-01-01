package fr.univlyon1.tiw1.tp3.service;

import fr.univlyon1.tiw1.metier.spec.Livraison;
import fr.univlyon1.tiw1.metier.spec.OperationFailedException;
import fr.univlyon1.tiw1.metier.spec.dao.LivraisonDAO;
import fr.univlyon1.tiw1.tp3.service.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author Amaia Nazábal
 * @version 1.0
 * @since 1.0 11/29/17.
 *
 * Layer of service for the delivery.
 */
@Service
public class LivraisonService {
    private LivraisonDAO ldao;

    @Autowired
    public LivraisonService(LivraisonDAO ldao) {
        this.ldao = ldao;
    }

    /**
     * Retrieve the delivery by id.
     *
     * @param id the delivery identifier.
     * @return the delivery entity.
     * @throws DataNotFoundException if any delivery with this id exists.
     */
    public Livraison getById(int id) throws DataNotFoundException {
        Livraison livraison = ldao.findById(id);

        if (livraison == null) {
            throw new DataNotFoundException("La livraison n'as pas été trouvé.");
        }

        return livraison;
    }

    /**
     * Retrieve all the deliveries
     * @return the list of deliveries
     * @throws OperationFailedException if the operation fails
     */
    public Collection<Livraison> getAll() throws OperationFailedException {
        return ldao.getAllLivraisons();
    }

    /**
     * Remove a specific delivery by id
     * @param id the delivery identifier.
     * @throws OperationFailedException if the operation fails
     * @throws DataNotFoundException if any delivery with this id exists.
     */
    public void remove(int id) throws OperationFailedException, DataNotFoundException {
        Livraison livraison = ldao.findById(id);

        if (livraison == null) {
            throw new DataNotFoundException("La livraison n'as pas été trouvé.");
        }

        ldao.remove(livraison);
    }

}
