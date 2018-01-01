package fr.univlyon1.tiw1.metier.spec;

import java.util.Date;

/**
 * Spécifie les opérations métier liées aux entrepôts.
 * Created by ecoquery on 27/06/2017.
 */
public interface EntrepotOperations {
    /**
     * Met à jour un entrepôt ou le modifie s'il existe déjà.
     *
     * @param entrepot l'entrepôt à créer ou déjà mis-à-jour.
     * @throws OperationFailedException si un problème survient.
     */
    void createOrUpdate(Entrepot entrepot) throws OperationFailedException;

    /**
     * Trouve un entrepôt par son nom.
     *
     * @param nom Le nom de l'entrepôt cherché
     * @return l'entrepôt, ou null s'il n'existe pas.
     */
    Entrepot getByNom(String nom);

    /**
     * Cree une livraison.
     *
     * @param entrepot    l'entrepôt d'où sont expédiées les marchandises
     * @param marchandise la marchandise à expédier
     * @param magasin     le magasin à livrer
     * @param quantite    la quantité de marchandises à livrer
     * @param datePrevue  la date de livraison prévue
     * @return un objet représentant la livraison
     * @throws OperationFailedException si la livraison ne peut être programmée
     */
    Livraison creeLivraison(Entrepot entrepot, Marchandise marchandise, String magasin, int quantite, Date datePrevue) throws OperationFailedException;

    /**
     * Cree un approvisionnement
     *
     * @param entrepot    l'entrepot à approvisionner
     * @param marchandise la marchandise concernée
     * @param fournisseur le fournisseur
     * @param quantite    la quantite de marchandise à réceptionner
     * @param datePrevue  la date prévue pour la réception des marchandises
     * @return un objet représentant l'approvisionnement
     * @throws OperationFailedException si l'approvisionnement ne peut pas être effectué
     */
    Approvisionnement creeApprovisionnement(Entrepot entrepot, Marchandise marchandise, String fournisseur, int quantite, Date datePrevue) throws OperationFailedException;

    /**
     * Indique qu'une livraison a été effectuée à la date donnée
     *
     * @param livraison     la livraison
     * @param dateLivraison la date où la livraison a été effectuée
     * @throws OperationFailedException si la livraison ne peut être effectuée à cause d'un stock trop bas.
     */
    void livrer(Livraison livraison, Date dateLivraison) throws OperationFailedException;

    /**
     * Indique qu'un approvisionnement a été effectué
     *
     * @param approvisionnement L'approvisionnement
     * @param dateReception     la date où les marchandises sont été réceptionnées
     * @throws OperationFailedException si l'aaprovisionnement ne peut être effectué faute de place
     */
    void receptionner(Approvisionnement approvisionnement, Date dateReception) throws OperationFailedException;
}
