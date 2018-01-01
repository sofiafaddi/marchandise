package fr.univlyon1.tiw1.dao.jpa;

import fr.univlyon1.tiw1.dao.jpa.modele.JPAMarchandise;
import fr.univlyon1.tiw1.metier.spec.Marchandise;
import fr.univlyon1.tiw1.metier.spec.OperationFailedException;
import fr.univlyon1.tiw1.metier.spec.dao.MarchandiseDAO;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by ecoquery on 12/07/2017.
 */
public class JPAMarchandiseDAO implements MarchandiseDAO {

    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public JPAMarchandise findByRef(int ref) {
        return entityManager.find(JPAMarchandise.class, ref);
    }

    @Override
    public Collection<Marchandise> getAllMarchandises() {
        return Collections.unmodifiableCollection(entityManager
                .createNamedQuery("allMarchandises", JPAMarchandise.class)
                .getResultList());
    }

    @Override
    public JPAMarchandise createOrUpdate(Marchandise marchandise) throws OperationFailedException {
        JPAMarchandise managed = findByRef(marchandise.getReference());
        if (managed == null) {
            managed = JPAMarchandise.asJPA(marchandise);
            if (marchandise.getReference() == -1) {
                entityManager.persist(managed);
            } else {
                // Le numéro est imposé, pas de solution directe en JPA, on passe par SQL
                // il faudrait vérifier que la séquence est correcte (i.e. > à la ref )
                entityManager
                        .createNativeQuery("INSERT INTO marchandise(ref,nom,vol_unit) VALUES (:ref,'',1.0)")
                        .setParameter("ref",marchandise.getReference())
                        .executeUpdate();
                managed = entityManager.merge(managed);
            }
        } else {
            JPAMarchandise marchandise2 = JPAMarchandise.asJPA(marchandise);
            if (managed != marchandise2) {
                managed = entityManager.merge(marchandise2);
            }
        }
        return managed;
    }

    @Override
    public void remove(Marchandise marchandise) throws OperationFailedException {
        JPAMarchandise managed = findByRef(marchandise.getReference());
        if (managed != null) {
            entityManager.remove(managed);
        }
    }
}
