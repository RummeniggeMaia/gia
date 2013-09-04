/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.dao.criteria;

import br.ufrn.cerescaico.labordoc.gia.modelo.Documento;
import br.ufrn.cerescaico.labordoc.gia.negocio.MongoClientSingleton;
import br.ufrn.cerescaico.labordoc.gia.util.Consts;
import com.google.code.morphia.Datastore;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBRefBase;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.Map;

/**
 *
 * @author Rummenigge
 */
public class CriteriaDocumento
        implements CriteriaStrategyIF<Documento, BasicDBObject>, Serializable {

    private Datastore datastore;

    public CriteriaDocumento(Datastore datastore) {
        this.datastore = datastore;
    }

    @Override
    public void operationCriteria(
            Documento entity,
            BasicDBObject query) {
        
        BasicDBList basicDBList = new BasicDBList();
        Map<String, Object> mapa = entity.getCampos();
        for (String key : mapa.keySet()) {
            basicDBList.add(new BasicDBObject(key, mapa.get(key)));
        }

        try {
            DBRefBase refBase = new DBRefBase(
                    datastore.getDB(),
                    Consts.COLECAO_TIPOS,
                    entity.getId());
            basicDBList.add(refBase);
        } catch (Exception ex) {
        }
        query.append("$or", basicDBList);
    }
}
