package br.ufrn.cerescaico.labordoc.gia.dao.criteria;

import br.ufrn.cerescaico.labordoc.gia.modelo.Documento;
import com.mongodb.DBObject;
import java.io.Serializable;

/**
 *
 * @author Rummenigge
 */
public class CriteriaDocumentoTipo
        implements CriteriaStrategyIF<Documento, DBObject>, Serializable {

    @Override
    public void operationCriteria(Documento entity, DBObject query) {
        query.put(
                "tipo", entity.getCampos().get("tipo"));
    }
}
