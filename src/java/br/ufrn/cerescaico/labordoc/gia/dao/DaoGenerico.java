/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.dao;

import br.ufrn.cerescaico.labordoc.gia.negocio.*;
import br.ufrn.cerescaico.labordoc.gia.util.Consts;
import com.google.code.morphia.*;
import com.google.code.morphia.query.Query;
import java.net.UnknownHostException;
import java.util.*;

/**
 *
 * @author Rummenigge
 */
public abstract class DaoGenerico<T> {

    protected Morphia morphia;
    protected Datastore dataStore;

    public DaoGenerico() throws UnknownHostException {
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
}
