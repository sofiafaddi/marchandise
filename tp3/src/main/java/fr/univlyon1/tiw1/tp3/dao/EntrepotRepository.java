package fr.univlyon1.tiw1.tp3.dao;

import fr.univlyon1.tiw1.dao.jpa.JPAEntrepotDAO;
import fr.univlyon1.tiw1.dao.jpa.JPAMarchandiseDAO;
import fr.univlyon1.tiw1.metier.spec.dao.MarchandiseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Amaia Nazábal
 * @version 1.0
 * @since 1.0 11/15/17.
 *
 * If I notice that I have to use the entity manager by autowire I have to make sure than the annotation transactional
 * is present
 */
//@Transactional
@Repository
public class EntrepotRepository extends JPAEntrepotDAO {

    @Override
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        super.setEntityManager(entityManager);
    }

    @Autowired
    public void setMarchandiseDAO(MarchandiseDAO marchandiseDAO) {
        super.setMarchandiseDAO((JPAMarchandiseDAO) marchandiseDAO);
    }
}
