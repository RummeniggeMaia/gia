/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.dao.criteria;

import br.ufrn.cerescaico.labordoc.gia.modelo.Documento;
import br.ufrn.cerescaico.labordoc.gia.util.Consts;
import com.google.code.morphia.Datastore;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBRefBase;
import java.util.Map;
import java.util.regex.Pattern;

/**
 *
 * @author Rummenigge
 */
public class CriteriaDocumentoConj
        implements CriteriaStrategyIF<Documento, BasicDBObject> {

    private Datastore datastore;

    public CriteriaDocumentoConj(Datastore datastore) {
        this.datastore = datastore;
    }

    @Override
    public void operationCriteria(Documento entity, BasicDBObject query) {
        Map<String, Object> map = entity.getCampos();
        BasicDBList lista = new BasicDBList();
        for (String key : map.keySet()) {
            Object valor = map.get(key);
            if (valor != null) {
                if (valor instanceof String) {
                    if (!((String) valor).isEmpty()) {
                        Pattern p = Pattern.compile(".*" + valor + ".*");
                        lista.add(new BasicDBObject(key, p));
                    }
                } else {
                    lista.add(new BasicDBObject(key, valor));
                }
            }
        }
        if (entity.getTipo() != null) {
            lista.add(new BasicDBObject("tipo",
                    new DBRefBase(
                    datastore.getDB(),
                    Consts.COLECAO_TIPOS,
                    entity.getTipo().getId())));
        }
        if (!lista.isEmpty()) {
            query.append("$and", lista);
        }
    }
}
