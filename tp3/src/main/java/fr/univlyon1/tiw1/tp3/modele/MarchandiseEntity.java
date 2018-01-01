package fr.univlyon1.tiw1.tp3.modele;

import fr.univlyon1.tiw1.dao.jpa.modele.JPAMarchandise;
import fr.univlyon1.tiw1.metier.spec.Marchandise;

import javax.persistence.Entity;

/**
 * @author Amaia Nazábal
 * @version 1.0
 * @since 1.0 11/15/17.
 *
 * The model JPA for the ware.
 *
 */
@Entity
public class MarchandiseEntity extends JPAMarchandise implements Marchandise {

    public MarchandiseEntity() {
        super();
    }

    public MarchandiseEntity(Integer reference, String nom, double volumeUnitaire, String description) {
        super(reference, nom, volumeUnitaire, description);
    }
}
