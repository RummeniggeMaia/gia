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
    public Object criar(E e) {
        return dataStore.save(e);
    }

    @Override
    public Object editar(E e) {
        return dataStore.merge(e);
    }

    @Override
    public Object excluir(E e) {
        return dataStore.delete(e);
    }
    
    public E pesquisarUm(E e) {
        return dataStore.get(e);
    }
    
    public E pesquisarUm(E e, Integer criteria) {
        List<E> lista = pesquisar(e, 0, 1, criteria);
        return lista.isEmpty() ? null : lista.get(0);
    }
    
    public List<E> pesquisarTodos(Class c) {
        return dataStore.find(c).asList();
    }
}
/*
 protected Morphia morphia;
    protected Datastore dataStore;

    public MongoDao() throws UnknownHostException {
        morphia = new Morphia();
        morphia.mapPackage(Consts.MODELO_PACKAGE);
        dataStore = morphia.createDatastore(
                MongoClientSingleton.getInstance(),
                Consts.BANCO);
    }

    public void criar(T t) {
        dataStore.save(t);
    }

    public void editar(T t) {
        dataStore.merge(t);
    }

    public void excluir(T t) {
        dataStore.delete(t);
    }

    public List<T> pesquisar(
            T t,
            int offset,
            int limit,
            int criteria) {

        Query q = dataStore.find(t.getClass());
        q.offset(offset);
        q.limit(limit);
        criarCriteria(t, q, criteria);
        return q.asList();
    }

    public T pesquisarUm(T t, int criteria) {
        List<T> lista = pesquisar(t, 0, 1, criteria);
        return lista.isEmpty() ? null : lista.get(0);
    }

    protected abstract void criarCriteria(T t, Object query, int criteria);
 */