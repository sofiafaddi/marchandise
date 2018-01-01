package fr.univlyon1.tiw1.metier.spec.dto;

import fr.univlyon1.tiw1.metier.spec.Livraison;

import java.util.Date;
import java.util.Objects;

/**
 * Implémentation basique d'une livraison permettant le transfert d'information entre deux composants.
 * <p>
 * Created by ecoquery on 03/07/2017.
 */
public class LivraisonDTO implements Livraison {
    private int id;
    private int refMarchandise;
    private String nomEntrepot;
    private String magasin;
    private int quantite;
    private Date dateCreation;
    private Date datePrevue;
    private Date dateEffectuee;

    /**
     * Créée une nouvele livraison. La livraison est supposée non effectuée.
     *
     * @param id             l'identifiant de la livraison
     * @param refMarchandise la référence de la marchandise
     * @param nomEntrepot    le nom de l'entrepôt
     * @param magasin        le nom du magasin
     * @param quantite       la quantite
     * @param dateCreation   la date de creation, o la date courante si null
     * @param datePrevue     la date de livraison prévue
     */
    public LivraisonDTO(int id, int refMarchandise, String nomEntrepot, String magasin, int quantite, Date dateCreation, Date datePrevue) {
        this.id = id;
        this.refMarchandise = refMarchandise;
        this.nomEntrepot = nomEntrepot;
        this.magasin = magasin;
        this.quantite = quantite;
        this.dateCreation = dateCreation;
        this.datePrevue = datePrevue;
    }

    /**
     * Contructeur de clone.
     *
     * @param livraison la livraison à cloner.
     */
    public LivraisonDTO(Livraison livraison) {
        this(livraison.getId(), livraison.getRefMarchandise(), livraison.getNomEntrepot(), livraison.getMagasin(),
                livraison.getQuantite(), livraison.getDateCreation(), livraison.getDatePrevue());
        if (livraison.isEffectuee()) {
            this.dateEffectuee = livraison.getDateEffectuee();
        }
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    public String getMagasin() {
        return magasin;
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
        return dateEffectuee;
    }

    @Override
    public void setDateEffectuee(Date dateEffectuee) {
        this.dateEffectuee = dateEffectuee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LivraisonDTO that = (LivraisonDTO) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Convertit une livraison en DTO
     *
     * @param livraison livraison à convertir
     * @return la livraison éventuellement convertie.
     */
    public static LivraisonDTO convert(Livraison livraison) {
        return livraison instanceof LivraisonDTO ? (LivraisonDTO) livraison : new LivraisonDTO(livraison);
    }
}
