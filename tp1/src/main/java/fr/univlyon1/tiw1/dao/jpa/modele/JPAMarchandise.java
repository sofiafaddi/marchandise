package fr.univlyon1.tiw1.dao.jpa.modele;

import fr.univlyon1.tiw1.metier.spec.Marchandise;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by ecoquery on 12/07/2017.
 */
@Entity
@Table(name = "marchandise")
@NamedQueries({@NamedQuery(name = "allMarchandises", query = "SELECT m FROM JPAMarchandise m")})
public class JPAMarchandise implements Marchandise {
    @Id
    @Column(name = "ref")
    @GeneratedValue(generator = "hibernate_sequence")
    @SequenceGenerator(name = "hibernate_sequence", allocationSize = 1)
    private Integer reference;
    private String nom;
    @Column(name = "vol_unit")
    private double volumeUnitaire;
    private String description;

    /**
     * Empty constructor for JPA
     */
    public JPAMarchandise() {
        //For JPA
    }

    /**
     * Nouvelle marchandise
     *
     * @param reference      la référence de la marchandise
     * @param nom            le nom
     * @param volumeUnitaire le volume unitaire
     * @param description    la description
     */
    public JPAMarchandise(Integer reference, String nom, double volumeUnitaire, String description) {
        Integer objectReference = reference;
        if (reference != null && reference == -1) {
            objectReference = null;
        }
        this.reference = objectReference;
        this.nom = nom;
        this.volumeUnitaire = volumeUnitaire;
        this.description = description;
    }

    /**
     * Clone constructor
     *
     * @param marchandise la marchandise à cloner
     */
    public JPAMarchandise(Marchandise marchandise) {
        this(marchandise.getReference() == -1 ? null : marchandise.getReference(),
                marchandise.getNom(), marchandise.getVolumeUnitaire(),
                marchandise.getDescription());
    }

    @Override
    public int getReference() {
        return reference == null ? -1 : reference;
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

    public void setReference(Integer reference) {
        this.reference = reference;
    }

    /**
     * Conbvertit une marchandise en marchandise pouvant être gérée par JPA
     * @param marchandise la marchandise à convertir
     * @return la marchandise convertie ou l'originale si aucune conversion n'est nécessaire.
     */
    public static JPAMarchandise asJPA(Marchandise marchandise) {
        if (marchandise instanceof JPAMarchandise) {
            return (JPAMarchandise) marchandise;
        } else {
            return new JPAMarchandise(marchandise);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JPAMarchandise that = (JPAMarchandise) o;
        return Objects.equals(reference, that.reference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference);
    }
}
