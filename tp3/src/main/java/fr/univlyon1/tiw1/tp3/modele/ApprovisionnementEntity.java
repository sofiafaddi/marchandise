package fr.univlyon1.tiw1.tp3.modele;

import com.fasterxml.jackson.annotation.JsonFormat;
import fr.univlyon1.tiw1.dao.jpa.modele.JPAApprovisionnement;
import fr.univlyon1.tiw1.metier.spec.Approvisionnement;

import javax.persistence.Entity;
import java.util.Date;

/**
 * @author Amaia Nazábal
 * @version 1.0
 * @since 1.0 11/15/17.
 *
 * The model JPA for provision.
 */
@Entity
public class ApprovisionnementEntity extends JPAApprovisionnement implements Approvisionnement {

    public ApprovisionnementEntity() {
    }

    public ApprovisionnementEntity(MarchandiseEntity marchandise, EntrepotEntity entrepot, String fournisseur, int quantite, Date creation, Date prevue) {
        super(marchandise, entrepot, fournisseur, quantite, creation, prevue);
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
