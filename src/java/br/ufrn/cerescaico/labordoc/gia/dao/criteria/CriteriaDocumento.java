/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.dao.criteria;

import br.ufrn.cerescaico.labordoc.gia.modelo.Documento;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import java.util.Map;

/**
 *
 * @author Rummenigge
 */
public class CriteriaDocumento
        implements CriteriaStrategyIF<Documento, BasicDBObject> {

    @Override
    public void operationCriteria(Documento entity, BasicDBObject query) {
        BasicDBList basicDBList = new BasicDBList();
        Map<String, Object> mapa = entity.getCampos();
        for (String key : mapa.keySet()) {
            basicDBList.add(new BasicDBObject(key, mapa.get(key)));
        }
        query.append("$or", basicDBList);
    }
}
