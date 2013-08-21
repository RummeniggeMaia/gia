/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
public abstract class DaoGenerico<T> {

    private Morphia morphia;
    private Datastore dataStore;

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
    
    public abstract List<T> pesquisar(T t);
}
