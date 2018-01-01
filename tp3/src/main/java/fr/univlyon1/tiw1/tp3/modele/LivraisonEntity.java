package fr.univlyon1.tiw1.tp3.modele;

import com.fasterxml.jackson.annotation.JsonFormat;
import fr.univlyon1.tiw1.dao.jpa.modele.JPALivraison;
import fr.univlyon1.tiw1.metier.spec.Livraison;

import javax.persistence.Entity;
import java.util.Date;

/**
 * @author Amaia Nazábal
 * @version 1.0
 * @since 1.0 11/15/17.
 *
 * The model JPA for delivery.
 */
@Entity
public class LivraisonEntity extends JPALivraison implements Livraison {

    public LivraisonEntity() {
    }

    public LivraisonEntity(MarchandiseEntity marchandise, EntrepotEntity entrepot, String magasin, int quantite, Date creation, Date datePrevue) {
        super(marchandise, entrepot, magasin, quantite, creation, datePrevue);
    }

    @Override
    @JsonFormat(pattern="MM-dd-yyyy HH:mm:ss.SS")
    public void setDateEffectuee(Date dateEffectuee) {
        super.setDateEffectuee(dateEffectuee);
    }

    @Override
    @JsonFormat(pattern="MM-dd-yyyy HH:mm:ss.SS")
    public void setDatePrevue(Date datePrevue) {
        super.setDatePrevue(datePrevue);
    }
}
