package fr.univlyon1.tiw1.metier.base.spec;

import fr.univlyon1.tiw1.metier.spec.*;
import fr.univlyon1.tiw1.metier.spec.dao.ApprovisionnementDAO;
import fr.univlyon1.tiw1.metier.spec.dao.EntrepotDAO;
import fr.univlyon1.tiw1.metier.spec.dao.LivraisonDAO;
import fr.univlyon1.tiw1.metier.spec.dao.MarchandiseDAO;
import fr.univlyon1.tiw1.metier.spec.dto.EntrepotDTO;
import fr.univlyon1.tiw1.metier.spec.dto.MarchandiseDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by ecoquery on 07/07/2017.
 */
public abstract class AbstractEntrepotOperationsTest {


    protected EntrepotOperations impl;
    protected EntrepotDAO edao;
    protected LivraisonDAO ldao;
    protected ApprovisionnementDAO adao;
    protected MarchandiseDAO mdao;
    protected Fixture baseFixture;

    /**
     * Classe contenant les donnéees de test
     */
    protected static class Fixture {
        /**
         * entrepots
         */
        private List<Entrepot> entrepots = new ArrayList<>();
        /**
         * marchandises
         */
        private List<Marchandise> marchandises = new ArrayList<>();
        /**
         * livraisons
         */
        private List<Livraison> livraisons = new ArrayList<>();
        /**
         * approvisionnements
         */
        private List<Approvisionnement> approvisionnements = new ArrayList<>();

        public List<Entrepot> getEntrepots() {
            return entrepots;
        }

        public List<Marchandise> getMarchandises() {
            return marchandises;
        }

        public List<Livraison> getLivraisons() {
            return livraisons;
        }

        public List<Approvisionnement> getApprovisionnements() {
            return approvisionnements;
        }
    }

    /**
     * Créée une date à partir d'une chaîne de caractères
     *
     * @param in la représentation de la date
     * @return livraisons'objet date
     */
    public static Date date(String in) {
        return Date.from(LocalDate.parse(in).atTime(LocalTime.parse("12:00")).atOffset(ZoneOffset.UTC).toInstant());
    }


    protected abstract MarchandiseDAO getMarchandiseDAO();

    protected abstract ApprovisionnementDAO getApprovionnementDAO();

    protected abstract LivraisonDAO getLivraisonDAO();

    protected abstract EntrepotDAO getEntrepotDAO();

    /**
     * initialisation du test. appelle {@link #setUpFixtures()}
     *
     * @throws OperationFailedException
     */
    @Before
    public final void setUp() throws OperationFailedException {
        edao = getEntrepotDAO();
        ldao = getLivraisonDAO();
        adao = getApprovionnementDAO();
        mdao = getMarchandiseDAO();
        impl = getEntrepotOperations(edao, ldao, adao, mdao);
        setUpFixtures();
    }

    protected abstract EntrepotOperations getEntrepotOperations(EntrepotDAO edao, LivraisonDAO ldao, ApprovisionnementDAO adao, MarchandiseDAO mdao);

    /**
     * Initialise les fixtures
     */
    protected void setUpFixtures() {
        baseFixture = new Fixture();
        baseFixture.getEntrepots().add(new EntrepotDTO("entrepot1", 15.0));
        baseFixture.getEntrepots().add(new EntrepotDTO("entrepot2", 20.0));
        baseFixture.getMarchandises().add(new MarchandiseDTO(1, "marchandise1", 3.0, "desc1"));
        baseFixture.getMarchandises().add(new MarchandiseDTO(2, "marchandise2", 5.0, "desc2"));
    }

    protected void createEntrepotMarchandises(Fixture fixture) throws OperationFailedException {
        for (Entrepot e : fixture.entrepots) {
            edao.createOrUpdate(e);
        }
        for (Marchandise m : fixture.marchandises) {
            mdao.createOrUpdate(m);
        }
    }

    /**
     * Teste la création d'entrepôts
     *
     * @throws OperationFailedException
     */
    @Test
    public void createGetEntrepot() throws OperationFailedException {
        for (Entrepot e : baseFixture.getEntrepots()) {
            impl.createOrUpdate(e);
            assertNotNull(impl.getByNom(e.getNom()));
            Assert.assertEquals(e.getNom(), impl.getByNom(e.getNom()).getNom());
            Assert.assertEquals(e.getCapacite(), impl.getByNom(e.getNom()).getCapacite(), 0.1);
        }
    }

    /**
     * Test approvisionnement
     *
     * @throws OperationFailedException
     */
    @Test
    public void creeApprovisionnementLivraisonOK() throws OperationFailedException {
        createEntrepotMarchandises(baseFixture);
        Entrepot e1 = impl.getByNom("entrepot1");
        Marchandise m1 = mdao.findByRef(1);
        Approvisionnement a1 = impl.creeApprovisionnement(e1, m1, "f1", 3, date("2017-07-05"));
        Livraison l1 = impl.creeLivraison(e1, m1, "mag1", 2, date("2017-07-06"));
        Approvisionnement a2 = impl.creeApprovisionnement(e1, m1, "f2", 4, date("2017-07-07"));
        Livraison l2 = impl.creeLivraison(e1, m1, "mag2", 5, date("2017-07-08"));
        impl.receptionner(a1, date("2017-07-05"));
        assertEquals(3, e1.getStock(m1));
        impl.livrer(l1, date("2017-07-06"));
        assertEquals(1, e1.getStock(m1));
        assertEquals(0, e1.getStock(mdao.findByRef(2)));
        impl.receptionner(a2, date("2017-07-07"));
        assertEquals(5, e1.getStock(m1));
        impl.livrer(l2, date("2017-07-08"));
        assertEquals(0, e1.getStock(m1));
    }
}
