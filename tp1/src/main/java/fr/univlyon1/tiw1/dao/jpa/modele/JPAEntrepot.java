package fr.univlyon1.tiw1.dao.jpa.modele;

import fr.univlyon1.tiw1.metier.spec.Entrepot;
import fr.univlyon1.tiw1.metier.spec.Marchandise;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by ecoquery on 12/07/2017.
 */
@Entity
@Table(name = "entrepot")
@NamedQueries({@NamedQuery(name = "allEntrepots", query = "SELECT e FROM JPAEntrepot e")})
public class JPAEntrepot implements Entrepot {
    @Id
    private String nom;
    private double capacite;

    @ElementCollection
    @JoinTable(name = "stock", joinColumns = {@JoinColumn(name = "nom_e")})
    @Column(name = "quantite")
    @MapKeyJoinColumn(name = "ref_m")
    private Map<JPAMarchandise, Integer> quantites = new HashMap<>();

    /**
     * Empty constructor for JPA
     */
    public JPAEntrepot() {
        // for JPA
    }

    /**
     * Cree un nouvel entrepot
     *
     * @param nom      le nom
     * @param capacite la capacite maximale
     */
    public JPAEntrepot(String nom, double capacite) {
        this.nom = nom;
        this.capacite = capacite;
    }

    /**
     * Constructeur de clone
     *
     * @param entrepot l'entrepot à cloner
     */
    public JPAEntrepot(Entrepot entrepot) {
        this(entrepot.getNom(), entrepot.getCapacite());
        for (Marchandise m : entrepot.getMarchandisesStockees()) {
            ajoute(m, entrepot.getStock(m));
        }
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public double getCapacite() {
        return capacite;
    }

    @Override
    public double getOccupation() {
        return quantites.entrySet().stream()
                .mapToDouble(e -> e.getKey().getVolumeUnitaire() * e.getValue())
                .sum();
    }

    @Override
    public Collection<Marchandise> getMarchandisesStockees() {
        return Collections.unmodifiableCollection(quantites.keySet());
    }

    public void setCapacite(double capacite) {
        this.capacite = capacite;
    }

    @Override
    public int getStock(Marchandise marchandise) {
        JPAMarchandise asJPA = JPAMarchandise.asJPA(marchandise);
        return quantites.containsKey(asJPA) ?
                quantites.get(asJPA) : 0;
    }

    @Override
    public void ajoute(Marchandise m, int quantite) {
        JPAMarchandise asJPA = JPAMarchandise.asJPA(m);
        quantites.put(asJPA, quantite + getStock(asJPA));
    }

    @Override
    public void supprime(Marchandise m, int quantite) {
        JPAMarchandise asJPA = JPAMarchandise.asJPA(m);
        int actuel = getStock(asJPA);
        if (actuel <= quantite) {
            quantites.remove(asJPA);
        } else {
            quantites.put(asJPA, actuel - quantite);
        }
    }

    /**
     * Convertit un entrepot en entrepot JPA.
     * Renvoie l'entrepot passé en argument s'il s'agit déjà d'un entrepot JPA.
     *
     * @param entrepot l'entrepot à convertir
     * @return le résultat de la conversion ou l'entrepot de départ si la conversion est inutile.
     */
    public static JPAEntrepot asJPA(Entrepot entrepot) {
        return entrepot instanceof JPAEntrepot ?
                (JPAEntrepot) entrepot : new JPAEntrepot(entrepot);
    }

    /**
     * Change le stock d'un entrepot
     * @param newStock le nouvel état du stock
     */
    public void updateStock(Map<JPAMarchandise, Integer> newStock) {
        Collection<JPAMarchandise> toRemove =
                quantites.keySet().stream()
                        .filter(m -> !newStock.containsKey(m))
                        .collect(Collectors.toList());
        toRemove.stream().forEach(quantites::remove);
        for (Map.Entry<JPAMarchandise, Integer> entry : newStock.entrySet()) {
            if (getStock(entry.getKey()) != entry.getValue()) {
                quantites.put(entry.getKey(), entry.getValue());
            }
        }
    }

    @Override
    public String toString() {
        return "Entrepot{" +
                "nom='" + nom + '\'' +
                ", capacite=" + capacite +
                ", quantites=" + quantites +
                '}';
    }
}
