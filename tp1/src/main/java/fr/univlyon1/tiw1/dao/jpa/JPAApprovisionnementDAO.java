package fr.univlyon1.tiw1.dao.jpa;

import fr.univlyon1.tiw1.dao.jpa.modele.JPAApprovisionnement;
import fr.univlyon1.tiw1.dao.jpa.modele.JPAEntrepot;
import fr.univlyon1.tiw1.dao.jpa.modele.JPAMarchandise;
import fr.univlyon1.tiw1.metier.spec.Approvisionnement;
import fr.univlyon1.tiw1.metier.spec.OperationFailedException;
import fr.univlyon1.tiw1.metier.spec.dao.ApprovisionnementDAO;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by ecoquery on 12/07/2017.
 */
public class JPAApprovisionnementDAO implements ApprovisionnementDAO {

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
    public JPAApprovisionnement findById(int id) {
        return entityManager.find(JPAApprovisionnement.class, id);
    }

    @Override
    public Collection<Approvisionnement> fromEntrepot(String nom) {
        return Collections.unmodifiableCollection(
                entityManager.createNamedQuery("approvisionnementsFromEntrepot", JPAApprovisionnement.class)
                        .setParameter("nom", nom)
                        .getResultList()
        );
    }

    @Override
    public Collection<Approvisionnement> fromMarchandise(int ref) {
        return Collections.unmodifiableCollection(
                entityManager.createNamedQuery("approvisionnementsFromMarchandise", JPAApprovisionnement.class)
                        .setParameter("ref", ref)
                        .getResultList()
        );
    }

    @Override
    public Collection<Approvisionnement> fromFournisseur(String nom) {
        return Collections.unmodifiableCollection(
                entityManager.createNamedQuery("approvisionnementsFromFournisseur", JPAApprovisionnement.class)
                        .setParameter("fournisseur", nom)
                        .getResultList()
        );
    }

    @Override
    public Approvisionnement createOrUpdate(Approvisionnement approvisionnement) throws OperationFailedException {
        JPAApprovisionnement jl = findById(approvisionnement.getId());
        if (jl == null || !(approvisionnement instanceof JPAApprovisionnement)) {
            boolean exists = jl != null;
            jl = copyJpaApprovisionnement(approvisionnement);
            if (exists) {
                return entityManager.merge(jl);
            } else {
                entityManager.persist(jl);
                return jl;
            }
        } else if (jl == approvisionnement) {
            return approvisionnement;
        } else {
            return entityManager.merge(approvisionnement);
        }
    }

    private JPAApprovisionnement copyJpaApprovisionnement(Approvisionnement approvisionnement) throws OperationFailedException {
        JPAApprovisionnement jl;
        JPAEntrepot e = edao.findByNom(approvisionnement.getNomEntrepot());
        if (e == null) {
            throw new OperationFailedException("pas d'entrepot correspondant trouvé");
        }
        JPAMarchandise m = mdao.findByRef(approvisionnement.getRefMarchandise());
        if (m == null) {
            throw new OperationFailedException("pas de marchandise trouvée");
        }
        jl = new JPAApprovisionnement(m, e, approvisionnement.getFournisseur(), approvisionnement.getQuantite(), approvisionnement.getDateCreation(), approvisionnement.getDatePrevue());
        jl.setDateEffectuee(approvisionnement.getDateEffectuee());
        return jl;
    }

    @Override
    public void remove(Approvisionnement approvisionnement) throws OperationFailedException {
        JPAApprovisionnement ja = findById(approvisionnement.getId());
        if (ja != null) {
            entityManager.remove(ja);
        }
    }

    @Override
    public Collection<Approvisionnement> getAllApprovisionnements() throws OperationFailedException {
        return Collections.unmodifiableCollection(
                entityManager.createNamedQuery("allApprovisionnements", JPAApprovisionnement.class)
                        .getResultList()
        );
    }
}
