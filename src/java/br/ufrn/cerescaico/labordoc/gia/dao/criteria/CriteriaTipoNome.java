package br.ufrn.cerescaico.labordoc.gia.dao.criteria;

import br.ufrn.cerescaico.labordoc.gia.modelo.Tipo;
import com.google.code.morphia.query.Query;
import java.util.regex.Pattern;

/**
 *
 * @author Rummenigge
 */
public class CriteriaTipoNome implements CriteriaStrategyIF<Tipo, Query<Tipo>> {

    @Override
    public void operationCriteria(Tipo entity, Query<Tipo> query) {
        if (entity == null) {
            return;
        }
        Pattern p = Pattern.compile(".*" + entity.getNome() + ".*");
        query.and(query.criteria("nome").equal(p));
        query.order("nome");
    }
}
