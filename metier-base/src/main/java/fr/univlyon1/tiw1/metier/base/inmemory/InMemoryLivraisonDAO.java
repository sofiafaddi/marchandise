package fr.univlyon1.tiw1.metier.base.inmemory;

import fr.univlyon1.tiw1.metier.spec.Livraison;
import fr.univlyon1.tiw1.metier.spec.OperationFailedException;
import fr.univlyon1.tiw1.metier.spec.dao.LivraisonDAO;
import fr.univlyon1.tiw1.metier.spec.dto.LivraisonDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Created by ecoquery on 04/07/2017.
 */
public class InMemoryLivraisonDAO implements LivraisonDAO {

    private Collection<LivraisonDTO> livraisons = new ArrayList<>();
    private int nextId = 0;

    @Override
    public LivraisonDTO findById(int id) {
        for (LivraisonDTO l : livraisons) {
            if (l.getId() == id) {
                return l;
            }
        }
        return null;
    }

    @Override
    public Collection<Livraison> fromEntrepot(String nom) {
        return livraisons.stream()
                .filter(l -> l.getNomEntrepot().equals(nom))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Livraison> fromMarchandise(int ref) {
        return livraisons.stream()
                .filter(l -> l.getRefMarchandise() == ref)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Livraison> fromMagasin(String nom) {
        return livraisons.stream()
                .filter(l -> l.getMagasin().equals(nom))
                .collect(Collectors.toList());
    }

    @Override
    public LivraisonDTO createOrUpdate(Livraison livraison) throws OperationFailedException {
        LivraisonDTO l = LivraisonDTO.convert(livraison);
        if (livraisons.contains(l)) {
            livraisons.remove(l);
        } else if (l.getId() == -1) {
            l.setId(nextId++);
        }
        livraisons.add(l);
        return l;
    }

    @Override
    public void remove(Livraison livraison) throws OperationFailedException {
        livraisons.remove(LivraisonDTO.convert(livraison));
    }

    @Override
    public Collection<Livraison> getAllLivraisons() throws OperationFailedException {
        return Collections.unmodifiableCollection(livraisons);
    }
}
