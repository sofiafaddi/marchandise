package fr.univlyon1.tiw1.metier.base.inmemory;

import fr.univlyon1.tiw1.metier.spec.Approvisionnement;
import fr.univlyon1.tiw1.metier.spec.OperationFailedException;
import fr.univlyon1.tiw1.metier.spec.dao.ApprovisionnementDAO;
import fr.univlyon1.tiw1.metier.spec.dto.ApprovisionnementDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Created by ecoquery on 04/07/2017.
 */
public class InMemoryApprovionnementDAO implements ApprovisionnementDAO {

    private Collection<ApprovisionnementDTO> approvisionnements = new ArrayList<>();
    private int nextId = 0;

    @Override
    public ApprovisionnementDTO findById(int id) {
        for (ApprovisionnementDTO a : approvisionnements) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    @Override
    public Collection<Approvisionnement> fromEntrepot(String nom) {
        return approvisionnements.stream()
                .filter(a -> a.getNomEntrepot().equals(nom))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Approvisionnement> fromMarchandise(int ref) {
        return approvisionnements.stream()
                .filter(a -> a.getRefMarchandise() == ref)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Approvisionnement> fromFournisseur(String nom) {
        return approvisionnements.stream()
                .filter(a -> a.getFournisseur().equals(nom))
                .collect(Collectors.toList());
    }

    @Override
    public ApprovisionnementDTO createOrUpdate(Approvisionnement approvisionnement) throws OperationFailedException {
        ApprovisionnementDTO a = ApprovisionnementDTO.convert(approvisionnement);
        if (approvisionnements.contains(a)) {
            approvisionnements.remove(a);
        } else if (a.getId() == -1) {
            a.setId(nextId++);
        }
        approvisionnements.add(a);
        return a;
    }

    @Override
    public void remove(Approvisionnement approvisionnement) throws OperationFailedException {
        approvisionnements.remove(ApprovisionnementDTO.convert(approvisionnement));
    }

    @Override
    public Collection<Approvisionnement> getAllApprovisionnements() throws OperationFailedException {
        return Collections.unmodifiableCollection(approvisionnements);
    }
}
