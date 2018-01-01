package fr.univlyon1.tiw1.metier.spec.dao;

import fr.univlyon1.tiw1.metier.spec.Approvisionnement;
import fr.univlyon1.tiw1.metier.spec.OperationFailedException;

import java.util.Collection;

/**
 * Operations autour du stockage des données des approvisionnements
 * <p>
 * Created by ecoquery on 03/07/2017.
 */
public interface ApprovisionnementDAO {
    /**
     * Charge un approvisionnement à partir de son identifiant
     *
     * @param id l'identifiant de l'approvisionnement
     * @return l'approvisionnement, ou null s'il n'y en a pas qui corresponde.
     */
    Approvisionnement findById(int id);

    /**
     * Les approvisionnements d'un entrepôt.
     *
     * @param nom le nom de l'entrepôt
     * @return la collection des approvisionnements de l'entrepôt.
     */
    Collection<Approvisionnement> fromEntrepot(String nom);

    /**
     * Les approvisionnements pour une marchandise
     *
     * @param ref la référence de la marchandise
     * @return la collection des approvisionnements concernant la marchandise
     */
    Collection<Approvisionnement> fromMarchandise(int ref);

    /**
     * Les approvisionnements pour un fournisseur
     *
     * @param nom le nom du fournisseur
     * @return la collection des approvisionnements auprès du fournisseur
     */
    Collection<Approvisionnement> fromFournisseur(String nom);

    /**
     * Ajoute ou mets à jour un approvisionnement.
     *
     * @param approvisionnement l'approvisionnement à ajouter ou à mettre à jour.
     * @return l'approvionnement passé en argument ou sa représentation interne au DAO
     * @throws OperationFailedException si la mise à jour ou l'ajout n'a pas pu être fait.
     */
    Approvisionnement createOrUpdate(Approvisionnement approvisionnement) throws OperationFailedException;

    /**
     * Supprime un approvisionnement
     *
     * @param approvisionnement l'approvisionnement à supprimer
     * @throws OperationFailedException si la suppression n'a pas pu être effectuée
     */
    void remove(Approvisionnement approvisionnement) throws OperationFailedException;


    /**
     * Tous les approvisionnements
     *
     * @return Tous les approvisionnements
     * @throws OperationFailedException si le chargement des livraisons échoue.
     */
    Collection<Approvisionnement> getAllApprovisionnements() throws OperationFailedException;
}
