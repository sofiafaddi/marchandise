package fr.univlyon1.tiw1.tp3.modele;

import fr.univlyon1.tiw1.dao.jpa.modele.JPAEntrepot;
import fr.univlyon1.tiw1.metier.spec.Entrepot;

import javax.persistence.Entity;

/**
 * @author Amaia Nazábal
 * @version 1.0
 * @since 1.0 11/15/17.
 *
 * The model JPA for warehouse.
 */
@Entity
public class EntrepotEntity extends JPAEntrepot implements Entrepot {

    public EntrepotEntity() {
    }

    public EntrepotEntity(String nom, double capacite) {
        super(nom, capacite);
    }

}
