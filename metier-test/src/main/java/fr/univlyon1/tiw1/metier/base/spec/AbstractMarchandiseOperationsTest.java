package fr.univlyon1.tiw1.metier.base.spec;

import fr.univlyon1.tiw1.metier.spec.Marchandise;
import fr.univlyon1.tiw1.metier.spec.MarchandiseOperations;
import fr.univlyon1.tiw1.metier.spec.OperationFailedException;
import fr.univlyon1.tiw1.metier.spec.dto.MarchandiseDTO;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test abstrait sur les operations sur marchandises.
 * Très proches des tests sur DAO au vu du métier demndé sur les marchandises.
 * <p>
 * Created by ecoquery on 11/07/2017.
 */
public abstract class AbstractMarchandiseOperationsTest {

    protected List<Marchandise> data;

    protected abstract MarchandiseOperations getMarchandisesOperations();

    /**
     * Initialise les données de marchandises.
     */
    @Before
    public void setUpData() {
        data = new ArrayList<>();
        for (int i = 1; i <= 4; ++i) {
            data.add(new MarchandiseDTO(-1, "marchandises" + i, 10.0 + i, "d" + i));
        }
    }

    /**
     * Test de création
     *
     * @throws OperationFailedException
     */
    @Test
    public void testCreateOrUpdate() throws OperationFailedException {
        MarchandiseOperations mo = getMarchandisesOperations();
        mo.createOrUpdate(data.get(0));
        mo.createOrUpdate(data.get(1));
        assertNotNull(mo.getByRef(data.get(0).getReference()));
        assertNotNull(mo.getByRef(data.get(1).getReference()));
    }

    /**
     * Test de recherche par clé
     *
     * @throws OperationFailedException
     */
    @Test
    public void testGetByRef() throws OperationFailedException {
        MarchandiseOperations mo = getMarchandisesOperations();
        List<Marchandise> data2 = new ArrayList<>();
        for (Marchandise e : this.data) {
            mo.createOrUpdate(e);
            Marchandise e2 = mo.getByRef(e.getReference());
            data2.add(e2);
        }
        for (int i = 0; i < data2.size(); ++i) {
            Marchandise e = data2.get(i);
            assertTrue("Different object for marchandise " + i, e == mo.getByRef(e.getReference()));
        }
    }

    /**
     * Test de suppression
     *
     * @throws OperationFailedException
     */
    @Test
    public void testDelete() throws OperationFailedException {
        MarchandiseOperations mo = getMarchandisesOperations();
        for (Marchandise m : data) {
            mo.createOrUpdate(m);
        }
        int i = 0;
        Marchandise es = mo.getByRef(data.get(i++).getReference());
        i++;
        Marchandise em = mo.getByRef(data.get(i++).getReference());
        Marchandise ee = mo.getByRef(data.get(i).getReference());
        mo.delete(em);
        assertNull(mo.getByRef(em.getReference()));
        mo.delete(es);
        assertNull(mo.getByRef(es.getReference()));
        mo.delete(ee);
        assertNull(mo.getByRef(ee.getReference()));
    }
}
