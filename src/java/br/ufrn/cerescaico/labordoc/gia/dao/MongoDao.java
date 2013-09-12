package br.ufrn.cerescaico.labordoc.gia.dao;

import br.ufrn.cerescaico.labordoc.gia.negocio.MongoClientSingleton;
import br.ufrn.cerescaico.labordoc.gia.util.Consts;
import com.google.code.morphia.*;
import java.net.UnknownHostException;
import java.util.List;

/**
 *
 * @author Rummenigge
 */
public abstract class MongoDao<E> implements DaoIF<E> {

    protected Morphia morphia;
    protected Datastore dataStore;

    public MongoDao() throws UnknownHostException {
        morphia = new Morphia();
        morphia.mapPackage(Consts.MODELO_PACKAGE);
        dataStore =
                morphia.createDatastore(
                MongoClientSingleton.getInstance(), Consts.BANCO);
        dataStore.ensureIndexes();
    }

    @Override
    public Object criar(E e) throws Exception {
        return dataStore.save(e);
    }

    @Override
    public Object editar(E e) throws Exception {
        return dataStore.merge(e);
    }

    @Override
    public Object excluir(E e) throws Exception {
        return dataStore.delete(e);
    }

    public E pesquisarUm(E e) throws Exception {
        return dataStore.get(e);
    }

    public E pesquisarUm(E e, Integer criteria) throws Exception {
        List<E> lista = pesquisar(e, 0, 1, criteria);
        return lista.isEmpty() ? null : lista.get(0);
    }

    public List<E> pesquisarTodos(Class c) throws Exception {
        return dataStore.find(c).asList();
    }
    
    public void close() {
        morphia = null;
        dataStore = null;
    }
}
