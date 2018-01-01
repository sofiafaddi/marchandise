package fr.univlyon1.tiw1.tp3.services;

import fr.univlyon1.tiw1.metier.spec.*;
import fr.univlyon1.tiw1.tp3.modele.EntrepotEntity;
import fr.univlyon1.tiw1.tp3.modele.MarchandiseEntity;
import fr.univlyon1.tiw1.tp3.service.ApprovisionnementService;
import fr.univlyon1.tiw1.tp3.service.EntrepotService;
import fr.univlyon1.tiw1.tp3.service.LivraisonService;
import fr.univlyon1.tiw1.tp3.service.MarchandiseService;
import fr.univlyon1.tiw1.tp3.service.exception.DataNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Amaia Nazábal
 * @version 1.0
 * @since 1.0 12/3/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class LivraisonServiceTest {

    @Autowired
    private LivraisonService livraisonService;

    @Autowired
    private ApprovisionnementService approvisionnementService;

    @Autowired
    private EntrepotService entrepotService;


    @Autowired
    private MarchandiseService marchandiseService;

    private Marchandise marchandise1 = new MarchandiseEntity(null, "marchandise1", 10, "marchandise1");
    private Marchandise marchandise2 = new MarchandiseEntity(null, "marchandise2", 5, "marchandise2");
    private Entrepot entrepot1 = new EntrepotEntity("entrepot1", 90);
    private Entrepot entrepot2 = new EntrepotEntity("entrepot2", 75);
    private Approvisionnement approvisionnement1;
    private Approvisionnement approvisionnement2;

    private Livraison livraison1;
    private Livraison livraison2;

    private List<Livraison> livraisonList;

    private boolean flag;;

    @Before
    public void setupData() throws OperationFailedException {
        if (!flag) {

            marchandise1 = marchandiseService.createOrUpdate(marchandise1);
            marchandise2 = marchandiseService.createOrUpdate(marchandise2);

            entrepotService.createOrUpdate(entrepot1);
            entrepotService.createOrUpdate(entrepot2);

            approvisionnement1 = entrepotService.creeApprovisionnement(entrepot1, marchandise1, "fournisseur1", 6, new Date());
            approvisionnement2 = entrepotService.creeApprovisionnement(entrepot2, marchandise2, "fournisseur1", 7, new Date());

            livraison1 = entrepotService.creeLivraison(entrepot1,  marchandise1,"livraison 1", 2, new Date());
            livraison2 = entrepotService.creeLivraison(entrepot2,  marchandise2,"livraison 2", 1, new Date());

            livraisonList = Arrays.asList(livraison1, livraison2);
            flag = true;
        }
    }

    @After
    public void removeData() throws OperationFailedException, DataNotFoundException {
        if (flag) {
            try {
                livraisonService.remove(livraison2.getId());
                livraisonService.remove(livraison1.getId());

            } catch (OperationFailedException | DataNotFoundException e) {
                // pass out
            }


            approvisionnementService.remove(approvisionnement1.getId());
            approvisionnementService.remove(approvisionnement2.getId());

            entrepotService.remove(entrepot1.getNom());
            entrepotService.remove(entrepot2.getNom());

            marchandiseService.remove(marchandise2.getReference());
            marchandiseService.remove(marchandise1.getReference());

            flag = false;
        }
    }

    @Test
    public void livrerTest() {
        Exception e = null;
        livraison1.setDateEffectuee(new Date());
        try {
            entrepotService.livrer(livraison1, livraison1.dateEffet());
        } catch (OperationFailedException ex) {
            e = ex;
        }

        assertNull(e);
    }

    @Test
    public void getByIdTest() throws DataNotFoundException {
        Livraison livraison = livraisonService.getById(livraison1.getId());

        assertEquals(livraison.getId(), livraison1.getId());
        assertEquals(livraison.getMagasin(), livraison1.getMagasin());
        assertEquals(livraison.getQuantite(), livraison1.getQuantite());
        assertEquals(livraison.getNomEntrepot(), livraison1.getNomEntrepot());
        assertEquals(livraison.getRefMarchandise(), livraison1.getRefMarchandise());
    }


    @Test
    public void getAllTest() throws OperationFailedException {
        Collection<Livraison> collection = livraisonService.getAll();
        assertEquals(collection.size(), livraisonList.size());
        assert(collection.contains(livraisonList.get(0)));
        assert(collection.contains(livraisonList.get(1)));
    }


    @Test
    public void removeTest() throws OperationFailedException, DataNotFoundException {
        livraisonService.remove(livraison1.getId());
        Collection<Livraison> collection = livraisonService.getAll();
        assert(!collection.contains(livraison1));
        assert(collection.size() < livraisonList.size());
    }
}
