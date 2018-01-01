package fr.univlyon1.tiw1.metier.base;

import fr.univlyon1.tiw1.metier.spec.OperationSurStock;
import fr.univlyon1.tiw1.metier.spec.dao.MarchandiseDAO;

import java.util.*;

/**
 * Created by ecoquery on 04/07/2017.
 */
public class Stock {

    private MarchandiseDAO marchandiseDAO;

    /**
     * Construit un composant de vérification de stock.
     *
     * @param marchandiseDAO le dao permettant de récupérer les informations sur les marchandises.
     */
    public Stock(MarchandiseDAO marchandiseDAO) {
        this.marchandiseDAO = marchandiseDAO;
    }

    /**
     * Vérifie qu'une suite d'opération sur un entrepôt peut être exécutée sans déoassement de la capacité de livraisons'entrepôt.
     *
     * @param maxCapacity la capacité maximale
     * @param ops         les opérations
     * @param sorted      true si les opérations sont déjà triées par date.
     * @return true si la suite est cohérente.
     */
    public boolean checkOperations(double maxCapacity, Collection<OperationSurStock> ops, boolean sorted) {
        List<OperationSurStock> l = new ArrayList<>(ops);
        if (!sorted) {
            l.sort((o1, o2) -> o1.compareTo(o2));
        }
        return checkSortedOperations(maxCapacity, l);
    }

    /**
     * Vérifie qu'une suite d'opération sur un entrepôt peut être exécutée sans déoassement de la capacité de livraisons'entrepôt.
     * Cette version re-trie les opérations par date.
     *
     * @param maxCapacity la capacité maximale
     * @param ops         les opérations
     * @return true si la suite est cohérente.
     */
    public boolean checkOperations(double maxCapacity, Collection<OperationSurStock> ops) {
        return checkOperations(maxCapacity, ops, false);
    }

    /**
     * Vérifie qu'une suite d'opération <strong>triées par date</strong> sur un entrepôt peut être exécutée
     * sans déoassement de la capacité de livraisons'entrepôt.
     *
     * @param maxCapacity la capacité maximale
     * @param ops         les opérations
     * @return true si la suite est cohérente.
     */
    public boolean checkSortedOperations(double maxCapacity, List<OperationSurStock> ops) {
        return checkCapacity(maxCapacity, ops) && checkQuantities(ops);
    }

    private boolean checkCapacity(double maxCapacity, List<OperationSurStock> ops) {
        double capacity = 0;
        for (OperationSurStock o : ops) {
            capacity += marchandiseDAO.findByRef(o.getRefMarchandise()).getVolumeUnitaire() * o.getQuantiteEffective();
            if (capacity < 0.0 || capacity > maxCapacity) {
                return false;
            }
        }
        return true;
    }

    private boolean checkQuantities(List<OperationSurStock> ops) {
        Map<Integer, Integer> quantities = new HashMap<>();
        ops.stream().forEach(o -> quantities.put(o.getRefMarchandise(), 0));
        for (OperationSurStock o : ops) {
            int qte = quantities.get(o.getRefMarchandise());
            qte += o.getQuantiteEffective();
            if (qte < 0) {
                return false;
            }
            quantities.put(o.getRefMarchandise(), qte);
        }
        return true;
    }

}
