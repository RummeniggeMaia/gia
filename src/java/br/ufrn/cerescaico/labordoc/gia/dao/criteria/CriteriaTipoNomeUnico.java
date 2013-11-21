package br.ufrn.cerescaico.labordoc.gia.dao.criteria;

import br.ufrn.cerescaico.labordoc.gia.modelo.Tipo;
import com.google.code.morphia.query.Query;
import java.util.regex.Pattern;

/**
 *
 * @author Rummenigge
 */
public class CriteriaTipoNomeUnico implements CriteriaStrategyIF<Tipo, Query<Tipo>> {

    @Override
    public void operationCriteria(Tipo entity, Query<Tipo> query) {
        if (entity == null) {
            return;
        }
        query.and(query.criteria("nome").equal(entity.getNome()));
        query.order("nome");
    }
}
