package fr.univlyon1.tiw1.metier.spec;

/**
 * Spécifie les opérations sur les marchandises.
 * Remarque: ces opérations sont de type CRUD.
 * <p>
 * Created by ecoquery on 27/06/2017.
 */
public interface MarchandiseOperations {

    /**
     * Crée une nouvelle marchandise o ula met à jour si elle est déjà connue du système.
     *
     * @param marchandise la marchandise à créer ou à mettre à jour.
     * @throws OperationFailedException si la création ou la mise à jour échoue.
     */
    Marchandise createOrUpdate(Marchandise marchandise) throws OperationFailedException;

    /**
     * Récupère une marchandise.
     *
     * @param ref la référence de la marchandise cherchée
     * @return Une représentation de cette marchandise, ou null si elle n'existe pas.
     */
    Marchandise getByRef(int ref);

    /**
     * Supprime une marchandise. Il est interdit de supprimer un marchandise référencée dans un entrepôt, un approvisionnement ou une livraison.
     *
     * @param marchandise la marchandise à supprimer
     * @throws OperationFailedException si la suppression ne peut pas avoir lieu
     */
    void delete(Marchandise marchandise) throws OperationFailedException;

}
