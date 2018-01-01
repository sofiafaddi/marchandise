package fr.univlyon1.tiw1.tp3.services;

import fr.univlyon1.tiw1.metier.spec.Approvisionnement;
import fr.univlyon1.tiw1.metier.spec.Entrepot;
import fr.univlyon1.tiw1.metier.spec.Marchandise;
import fr.univlyon1.tiw1.metier.spec.OperationFailedException;
import fr.univlyon1.tiw1.tp3.modele.EntrepotEntity;
import fr.univlyon1.tiw1.tp3.modele.MarchandiseEntity;
import fr.univlyon1.tiw1.tp3.service.ApprovisionnementService;
import fr.univlyon1.tiw1.tp3.service.EntrepotService;
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
 * @since 1.0 12/4/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ApprovisionnementServiceTest {

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


    private List<Approvisionnement> approvisionnementList;


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

            approvisionnementList = Arrays.asList(approvisionnement1, approvisionnement2);
            flag = true;
        }
    }

    @After
    public void removeData() throws OperationFailedException, DataNotFoundException {
        if (flag) {
            try {
                approvisionnementService.remove(approvisionnement2.getId());
                approvisionnementService.remove(approvisionnement1.getId());

            } catch (OperationFailedException | DataNotFoundException e) {
                // pass out
            }

            entrepotService.remove(entrepot1.getNom());
            entrepotService.remove(entrepot2.getNom());

            marchandiseService.remove(marchandise2.getReference());
            marchandiseService.remove(marchandise1.getReference());
            flag = false;
        }
    }

    @Test
    public void receptionnerTest() {
        Exception e = null;
        approvisionnement1.setDateEffectuee(new Date());
        try {
            entrepotService.receptionner(approvisionnement1, approvisionnement1.getDateEffectuee());
        } catch (OperationFailedException ex) {
            e = ex;
        }

        assertNull(e);
    }

    @Test
    public void getByIdTest() throws DataNotFoundException {
        Approvisionnement approvisionnement = approvisionnementService.getById(approvisionnement1.getId());

        assertEquals(approvisionnement.getId(), approvisionnement1.getId());
        assertEquals(approvisionnement.getFournisseur(), approvisionnement1.getFournisseur());
        assertEquals(approvisionnement.getQuantite(), approvisionnement1.getQuantite());
        assertEquals(approvisionnement.getNomEntrepot(), approvisionnement1.getNomEntrepot());
        assertEquals(approvisionnement.getRefMarchandise(), approvisionnement1.getRefMarchandise());
    }


    @Test
    public void getAllTest() throws OperationFailedException {
        Collection<Approvisionnement> collection = approvisionnementService.getAll();
        assertEquals(collection.size(), approvisionnementList.size());
        assert(collection.contains(approvisionnementList.get(0)));
        assert(collection.contains(approvisionnementList.get(1)));
    }


    @Test
    public void removeTest() throws OperationFailedException, DataNotFoundException {
        approvisionnementService.remove(approvisionnement1.getId());
        Collection<Approvisionnement> collection = approvisionnementService.getAll();

        assert(!collection.contains(approvisionnement1));
        assert(collection.size() < approvisionnementList.size());
    }
}
