package fr.univlyon1.tiw1.dao.jpa.modele;

import fr.univlyon1.tiw1.metier.spec.Approvisionnement;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ecoquery on 12/07/2017.
 */
@Entity
@Table(name = "approvisionnement")
@NamedQueries({
        @NamedQuery(name = "allApprovisionnements", query = "SELECT a FROM JPAApprovisionnement a"),
        @NamedQuery(name = "approvisionnementsFromEntrepot", query = "SELECT a FROM JPAApprovisionnement a JOIN a.entrepot e WHERE e.nom = :nom"),
        @NamedQuery(name = "approvisionnementsFromMarchandise", query = "SELECT l FROM JPAApprovisionnement l JOIN l.marchandise m WHERE m.reference = :ref"),
        @NamedQuery(name = "approvisionnementsFromFournisseur", query = "SELECT l FROM JPAApprovisionnement l  WHERE l.fournisseur = :fournisseur")})

public class JPAApprovisionnement implements Approvisionnement {
    @Id
    @GeneratedValue(generator = "hibernate-sequence")
    @SequenceGenerator(name = "hibernate-sequence", allocationSize = 1)
    private Integer id;

    private String fournisseur;
    @Temporal(TemporalType.DATE)
    private Date datePrevue;
    @Temporal(TemporalType.DATE)
    private Date dateEffectuee = null;
    @ManyToOne
    @JoinColumn(name = "ref_m")
    protected JPAMarchandise marchandise;
    @ManyToOne
    @JoinColumn(name = "nom_e")
    protected JPAEntrepot entrepot;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
    private int quantite;

    public JPAApprovisionnement() {
        // Empty constructor for JPA
    }

    /**
     * Construit un nouvel approvisionnement
     * @param marchandise la marchandise
     * @param entrepot l'entrepot
     * @param fournisseur le fournisseur
     * @param quantite la quantite
     * @param creation la date de creation. Si cette date est nulle, la création est initialisée à la date courante
     * @param datePrevue la date de livraison prévue.
     */
    public JPAApprovisionnement(JPAMarchandise marchandise, JPAEntrepot entrepot, String fournisseur, int quantite, Date creation, Date datePrevue) {
        this.fournisseur = fournisseur;
        this.datePrevue = datePrevue;
        this.dateCreation = creation;
        this.marchandise = marchandise;
        this.entrepot = entrepot;
        this.quantite = quantite;
        if (this.dateCreation == null) {
            this.dateCreation = new Date();
        }
    }

    @Override
    public int getId() {
        return id;
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
    public int getRefMarchandise() {
        return marchandise.getReference();
    }

    @Override
    public String getNomEntrepot() {
        return entrepot.getNom();
    }

    @Override
    public Date getDateCreation() {
        return dateCreation;
    }

    @Override
    public Date getDateEffectuee() {
        return dateEffectuee;
    }

    @Override
    public Date getDatePrevue() {
        return datePrevue;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }

    public void setDatePrevue(Date datePrevue) {
        this.datePrevue = datePrevue;
    }

    public void setDateCreation(Date creation) {
        this.dateCreation = creation;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setDateEffectuee(Date effectue) {
        this.dateEffectuee = effectue;
    }

    public void setMarchandise(JPAMarchandise marchandise) {
        this.marchandise = marchandise;
    }

    public void setEntrepot(JPAEntrepot entrepot) {
        this.entrepot = entrepot;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
