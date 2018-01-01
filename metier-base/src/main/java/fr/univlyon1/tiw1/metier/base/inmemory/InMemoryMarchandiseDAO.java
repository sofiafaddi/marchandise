package fr.univlyon1.tiw1.metier.base.inmemory;

import fr.univlyon1.tiw1.metier.spec.Marchandise;
import fr.univlyon1.tiw1.metier.spec.OperationFailedException;
import fr.univlyon1.tiw1.metier.spec.dao.MarchandiseDAO;
import fr.univlyon1.tiw1.metier.spec.dto.MarchandiseDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by ecoquery on 04/07/2017.
 */
public class InMemoryMarchandiseDAO implements MarchandiseDAO {
    private Collection<MarchandiseDTO> marchandises = new ArrayList<>();
    private int nextId = 0;

    @Override
    public Marchandise findByRef(int ref) {
        for (MarchandiseDTO m : marchandises) {
            if (ref == m.getReference()) {
                return m;
            }
        }
        return null;
    }

    @Override
    public Collection<Marchandise> getAllMarchandises() {
        return Collections.unmodifiableCollection(marchandises);
    }

    @Override
    public MarchandiseDTO createOrUpdate(Marchandise marchandise) throws OperationFailedException {
        MarchandiseDTO mdto = MarchandiseDTO.convert(marchandise);
        if (mdto.getReference() < 0) {
            mdto.setRef(nextId++);
        }
        if (marchandises.contains(mdto)) {
            marchandises.remove(mdto);
        }
        marchandises.add(mdto);
        return mdto;
    }

    @Override
    public void remove(Marchandise marchandise) throws OperationFailedException {
        MarchandiseDTO mdto = MarchandiseDTO.convert(marchandise);
        marchandises.remove(mdto);
    }
}
