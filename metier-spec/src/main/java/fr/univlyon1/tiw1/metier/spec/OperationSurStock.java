package fr.univlyon1.tiw1.metier.spec;

import java.util.Comparator;
import java.util.Date;

/**
 * Created by ecoquery on 04/07/2017.
 */
public interface OperationSurStock extends Comparable<OperationSurStock> {

    /**
     * La marchandise concernée par cette operation.
     *
     * @return la référence de la marchandise
     */
    int getRefMarchandise();

    /**
     * L'entrepôt auquel est destiné l'opération.
     *
     * @return le nom de l'entrepot
     */
    String getNomEntrepot();

    /**
     * La quantité d'unités de marchandises à ajouter à l'entrepot.
     *
     * @return la quantité de marchandises. Si la marchandise est retirée, cette quantité est négative.
     */
    int getQuantiteEffective();

    /**
     * Indique si l'opération a été effectuée.
     *
     * @return true si l'opération a été effectuée.
     */
    boolean isEffectuee();

    /**
     * La date à laquelle prendre en compte l'effet de l'opération.
     *
     * @return la date effective si l'opération a eu lieu, la date prévue sinon.
     */
    Date dateEffet();

    @Override
    default int compareTo(OperationSurStock o) {
        return this.dateEffet().compareTo(o.dateEffet());
    }

    /**
     * Renvoie un comparateur pour les opérations sur stock basé sur les dates.
     *
     * @return le comparateur.
     */
    public static Comparator<OperationSurStock> comparator() {
        return (o1, o2) -> o1.compareTo(o2);
    }
}
