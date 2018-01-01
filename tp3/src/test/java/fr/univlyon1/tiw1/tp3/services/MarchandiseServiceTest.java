package fr.univlyon1.tiw1.tp3.services;

import fr.univlyon1.tiw1.metier.spec.Marchandise;
import fr.univlyon1.tiw1.metier.spec.OperationFailedException;
import fr.univlyon1.tiw1.tp3.modele.MarchandiseEntity;
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
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Amaia Nazábal
 * @version 1.0
 * @since 1.0 12/3/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MarchandiseServiceTest {

    @Autowired
    private MarchandiseService marchandiseService;


    private Marchandise marchandise1 = new MarchandiseEntity(null, "marchandise1", 10, "marchandise1");
    private Marchandise marchandise2 = new MarchandiseEntity(null, "marchandise2", 5, "marchandise2");
    private List<Marchandise> marchandiseList = Arrays.asList(marchandise1, marchandise2);

    private boolean flag;

    @Before
    public void setupData() throws OperationFailedException {
        if (!flag) {
            marchandise1 = marchandiseService.createOrUpdate(marchandise1);
            marchandise2 = marchandiseService.createOrUpdate(marchandise2);
            flag = true;
        }
    }

    @After
    public void removeData() {
        if (flag)
            try {
                marchandiseService.remove(marchandise2.getReference());
                marchandiseService.remove(marchandise1.getReference());
            } catch (OperationFailedException | DataNotFoundException e) {
                // pass out
            }
    }

    @Test
    public void createOrUpdateTest() throws OperationFailedException {
        Marchandise marchandise = new MarchandiseEntity(null, "marchandise", 7, "marchandise");
        marchandise = marchandiseService.createOrUpdate(marchandise);

        assert (marchandise.getReference() != 0);
        assertEquals(marchandise.getNom(), "marchandise");
        assertEquals(marchandise.getVolumeUnitaire(), 7, 0);
        assertEquals(marchandise.getDescription(), "marchandise");
    }

    @Test
    public void getByNomTest() {
        Marchandise marchandise = marchandiseService.getByRef(marchandise1.getReference());

        assertEquals(marchandise.getReference(), marchandise1.getReference());
        assertEquals(marchandise.getNom(), marchandise1.getNom());
        assertEquals(marchandise.getVolumeUnitaire(), marchandise1.getVolumeUnitaire(), 0);
        assertEquals(marchandise.getDescription(), marchandise1.getDescription());
    }

    @Test
    public void getAllTest() {
        Collection<Marchandise> marchandiseCollection = marchandiseService.getAll();

        assert(marchandiseCollection.contains(marchandiseList.get(0)));
        assert(marchandiseCollection.contains(marchandiseList.get(1)));
    }

    @Test
    public void removeTest() throws OperationFailedException, DataNotFoundException {
        marchandiseService.remove(marchandise1.getReference());
        Collection<Marchandise> marchandiseCollection = marchandiseService.getAll();

        assert(!marchandiseCollection.contains(marchandise1));
        assert(marchandiseCollection.contains(marchandise2));
    }
}
