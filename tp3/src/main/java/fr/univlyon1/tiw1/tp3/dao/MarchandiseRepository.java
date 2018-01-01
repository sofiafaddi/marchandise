package fr.univlyon1.tiw1.tp3.dao;

import fr.univlyon1.tiw1.dao.jpa.JPAMarchandiseDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Amaia Nazábal
 * @version 1.0
 * @since 1.0 11/15/17.
 */
@Repository
public class MarchandiseRepository extends JPAMarchandiseDAO {

    @Override
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        super.setEntityManager(entityManager);
    }

}
