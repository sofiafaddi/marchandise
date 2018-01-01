package fr.univlyon1.tiw1.tp3.service;

import fr.univlyon1.tiw1.metier.base.MarchandiseOperationsImpl;
import fr.univlyon1.tiw1.metier.spec.Marchandise;
import fr.univlyon1.tiw1.metier.spec.OperationFailedException;
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
 * Layer of service for the ware.
 */
@Service
public class MarchandiseService extends MarchandiseOperationsImpl {

    @Autowired
    public MarchandiseService(MarchandiseDAO mdao) {
        super(mdao);
    }

    /**
     * Retrieve all the wares
     * @return the list of wares
     */
    public Collection<Marchandise> getAll() {
        return this.mdao.getAllMarchandises();
    }

    /**
     * Remove a specific ware by reference
     * @param ref the ware reference
     * @throws OperationFailedException if the operation fails
     * @throws DataNotFoundException if any ware with this reference exists.
     */
    public void remove(int ref) throws OperationFailedException, DataNotFoundException {
        Marchandise marchandise = this.mdao.findByRef(ref);
        if (marchandise == null)
            throw new DataNotFoundException("Marchandise n'existe pas.");

        super.delete(marchandise);
    }

    /**
     * Retrieve the ware by reference.
     *
     * @param ref the ware reference.
     * @return the ware entity.
     * @throws DataNotFoundException if any ware with this reference exists.
     */
    public Marchandise getByReference(int ref) throws DataNotFoundException {
        Marchandise marchandise = getByRef(ref);

        if (marchandise == null)
            throw new DataNotFoundException("La marchandise n'existe pas.");

        return marchandise;
    }
}
