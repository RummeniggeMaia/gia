/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.dao;

import br.ufrn.cerescaico.labordoc.gia.dao.criteria.*;
import br.ufrn.cerescaico.labordoc.gia.modelo.Tipo;
import br.ufrn.cerescaico.labordoc.gia.util.Consts;
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
    private CriteriaStrategyIF<Tipo, Query<Tipo>> buscaNome =
            new CriteriaTipoNome();
    private CriteriaStrategyIF<Tipo, Query<Tipo>> buscaNomeUnico =
            new CriteriaTipoNomeUnico();

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
        mudarCriteria(criteria);
        criteriaStrategyIF.operationCriteria(e, query);
        return query.asList();
    }
    
    private void mudarCriteria(Integer criteria) {
        if (criteria == Consts.CRITERIA_TIPO_NOME_UNICO) {
            criteriaStrategyIF = buscaNomeUnico;
        } else if (criteria == Consts.CRITERIA_TIPO_NOME) {
            criteriaStrategyIF = buscaNome;
        } else {
            criteriaStrategyIF = nullCriteria;
        }
    }
    
    public int contar(Tipo t, Integer criteria) {
        Query<Tipo> query = dataStore.find(Tipo.class);
        mudarCriteria(criteria);
        criteriaStrategyIF.operationCriteria(t, query);
        return (int) query.countAll();
    }
}
