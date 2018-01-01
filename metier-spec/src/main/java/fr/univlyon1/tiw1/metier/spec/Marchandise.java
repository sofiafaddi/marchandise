package fr.univlyon1.tiw1.metier.spec;

/**
 * Représent une marchandise (i.e. un type d'objet, avec une référence particulière).
 * <p>
 * Created by ecoquery on 23/06/2017.
 */
public interface Marchandise {
    /**
     * Un entier identifiant de manière unique la marchandise.
     *
     * @return la référence de la marchandise.
     */
    int getReference();

    /**
     * Le nom de la marchandise.
     *
     * @return Le nom de la marchandise.
     */
    String getNom();

    /**
     * Le volume occupé par une unité de cette marchandise.
     *
     * @return le volume unitaire.
     */
    double getVolumeUnitaire();

    /**
     * Une description de la marchandise.
     *
     * @return la description.
     */
    String getDescription();
}
