package fr.univlyon1.tiw1.metier.spec.dto;

import fr.univlyon1.tiw1.metier.spec.Approvisionnement;

import java.util.Date;
import java.util.Objects;

/**
 * Representation d'un approvisionnement.
 * <p>
 * Created by ecoquery on 03/07/2017.
 */
public class ApprovisionnementDTO implements Approvisionnement {
    private int id;
    private int refMarchandise;
    private String nomEntrepot;
    private String fournisseur;
    private int quantite;
    private Date dateCreation;
    private Date datePrevue;
    private Date dateEffectue;

    /**
     * Creation d'un approvisionnement. L'approvisionnement est supposé non encore effectué.
     *
     * @param id             l'identifiant
     * @param refMarchandise la référence de la marchandise
     * @param nomEntrepot    le nom de l'entrepot
     * @param fournisseur    le fournisseur
     * @param quantite       la quantite
     * @param dateCreation   la date de creation
     * @param datePrevue      la date de livraison prevue
     */
    public ApprovisionnementDTO(int id, int refMarchandise, String nomEntrepot, String fournisseur,
                                int quantite, Date dateCreation, Date datePrevue) {
        this.id = id;
        this.refMarchandise = refMarchandise;
        this.nomEntrepot = nomEntrepot;
        this.fournisseur = fournisseur;
        this.quantite = quantite;
        this.dateCreation = dateCreation;
        this.datePrevue = datePrevue;
        this.dateEffectue = null;
        if (this.dateCreation == null) {
            this.dateCreation = new Date();
        }
    }

    /**
     * Clone d'un approvisionnement
     *
     * @param approvisionnement initial
     */
    public ApprovisionnementDTO(Approvisionnement approvisionnement) {
        this(approvisionnement.getId(), approvisionnement.getRefMarchandise(), approvisionnement.getNomEntrepot(),
                approvisionnement.getFournisseur(), approvisionnement.getQuantite(),
                approvisionnement.getDateCreation(), approvisionnement.getDatePrevue());
        if (approvisionnement.isEffectuee()) {
            this.setDateEffectuee(approvisionnement.getDateEffectuee());
        }
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getRefMarchandise() {
        return refMarchandise;
    }

    @Override
    public String getNomEntrepot() {
        return nomEntrepot;
    }

    @Override
    public String getFournisseur() {
        return fournisseur;
    }

    @Override
    public int getQuantite() {
        return quantite;
    }

    @Override
    public Date getDateCreation() {
        return dateCreation;
    }

    @Override
    public Date getDatePrevue() {
        return datePrevue;
    }

    @Override
    public Date getDateEffectuee() {
        return dateEffectue;
    }

    @Override
    public void setDateEffectuee(Date dateEffectue) {
        this.dateEffectue = dateEffectue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ApprovisionnementDTO that = (ApprovisionnementDTO) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Convertit un approvisionnement en DTO
     *
     * @param approvisionnement l'approvisionnement à convertir
     * @return l'apporvisionnement éventuellement converti
     */
    public static ApprovisionnementDTO convert(Approvisionnement approvisionnement) {
        return approvisionnement instanceof ApprovisionnementDTO ? (ApprovisionnementDTO) approvisionnement : new ApprovisionnementDTO(approvisionnement);
    }

    public void setId(int id) {
        this.id = id;
    }
}
