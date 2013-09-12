/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.dao;

import br.ufrn.cerescaico.labordoc.gia.dao.criteria.*;
import br.ufrn.cerescaico.labordoc.gia.modelo.Tipo;
import com.google.code.morphia.query.Query;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.List;

/**
 *
 * @author Rummenigge
 */
public class TipoDao extends MongoDao<Tipo>
        implements Serializable {

    private CriteriaStrategyIF<
            Tipo, Query<Tipo>> criteriaStrategyIF;
    private NullCriteria nullCriteria = new NullCriteria();

    public TipoDao() throws UnknownHostException {
        super();
        criteriaStrategyIF = nullCriteria;
    }

    @Override
    public Object criar(Tipo e) throws Exception {
        e.setId(null);
        return super.criar(e);
    }

    @Override
    public List<Tipo> pesquisar(
            Tipo e,
            int offset,
            int limit,
            Integer criteria) throws Exception {

        Query<Tipo> query = dataStore.find(Tipo.class);
        query.offset(offset);
        query.limit(limit);
        criteriaStrategyIF.operationCriteria(e, query);
        return query.asList();
    }
}
