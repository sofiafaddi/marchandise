package fr.univlyon1.tiw1.dao.jpa;

import fr.univlyon1.tiw1.dao.jpa.modele.JPAEntrepot;
import fr.univlyon1.tiw1.dao.jpa.modele.JPALivraison;
import fr.univlyon1.tiw1.dao.jpa.modele.JPAMarchandise;
import fr.univlyon1.tiw1.metier.spec.Livraison;
import fr.univlyon1.tiw1.metier.spec.OperationFailedException;
import fr.univlyon1.tiw1.metier.spec.dao.LivraisonDAO;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by ecoquery on 12/07/2017.
 */
public class JPALivraisonDAO implements LivraisonDAO {

    private EntityManager entityManager;
    private JPAEntrepotDAO edao;
    private JPAMarchandiseDAO mdao;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void setEdao(JPAEntrepotDAO edao) {
        this.edao = edao;
    }

    public void setMdao(JPAMarchandiseDAO mdao) {
        this.mdao = mdao;
    }

    @Override
    public JPALivraison findById(int id) {
        return entityManager.find(JPALivraison.class, id);
    }

    @Override
    public Collection<Livraison> fromEntrepot(String nom) {
        return Collections.unmodifiableCollection(
                entityManager.createNamedQuery("livraisonsFromEntrepot", JPALivraison.class)
                        .setParameter("nom", nom)
                        .getResultList());
    }

    @Override
    public Collection<Livraison> fromMarchandise(int ref) {
        return Collections.unmodifiableCollection(
                entityManager.createNamedQuery("livraisonsFromMarchandise", JPALivraison.class)
                        .setParameter("ref", ref)
                        .getResultList());
    }

    @Override
    public Collection<Livraison> fromMagasin(String nom) {
        return Collections.unmodifiableCollection(
                entityManager.createNamedQuery("livraisonsFromMagasin", JPALivraison.class)
                        .setParameter("magasin", nom)
                        .getResultList()
        );
    }

    @Override
    public Livraison createOrUpdate(Livraison livraison) throws OperationFailedException {
        JPALivraison jl = findById(livraison.getId());
        if (jl == null || !(livraison instanceof JPALivraison)) {
            boolean exists = jl != null;
            jl = copyJpaLivraison(livraison);
            if (exists) {
                return entityManager.merge(jl);
            } else {
                entityManager.persist(jl);
                return jl;
            }
        } else if (jl == livraison) {
            return livraison;
        } else {
            return entityManager.merge(livraison);
        }
    }

    private JPALivraison copyJpaLivraison(Livraison livraison) throws OperationFailedException {
        JPALivraison jl;
        JPAEntrepot e = edao.findByNom(livraison.getNomEntrepot());
        if (e == null) {
            throw new OperationFailedException("pas d'entrepot correspondant trouvé");
        }
        JPAMarchandise m = mdao.findByRef(livraison.getRefMarchandise());
        if (m == null) {
            throw new OperationFailedException("pas de marchandise trouvée");
        }
        jl = new JPALivraison(m, e, livraison.getMagasin(), livraison.getQuantite(), livraison.getDateCreation(), livraison.getDatePrevue());
        jl.setDateEffectuee(livraison.getDateEffectuee());
        return jl;
    }

    @Override
    public void remove(Livraison livraison) throws OperationFailedException {
        JPALivraison jl = findById(livraison.getId());
        if (jl != null) {
            entityManager.remove(jl);
        }
    }

    @Override
    public Collection<Livraison> getAllLivraisons() throws OperationFailedException {
        return Collections.unmodifiableCollection(entityManager.createNamedQuery("allLivraisons", JPALivraison.class).getResultList());
    }
}
