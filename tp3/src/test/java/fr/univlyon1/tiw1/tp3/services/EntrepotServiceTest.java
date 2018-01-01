package fr.univlyon1.tiw1.tp3.services;

import fr.univlyon1.tiw1.dao.jpa.modele.JPAEntrepot;
import fr.univlyon1.tiw1.metier.spec.Entrepot;
import fr.univlyon1.tiw1.metier.spec.OperationFailedException;
import fr.univlyon1.tiw1.tp3.modele.EntrepotEntity;
import fr.univlyon1.tiw1.tp3.service.EntrepotService;
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
public class EntrepotServiceTest {
    @Autowired
    private EntrepotService entrepotService;

    private Entrepot entrepot1 = new EntrepotEntity("entrepot1", 50);
    private Entrepot entrepot2 = new EntrepotEntity("entrepot2", 35);
    private List<Entrepot> entrepotList = Arrays.asList(entrepot1, entrepot2);

    @Before
    public void setupData() throws OperationFailedException {
        entrepotService.createOrUpdate(entrepot1);
        entrepotService.createOrUpdate(entrepot2);
    }

    @Test
    public void createOrUpdateTest() throws OperationFailedException {
        Entrepot entrepot = new EntrepotEntity("entrepot", 50);
        entrepotService.createOrUpdate(entrepot);

        assertEquals(entrepot.getNom(), "entrepot");
        assertEquals(entrepot.getCapacite(), 50, 0);
    }

    @Test
    public void getByNomTest() {
        JPAEntrepot entrepot = new EntrepotEntity("entrepot1", 50);
        Entrepot entrepot1 = entrepotService.getByNom(entrepot.getNom());

        assertEquals(entrepot.getNom(), entrepot1.getNom());
        assertEquals(entrepot.getCapacite(), entrepot1.getCapacite(), 0);
    }


    @Test
    public void getAllTest() {
        Collection<Entrepot> entrepotList1 = entrepotService.getAll();

        assert(entrepotList1.contains(entrepotList.get(0)));
        assert(entrepotList1.contains(entrepotList.get(1)));
    }
}
