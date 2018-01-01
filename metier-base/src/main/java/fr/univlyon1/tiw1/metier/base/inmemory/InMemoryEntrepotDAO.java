package fr.univlyon1.tiw1.metier.base.inmemory;

import fr.univlyon1.tiw1.metier.spec.Entrepot;
import fr.univlyon1.tiw1.metier.spec.OperationFailedException;
import fr.univlyon1.tiw1.metier.spec.dao.EntrepotDAO;
import fr.univlyon1.tiw1.metier.spec.dto.EntrepotDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * DAO fonctionnant seulement en mémoire.
 * <p>
 * Created by ecoquery on 04/07/2017.
 */
public class InMemoryEntrepotDAO implements EntrepotDAO {
    private Collection<EntrepotDTO> entrepots = new ArrayList<>();

    @Override
    public EntrepotDTO findByNom(String nom) {
        for (EntrepotDTO entrepot : entrepots) {
            if (entrepot.getNom().equals(nom)) {
                return entrepot;
            }
        }
        return null;
    }

    @Override
    public Collection<Entrepot> getAllEntrepots() {
        return Collections.unmodifiableCollection(entrepots);
    }

    @Override
    public EntrepotDTO createOrUpdate(Entrepot entrepot) throws OperationFailedException {
        EntrepotDTO edto = EntrepotDTO.convert(entrepot);
        if (entrepots.contains(edto)) {
            entrepots.remove(edto);
        }
        entrepots.add(edto);
        return edto;
    }

    @Override
    public void remove(Entrepot entrepot) throws OperationFailedException {
        EntrepotDTO edto = EntrepotDTO.convert(entrepot);
        entrepots.remove(edto);
    }

}
