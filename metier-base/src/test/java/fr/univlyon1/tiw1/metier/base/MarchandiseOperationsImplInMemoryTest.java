package fr.univlyon1.tiw1.metier.base;

import fr.univlyon1.tiw1.metier.base.inmemory.InMemoryMarchandiseDAO;
import fr.univlyon1.tiw1.metier.base.spec.AbstractMarchandiseOperationsTest;
import fr.univlyon1.tiw1.metier.spec.MarchandiseOperations;

public class MarchandiseOperationsImplInMemoryTest extends AbstractMarchandiseOperationsTest {
    @Override
    protected MarchandiseOperations getMarchandisesOperations() {
        return new MarchandiseOperationsImpl(new InMemoryMarchandiseDAO());
    }
}
