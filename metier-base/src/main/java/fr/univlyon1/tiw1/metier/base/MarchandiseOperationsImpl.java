package fr.univlyon1.tiw1.metier.base;

import fr.univlyon1.tiw1.metier.spec.Marchandise;
import fr.univlyon1.tiw1.metier.spec.MarchandiseOperations;
import fr.univlyon1.tiw1.metier.spec.OperationFailedException;
import fr.univlyon1.tiw1.metier.spec.dao.MarchandiseDAO;

public class MarchandiseOperationsImpl implements MarchandiseOperations {

    protected MarchandiseDAO mdao;

    /**
     * Contruit un nouveau composant MarchandiseOperationsImpl.
     * @param mdao le DAO utilisé pour gérer la persistance des marchandises.
     */
    public MarchandiseOperationsImpl(MarchandiseDAO mdao) {
        this.mdao = mdao;
    }

    @Override
    public Marchandise createOrUpdate(Marchandise marchandise) throws OperationFailedException {
        return mdao.createOrUpdate(marchandise);
    }

    @Override
    public Marchandise getByRef(int ref) {
        return mdao.findByRef(ref);
    }

    @Override
    public void delete(Marchandise marchandise) throws OperationFailedException {
        mdao.remove(marchandise);
    }
}
