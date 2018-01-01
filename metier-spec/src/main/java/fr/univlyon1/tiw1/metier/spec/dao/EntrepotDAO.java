package fr.univlyon1.tiw1.metier.spec.dao;

import fr.univlyon1.tiw1.metier.spec.Entrepot;
import fr.univlyon1.tiw1.metier.spec.OperationFailedException;

import java.util.Collection;

/**
 * Specifie les opérations d'ajout, de récupération, de mise-à-jour et de suppression pour les entrepôts.
 * Created by ecoquery on 03/07/2017.
 */
public interface EntrepotDAO {
    /**
     * Trouve un entrepôt par son nom.
     *
     * @param nom Le nom de l'entrepôt.
     * @return l'entrepôt cherché, ou null s'il n'y en a pas.
     */
    Entrepot findByNom(String nom);

    /**
     * Tous les entrepôts
     *
     * @return renvoie tous les entrepôts accessibles à travers ce DAO.
     */
    Collection<Entrepot> getAllEntrepots();

    /**
     * Met à jour ou créée un entrepôt.
     *
     * @param entrepot L'entrepôt à mettre-à-jour ou à ajouter
     * @return l'entrepot passé en argument ou sa représentation interne au DAO
     * @throws OperationFailedException si la mise a jour échoue
     */
    Entrepot createOrUpdate(Entrepot entrepot) throws OperationFailedException;

    /**
     * Supprime un entrepôt.
     *
     * @param entrepot l'entrepôt à supprimer.
     * @throws OperationFailedException si la suppressiona échoué.
     */
    void remove(Entrepot entrepot) throws OperationFailedException;
}
