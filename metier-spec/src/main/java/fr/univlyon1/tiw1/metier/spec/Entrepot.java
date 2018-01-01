package fr.univlyon1.tiw1.metier.spec;

import java.util.Collection;

/**
 * Représente un entrepôt.
 * <p>
 * Created by ecoquery on 23/06/2017.
 */
public interface Entrepot {
    /**
     * Le nom de l'entrepôt.
     *
     * @return le nom
     */
    String getNom();

    /**
     * La capacité de l'entrepôt en m<sup>3</sup>.
     *
     * @return la capacité maximale de l'entrepôt.
     */
    double getCapacite();

    /**
     * Le volume de marchandises actuellement stocké dans l'entrepôt.
     *
     * @return le volume actuellement stocké.
     */
    double getOccupation();

    /**
     * Les marchandises actuellement stockées dans l'entrepôt.
     * Cette collection est considérée comme immuable.
     *
     * @return les marchandises stockées.
     */
    Collection<Marchandise> getMarchandisesStockees();

    /**
     * Le nombre d'unités de marchandise stockée dans l'entrepôt.
     *
     * @param marchandise La marchandise dont on veut connaître la quantité stockée.
     * @return le nombre d'unité de cette marchandise stockée dans l'entrepôt.
     */
    int getStock(Marchandise marchandise);


    /**
     * Ajoute une quantite d'une marchandise. Ne fait pas de vérification de capacité.
     *
     * @param m        la marchandise
     * @param quantite la quantite à ajouter
     */
    void ajoute(Marchandise m, int quantite);

    /**
     * Supprime une quantite d'une marchandise.
     *
     * @param m        la marchandise
     * @param quantite la quantite à supprimer.
     */
    void supprime(Marchandise m, int quantite);
}
