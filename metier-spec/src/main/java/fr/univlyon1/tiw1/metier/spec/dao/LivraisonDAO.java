package fr.univlyon1.tiw1.metier.spec.dao;

import fr.univlyon1.tiw1.metier.spec.Livraison;
import fr.univlyon1.tiw1.metier.spec.OperationFailedException;

import java.util.Collection;

/**
 * Created by ecoquery on 03/07/2017.
 */
public interface LivraisonDAO {
    /**
     * Charge une livraison à partir de son identifiant
     *
     * @param id l'identifiant de la livraison
     * @return la livraison, ou null s'il n'y en a pas qui corresponde.
     */
    Livraison findById(int id);

    /**
     * les livraisons d'un entrepôt.
     *
     * @param nom le nom de l'entrepôt
     * @return la collection des livraisons de l'entrepôt.
     */
    Collection<Livraison> fromEntrepot(String nom);

    /**
     * les livraisons pour une marchandise
     *
     * @param ref la référence de la marchandise
     * @return la collection des livraisons concernant la marchandise
     */
    Collection<Livraison> fromMarchandise(int ref);

    /**
     * les livraisons pour un magasin
     *
     * @param nom le nom du magasin
     * @return la collection des livraisons auprès du magasin
     */
    Collection<Livraison> fromMagasin(String nom);

    /**
     * Ajoute ou mets à jour une livraison.
     *
     * @param livraison la livraison à ajouter ou à mettre à jour.
     * @return la livraison passée en argument ou sa représentation interne au DAO
     * @throws OperationFailedException si la mise à jour ou l'ajout n'a pas pu être fait.
     */
    Livraison createOrUpdate(Livraison livraison) throws OperationFailedException;

    /**
     * Supprime une livraison
     *
     * @param livraison la livraison à supprimer
     * @throws OperationFailedException si la suppression n'a pas pu être effectuée
     */
    void remove(Livraison livraison) throws OperationFailedException;


    /**
     * Toutes les livraisons
     *
     * @return Toutes les livraisons
     * @throws OperationFailedException si le chargement des livraisons échoue.
     */
    Collection<Livraison> getAllLivraisons() throws OperationFailedException;
}
