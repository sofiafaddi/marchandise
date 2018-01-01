package fr.univlyon1.tiw1.metier.spec;

import java.util.Date;

/**
 * Représente un approvisionnement de l'entrepôt (suite à une commande auprès d'un fournisseur).
 * <p>
 * Created by ecoquery on 23/06/2017.
 */
public interface Approvisionnement extends OperationSurStock {
    /**
     * L'identifiant de l'operation.
     *
     * @return un entier identifant de manière unique cet approvisionnement.
     */
    int getId();

    /**
     * Le nom du fournisseur source de cet approvisionnement.
     *
     * @return le nom du fournisseur.
     */
    String getFournisseur();

    /**
     * La date de création de l'approvisionnement.
     *
     * @return la date de création de l'approvisionnement.
     */
    Date getDateCreation();

    /**
     * La date à laquelle l'approvisionnement a été effectué.
     *
     * @return la date à laquelle l'approvisionnement a été effectué, ou null s'il n'a pas encore eu lieu.
     */
    Date getDateEffectuee();

    /**
     * Mets à jour la date effective pour l'approvisionnmenent
     *
     * @param dateReception la nouvelle date ou l'approvisionnement a été effectué.
     */
    void setDateEffectuee(Date dateReception);

    /**
     * La date prévue pour l'approvisionnement.
     *
     * @return la date prévue pour l'approvisionnement.
     */
    Date getDatePrevue();

    @Override
    default Date dateEffet() {
        return isEffectuee() ? getDateEffectuee() : getDatePrevue();
    }

    @Override
    default boolean isEffectuee() {
        return getDateEffectuee() != null;
    }


    /**
     * La quantité d'unités de marchandises à ajouter/supprimer de l'entrepot.
     *
     * @return la quantité de marchandises.
     */
    int getQuantite();

    @Override
    default int getQuantiteEffective() {
        return getQuantite();
    }
}
