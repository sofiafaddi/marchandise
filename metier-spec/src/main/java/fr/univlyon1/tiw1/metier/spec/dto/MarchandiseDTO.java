package fr.univlyon1.tiw1.metier.spec.dto;

import fr.univlyon1.tiw1.metier.spec.Marchandise;

import java.util.Objects;

/**
 * Implémentation basique d'une marchandise pour l'échange de données entre composants
 * Created by ecoquery on 03/07/2017.
 */
public class MarchandiseDTO implements Marchandise {
    private int ref;
    private String nom;
    private double volumeUnitaire;
    private String description;

    /**
     * Crée une marchandise à partir de ses opérations de base.
     *
     * @param ref            la référence
     * @param nom            le nom
     * @param volumeUnitaire le volume unitaire
     * @param description    la description
     */
    public MarchandiseDTO(int ref, String nom, double volumeUnitaire, String description) {
        this.ref = ref;
        this.nom = nom;
        this.volumeUnitaire = volumeUnitaire;
        this.description = description;
    }

    /**
     * Clone une marchandise.
     *
     * @param marchandise la marchandise à cloner
     */
    public MarchandiseDTO(Marchandise marchandise) {
        this(marchandise.getReference(), marchandise.getNom(), marchandise.getVolumeUnitaire(), marchandise.getDescription());
    }

    @Override
    public int getReference() {
        return ref;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public double getVolumeUnitaire() {
        return volumeUnitaire;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MarchandiseDTO that = (MarchandiseDTO) o;
        return ref == that.ref;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ref);
    }


    /**
     * Convertit au besoin une marchandise en dto
     *
     * @param marchandise la marchandise
     * @return le dto
     */
    public static MarchandiseDTO convert(Marchandise marchandise) {
        return marchandise instanceof MarchandiseDTO ? (MarchandiseDTO) marchandise : new MarchandiseDTO(marchandise);
    }

    public void setRef(int ref) {
        this.ref = ref;
    }
}
