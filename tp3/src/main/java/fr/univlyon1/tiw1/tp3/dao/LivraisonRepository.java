package fr.univlyon1.tiw1.tp3.dao;

import fr.univlyon1.tiw1.dao.jpa.JPAEntrepotDAO;
import fr.univlyon1.tiw1.dao.jpa.JPALivraisonDAO;
import fr.univlyon1.tiw1.dao.jpa.JPAMarchandiseDAO;
import fr.univlyon1.tiw1.metier.spec.dao.EntrepotDAO;
import fr.univlyon1.tiw1.metier.spec.dao.MarchandiseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Amaia Nazábal
 * @version 1.0
 * @since 1.0 11/15/17.
 */
@Repository
public class LivraisonRepository extends JPALivraisonDAO {

    @Override
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        super.setEntityManager(entityManager);
    }

    @Autowired
    public void setEdao(EntrepotDAO edao) {
        super.setEdao((JPAEntrepotDAO) edao);
    }

    @Autowired
    public void setMdao(MarchandiseDAO mdao) {
        super.setMdao((JPAMarchandiseDAO) mdao);
    }
}
