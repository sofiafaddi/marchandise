package fr.univlyon1.tiw1.metier.base;

import fr.univlyon1.tiw1.metier.spec.*;
import fr.univlyon1.tiw1.metier.spec.dao.ApprovisionnementDAO;
import fr.univlyon1.tiw1.metier.spec.dao.EntrepotDAO;
import fr.univlyon1.tiw1.metier.spec.dao.LivraisonDAO;
import fr.univlyon1.tiw1.metier.spec.dao.MarchandiseDAO;
import fr.univlyon1.tiw1.metier.spec.dto.ApprovisionnementDTO;
import fr.univlyon1.tiw1.metier.spec.dto.LivraisonDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Implementation simple des operations d'un entrepot.
 * <p>
 * Created by ecoquery on 03/07/2017.
 */
public class EntrepotOperationsImpl implements EntrepotOperations {
    protected EntrepotDAO edao;
    private LivraisonDAO ldao;
    private ApprovisionnementDAO adao;
    private MarchandiseDAO mdao;

    /**
     * Cree une implémentation d'opérations sur les entrepots
     *
     * @param edao le dao entrepot
     * @param ldao le dao livraisons
     * @param adao le dao approvisionnement
     * @param mdao le dao marchandise
     */
    public EntrepotOperationsImpl(EntrepotDAO edao, LivraisonDAO ldao, ApprovisionnementDAO adao, MarchandiseDAO mdao) {
        this.edao = edao;
        this.ldao = ldao;
        this.adao = adao;
        this.mdao = mdao;
    }

    @Override
    public void createOrUpdate(Entrepot entrepot) throws OperationFailedException {
        edao.createOrUpdate(entrepot);
    }

    @Override
    public Entrepot getByNom(String nom) {
        return edao.findByNom(nom);
    }

    private boolean verifieOperationSurStocks(Entrepot entrepot, OperationSurStock operationSurStock) {
        Collection<OperationSurStock> ops = new ArrayList<>();
        ops.addAll(ldao.fromEntrepot(entrepot.getNom()));
        ops.addAll(adao.fromEntrepot(entrepot.getNom()));
        if (operationSurStock != null) {
            ops.add(operationSurStock);
        }
        return new Stock(mdao).checkOperations(entrepot.getCapacite(), ops);
    }

    @Override
    public Livraison creeLivraison(Entrepot entrepot, Marchandise marchandise, String magasin, int quantite, Date datePrevue) throws OperationFailedException {
        LivraisonDTO livraison = new LivraisonDTO(-1, marchandise.getReference(), entrepot.getNom(), magasin, quantite, null, datePrevue);
        if (verifieOperationSurStocks(entrepot, livraison)) {
            return ldao.createOrUpdate(livraison);
        } else {
            throw new OperationFailedException("Probleme de capacite dans livraisons'entrepot lors de la creation d'une livraison: " + livraison.toString());
        }
    }

    @Override
    public Approvisionnement creeApprovisionnement(Entrepot entrepot, Marchandise marchandise, String fournisseur, int quantite, Date datePrevue) throws OperationFailedException {
        ApprovisionnementDTO approvisionnement = new ApprovisionnementDTO(-1, marchandise.getReference(), entrepot.getNom(), fournisseur, quantite, null, datePrevue);
        if (verifieOperationSurStocks(entrepot, approvisionnement)) {
            return adao.createOrUpdate(approvisionnement);
        } else {
            throw new OperationFailedException("Probleme de capacite dans livraisons'entrepot lors de la creation d'un approvisionnement " + approvisionnement.toString());
        }
    }

    @Override
    public void livrer(Livraison livraison, Date dateLivraison) throws OperationFailedException {
        Livraison l = ldao.findById(livraison.getId());
        boolean created = false;
        if (l == null) {
            l = ldao.createOrUpdate(livraison);
            created = true;
        }
        Date ancienneDate = l.getDateEffectuee();
        l.setDateEffectuee(dateLivraison);
        if (verifieOperationSurStocks(edao.findByNom(l.getNomEntrepot()), null)) {
            ldao.createOrUpdate(l);
            edao.findByNom(l.getNomEntrepot()).supprime(mdao.findByRef(l.getRefMarchandise()), l.getQuantite());
        } else {
            if (created) {
                ldao.remove(l);
            }
            l.setDateEffectuee(ancienneDate);
            throw new OperationFailedException("Probleme de capacite lors de la mise à jour d'une livraison: " + livraison.toString());
        }
    }

    @Override
    public void receptionner(Approvisionnement approvisionnement, Date dateReception) throws OperationFailedException {
        Approvisionnement a = adao.findById(approvisionnement.getId());
        boolean created = false;
        if (a == null) {
            a = adao.createOrUpdate(approvisionnement);
            created = true;
        }
        Date ancienneDate = a.getDateEffectuee();
        a.setDateEffectuee(dateReception);
        if (verifieOperationSurStocks(edao.findByNom(a.getNomEntrepot()), null)) {
            adao.createOrUpdate(a);
            edao.findByNom(a.getNomEntrepot()).ajoute(mdao.findByRef(a.getRefMarchandise()), a.getQuantite());
        } else {
            if (created) {
                adao.remove(a);
            }
            a.setDateEffectuee(ancienneDate);
            throw new OperationFailedException("Probleme de capacite lors de la mise à jour d'un approvisionnement: " + approvisionnement.toString());
        }
    }
}
