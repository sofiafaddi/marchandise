package fr.univlyon1.tiw1.dao.jpa.modele;

import fr.univlyon1.tiw1.metier.spec.Livraison;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ecoquery on 12/07/2017.
 */
@Entity
@Table(name = "livraison")
@NamedQueries({
        @NamedQuery(name = "allLivraisons", query = "SELECT l FROM JPALivraison l"),
        @NamedQuery(name = "livraisonsFromEntrepot", query = "SELECT l FROM JPALivraison l JOIN l.entrepot e WHERE e.nom = :nom"),
        @NamedQuery(name = "livraisonsFromMarchandise", query = "SELECT l FROM JPALivraison l JOIN l.marchandise m WHERE m.reference = :ref"),
        @NamedQuery(name = "livraisonsFromMagasin", query = "SELECT l FROM JPALivraison l  WHERE l.magasin = :magasin")})
public class JPALivraison implements Livraison {
    @Id
    @GeneratedValue(generator = "hibernate_sequence")
    @SequenceGenerator(name = "hibernate_sequence", allocationSize = 1)
    private Integer id;
    @Temporal(TemporalType.DATE)
    @Column(name = "prevue")
    private Date datePrevue;
    @Temporal(TemporalType.DATE)
    @Column(name = "effectuee")
    private Date dateEffective;
    private String magasin;
    @ManyToOne
    @JoinColumn(name = "ref_m")
    protected JPAMarchandise marchandise;
    @ManyToOne
    @JoinColumn(name = "nom_e")
    protected JPAEntrepot entrepot;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creation;
    private int quantite;

    public JPALivraison() {
        // Empty constructor for JPA
    }

    /**
     * Crée une nouvelle livraison
     * @param marchandise la marchandise
     * @param entrepot l'entrepot
     * @param magasin le magasin
     * @param quantite la quantite à livrer
     * @param creation la date de creation. Si cette date est nulle, c'est la date courante qui sera utilisée
     * @param datePrevue la date prévue pour la livraison
     */
    public JPALivraison(JPAMarchandise marchandise, JPAEntrepot entrepot, String magasin, int quantite, Date creation, Date datePrevue) {
        this.datePrevue = datePrevue;
        this.magasin = magasin;
        this.marchandise = marchandise;
        this.entrepot = entrepot;
        this.creation = creation;
        this.quantite = quantite;
        if (this.creation == null) {
            this.creation = new Date();
        }
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
    @Transient
    public int getQuantiteEffective() {
        return -quantite;
    }


    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getMagasin() {
        return magasin;
    }

    @Override
    public Date getDateCreation() {
        return creation;
    }

    @Override
    public Date getDatePrevue() {
        return datePrevue;
    }

    @Override
    @Transient
    public Date getDateEffectuee() {
        return dateEffective;
    }

    @Override
    public void setDateEffectuee(Date dateEffectuee) {
        this.dateEffective = dateEffectuee;
    }

    public void setDatePrevue(Date datePrevue) {
        this.datePrevue = datePrevue;
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

    @Override
    public int getQuantite() {
        return quantite;
    }
}
