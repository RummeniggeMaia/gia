/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.dao;

import br.ufrn.cerescaico.labordoc.gia.dao.criteria.CriteriaUsuarioId;
import br.ufrn.cerescaico.labordoc.gia.dao.criteria.CriteriaAutentica;
import br.ufrn.cerescaico.labordoc.gia.dao.criteria.CriteriaUsuario;
import br.ufrn.cerescaico.labordoc.gia.dao.criteria.CriteriaStrategyIF;
import br.ufrn.cerescaico.labordoc.gia.dao.criteria.NullCriteria;
import br.ufrn.cerescaico.labordoc.gia.modelo.Usuario;
import br.ufrn.cerescaico.labordoc.gia.util.Consts;
import com.google.code.morphia.query.*;
import java.io.Serializable;
import java.net.UnknownHostException;

/**
 *
 * @author Rummenigge
 */
public class UsuarioDao extends MongoDao<Usuario> implements Serializable {

    private CriteriaStrategyIF<Usuario> criteriaStrategyIF;
    private CriteriaUsuario buscaUsuario = new CriteriaUsuario();
    private CriteriaUsuarioId buscaUsuarioId =
            new CriteriaUsuarioId();
    private CriteriaAutentica autenticaCriteria = new CriteriaAutentica();
    private NullCriteria nullCriteria = new NullCriteria();

    public UsuarioDao() throws UnknownHostException {
        super();
        criteriaStrategyIF = nullCriteria;
    }

    @Override
    protected void criarCriteria(Usuario u, Object query, int criteria) {
        if (!(query instanceof Query)) {
            throw new IllegalArgumentException("Query não é instância de "
                    + "com.google.code.morphia.query.Query");
        }
        Query<Usuario> q = (Query<Usuario>) query;
        if (criteria == Consts.CRITERIA_USUARIO_ID) {
            criteriaStrategyIF = buscaUsuarioId;
        } else if (criteria == Consts.CRITERIA_AUTENTICAR) {
            criteriaStrategyIF = autenticaCriteria;
        } else {
            criteriaStrategyIF = buscaUsuario;
        }
        criteriaStrategyIF.operationCriteria(u, q);
    }
}
