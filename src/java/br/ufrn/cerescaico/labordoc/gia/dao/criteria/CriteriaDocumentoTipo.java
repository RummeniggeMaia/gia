/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.dao.criteria;

import br.ufrn.cerescaico.labordoc.gia.modelo.Documento;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 *
 * @author Rummenigge
 */
public class CriteriaDocumentoTipo
        implements CriteriaStrategyIF<Documento, DBObject> {

    @Override
    public void operationCriteria(Documento entity, DBObject query) {
        query.put(
                "tipo", entity.getCampos().get("tipo"));
    }
}
