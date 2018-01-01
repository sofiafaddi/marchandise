package fr.univlyon1.tiw1.metier.spec.dto;

import fr.univlyon1.tiw1.metier.spec.Entrepot;
import fr.univlyon1.tiw1.metier.spec.Marchandise;

import java.util.*;

/**
 * Created by ecoquery on 03/07/2017.
 */
public class EntrepotDTO implements Entrepot {

    private String nom;
    private double capacite;
    private Map<MarchandiseDTO, Integer> marchandises;

    /**
     * Construit un dto à partir du nom et de la capacité
     *
     * @param nom      le nom
     * @param capacite la capacite
     */
    public EntrepotDTO(String nom, double capacite) {
        this.nom = nom;
        this.capacite = capacite;
        this.marchandises = new HashMap<>();
    }

    /**
     * Clone un entrepot
     *
     * @param entrepot l'entrepot à cloner
     */
    public EntrepotDTO(Entrepot entrepot) {
        this(entrepot.getNom(), entrepot.getCapacite());
        for (Marchandise m : entrepot.getMarchandisesStockees()) {
            marchandises.put(new MarchandiseDTO(m), entrepot.getStock(m));
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
        return marchandises
                .entrySet().stream()
                // volume * quantite pour chaque marchandise
                .mapToDouble(marQte -> marQte.getKey().getVolumeUnitaire() * marQte.getValue())
                .sum();
    }

    @Override
    public Collection<Marchandise> getMarchandisesStockees() {
        return Collections.unmodifiableCollection(marchandises.keySet());
    }

    @Override
    public int getStock(Marchandise marchandise) {
        MarchandiseDTO m = new MarchandiseDTO(marchandise);
        if (marchandises.containsKey(m)) {
            return marchandises.get(m);
        } else {
            return 0;
        }
    }

    @Override
    public void ajoute(Marchandise m, int quantite) {
        marchandises.put(MarchandiseDTO.convert(m), quantite + getStock(m));
    }

    @Override
    public void supprime(Marchandise m, int quantite) {
        marchandises.put(MarchandiseDTO.convert(m), Math.max(getStock(m) - quantite, 0));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EntrepotDTO that = (EntrepotDTO) o;
        return Objects.equals(nom, that.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }

    /**
     * Convertit un entrepot en dto si celui n'est pas déjà un entrepot.
     *
     * @param entrepot l'entrepot à convertir
     * @return l'entrepot éventuellement convertit
     */
    public static EntrepotDTO convert(Entrepot entrepot) {
        return entrepot instanceof EntrepotDTO ? (EntrepotDTO) entrepot : new EntrepotDTO(entrepot);
    }

}
