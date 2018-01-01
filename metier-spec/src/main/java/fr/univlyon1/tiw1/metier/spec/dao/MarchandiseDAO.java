package fr.univlyon1.tiw1.metier.spec.dao;

import fr.univlyon1.tiw1.metier.spec.Marchandise;
import fr.univlyon1.tiw1.metier.spec.OperationFailedException;

import java.util.Collection;

/**
 * Specifie les opérations d'ajout, de récupération, de mise-à-jour et de suppression pour les marchandises.
 * Created by ecoquery on 03/07/2017.
 */
public interface MarchandiseDAO {
    /**
     * Trouve une marchandise à partir de sa référence.
     *
     * @param ref la référence
     * @return la marchandise ou null si elle n'est pas trouvée
     */
    Marchandise findByRef(int ref);

    /**
     * Toutes les marchandises
     *
     * @return toutes les marchandises accessibles par ce DAO.
     */
    Collection<Marchandise> getAllMarchandises();

    /**
     * Ajoute ou met-à-jour une marchandise.
     * Si la référence de la marchandise vaut -1, le dao doit lui attribuer une nouvelle référence.
     *
     * @param marchandise la marchandise à mettre à jour
     * @return la marchandise passée en argument ou sa représentation interne au DAO
     * @throws OperationFailedException si la mise à jour échoue
     */
    Marchandise createOrUpdate(Marchandise marchandise) throws OperationFailedException;

    /**
     * Supprime une marchandise
     *
     * @param marchandise la marchandise à supprimer
     * @throws OperationFailedException si la marchandise ne peut pas être supprimée.
     */
    void remove(Marchandise marchandise) throws OperationFailedException;
}
