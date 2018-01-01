package fr.univlyon1.tiw1.metier.base.spec;

import fr.univlyon1.tiw1.metier.spec.OperationFailedException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Classe de base pour tester les DAOs
 * <p>
 * Created by ecoquery on 06/07/2017.
 *
 * @param <DAO> La classe de DAO à tester
 * @param <ELT> le type d'objet géré par le DAO
 * @param <KEY> le type des clés utilisées dans le DAO
 */
public abstract class GenericDAOAbstractTest<DAO, ELT, KEY> {

    protected List<ELT> data;

    protected abstract DAO getDAO();

    /**
     * Remplit la liste data.
     */
    @Before
    public abstract void setUpData();

    /**
     * Appelle correctement le DAO pour créer ou mettre à jour livraisons'objet
     *
     * @param dao le dao
     * @param elt livraisons'objet
     * @return livraisons'objet ou sa copie gérée par le dao
     * @throws OperationFailedException si un problème survient
     */
    protected abstract ELT createOrUpdate(DAO dao, ELT elt) throws OperationFailedException;

    /**
     * Appelle une méthode du DAO pour récupérer tous les objets ELT stockés
     *
     * @param dao le DAO
     * @return la liste des objets stockés
     * @throws OperationFailedException si un problème survient
     */
    protected abstract Collection<ELT> getAll(DAO dao) throws OperationFailedException;

    /**
     * Appelle le DAO pour récupérer un objet à partir de sa clé
     *
     * @param dao le DAO
     * @param key la clé
     * @return livraisons'objet cherché ou null
     * @throws OperationFailedException si un problème survient
     */
    protected abstract ELT findByKey(DAO dao, KEY key) throws OperationFailedException;

    /**
     * Renvoie la clé d'un objet
     *
     * @param elt livraisons'objet
     * @return la clé
     */
    protected abstract KEY getKey(ELT elt);

    /**
     * Suprime un élément du DAO
     *
     * @param dao le DAO
     * @param elt livraisons'élément
     * @throws OperationFailedException si un problème survient.
     */
    protected abstract void remove(DAO dao, ELT elt) throws OperationFailedException;

    /**
     * Test de création
     *
     * @throws OperationFailedException
     */
    @Test
    public void testCreateOrUpdate() throws OperationFailedException {
        DAO dao = getDAO();
        ELT e1 = createOrUpdate(dao, data.get(0));
        ELT e2 = createOrUpdate(dao, data.get(1));
        assertNotNull(e1);
        assertNotNull(e2);
        assertEquals(2, getAll(dao).size());
        assertNotNull(findByKey(dao, getKey(e1)));
        assertNotNull(findByKey(dao, getKey(e2)));
        data.set(0, e1);
        data.set(1, e2);
        for (ELT elt : data) {
            createOrUpdate(dao, elt);
        }
        assertEquals(data.size(), getAll(dao).size());
    }

    /**
     * Test de recherche par clé
     *
     * @throws OperationFailedException
     */
    @Test
    public void testFindByKey() throws OperationFailedException {
        DAO dao = getDAO();
        List<ELT> data2 = new ArrayList<>();
        for (ELT e : this.data) {
            data2.add(createOrUpdate(dao, e));
        }
        for (int i = 0; i < data2.size(); ++i) {
            ELT e = data2.get(i);
            assertTrue("Different object for marchandise " + i, e == findByKey(dao, getKey(e)));
        }
    }

    /**
     * test de récupération de tous les objets
     *
     * @throws OperationFailedException
     */
    @Test
    public void testGetAll() throws OperationFailedException {
        DAO dao = getDAO();
        for (ELT e : this.data) {
            createOrUpdate(dao, e);
        }
        assertEquals(data.size(), getAll(dao).size());
    }

    /**
     * Test de suppression
     *
     * @throws OperationFailedException
     */
    @Test
    public void testRemove() throws OperationFailedException {
        DAO dao = getDAO();
        int i = 0;
        ELT es = createOrUpdate(dao, data.get(i++));
        createOrUpdate(dao, data.get(i++));
        ELT em = createOrUpdate(dao, data.get(i++));
        ELT ee = createOrUpdate(dao, data.get(i));
        remove(dao, em);
        assertNull(findByKey(dao, getKey(em)));
        assertEquals(3, getAll(dao).size());
        remove(dao, es);
        assertNull(findByKey(dao, getKey(es)));
        assertEquals(2, getAll(dao).size());
        remove(dao, ee);
        assertNull(findByKey(dao, getKey(ee)));
        assertEquals(1, getAll(dao).size());
    }
}
