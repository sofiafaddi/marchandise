package fr.univlyon1.tiw1.metier.spec;

import java.util.Date;

/**
 * Représente un livraison d'un entrepôt vers un magasin.
 * <p>
 * Created by ecoquery on 23/06/2017.
 */
public interface Livraison extends OperationSurStock {
    /**
     * L'identifiant de la livraison.
     *
     * @return un entier identifant de manière unique cette livraison.
     */
    int getId();

    /**
     * Le nom du magasin auquel cette livraison est destinée.
     *
     * @return le nom du magasin.
     */
    String getMagasin();

    /**
     * La date de création de la livraison.
     *
     * @return la date de création de la livraison.
     */
    Date getDateCreation();

    @Override
    default boolean isEffectuee() {
        return getDateEffectuee() != null;
    }

    /**
     * La date prévue pour la livraison.
     *
     * @return la date de livraison prévue.
     */
    Date getDatePrevue();

    /**
     * La date à laquelle la livraison a été effectuée
     *
     * @return la date de livraison ou null si la livraison n'a pas encore eu lieu.
     */
    Date getDateEffectuee();

    /**
     * Change la date à laquelle la livraison a été effectuée.
     *
     * @param dateEffectuee la date à laquelle la livraison a été effectuée
     */
    void setDateEffectuee(Date dateEffectuee);

    @Override
    default Date dateEffet() {
        return isEffectuee() ? getDateEffectuee() : getDatePrevue();
    }


    /**
     * La quantité d'unités de marchandises à ajouter/supprimer de l'entrepot.
     *
     * @return la quantité de marchandises.
     */
    int getQuantite();

    @Override
    default int getQuantiteEffective() {
        return -getQuantite();
    }
}
