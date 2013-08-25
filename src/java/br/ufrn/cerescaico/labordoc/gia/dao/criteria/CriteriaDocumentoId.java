package br.ufrn.cerescaico.labordoc.gia.dao.criteria;

import br.ufrn.cerescaico.labordoc.gia.modelo.Documento;
import br.ufrn.cerescaico.labordoc.gia.util.Consts;
import com.mongodb.BasicDBObject;

/**
 *
 * @author Rummenigge
 */
public class CriteriaDocumentoId
        implements CriteriaStrategyIF<Documento, BasicDBObject> {

    @Override
    public void operationCriteria(Documento entity, BasicDBObject query) {
        query.append(Consts._ID, entity.getId());
    }
}
