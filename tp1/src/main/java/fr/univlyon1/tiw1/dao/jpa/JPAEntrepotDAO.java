package fr.univlyon1.tiw1.dao.jpa;

import fr.univlyon1.tiw1.dao.jpa.modele.JPAEntrepot;
import fr.univlyon1.tiw1.dao.jpa.modele.JPAMarchandise;
import fr.univlyon1.tiw1.metier.spec.Entrepot;
import fr.univlyon1.tiw1.metier.spec.Marchandise;
import fr.univlyon1.tiw1.metier.spec.OperationFailedException;
import fr.univlyon1.tiw1.metier.spec.dao.EntrepotDAO;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ecoquery on 12/07/2017.
 */
public class JPAEntrepotDAO implements EntrepotDAO {

    private EntityManager entityManager;
    private JPAMarchandiseDAO marchandiseDAO;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void setMarchandiseDAO(JPAMarchandiseDAO marchandiseDAO) {
        this.marchandiseDAO = marchandiseDAO;
    }

    @Override
    public JPAEntrepot findByNom(String nom) {
        return entityManager.find(JPAEntrepot.class, nom);
    }

    @Override
    public Collection<Entrepot> getAllEntrepots() {
        return Collections.unmodifiableCollection(entityManager
                .createNamedQuery("allEntrepots", JPAEntrepot.class)
                .getResultList());
    }

    @Override
    public Entrepot createOrUpdate(Entrepot entrepot) throws OperationFailedException {
        JPAEntrepot managed = findByNom(entrepot.getNom());
        if (managed == null) {
            managed = JPAEntrepot.asJPA(entrepot);
            entityManager.persist(managed);
        }
        if (managed != entrepot) {
            managed.setCapacite(entrepot.getCapacite());
            Map<JPAMarchandise, Integer> newStock = new HashMap<>();
            for (Marchandise m : entrepot.getMarchandisesStockees()) {
                int qte = entrepot.getStock(m);
                if (qte != 0) {
                    JPAMarchandise jm = marchandiseDAO.createOrUpdate(m);
                    newStock.put(jm, qte);
                }
            }
            managed.updateStock(newStock);
        }
        return managed;
    }

    @Override
    public void remove(Entrepot entrepot) throws OperationFailedException {
        JPAEntrepot e = findByNom(entrepot.getNom());
        if (e != null) {
            entityManager.remove(e);
        }
    }
}
