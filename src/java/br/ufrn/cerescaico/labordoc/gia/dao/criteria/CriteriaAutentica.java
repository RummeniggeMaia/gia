/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.dao.criteria;

import br.ufrn.cerescaico.labordoc.gia.modelo.Usuario;
import com.google.code.morphia.query.Query;

/**
 *
 * @author Rummenigge
 */
public class CriteriaAutentica 
    implements CriteriaStrategyIF<Usuario, Query<Usuario>> {

    @Override
    public void operationCriteria(Usuario t, Query<Usuario> query) {
        Query<Usuario> q = (Query<Usuario>) query;
        q.and(
                q.criteria("login").equal(t.getLogin()),
                q.criteria("senha").equal(t.getSenha()));
    }
}
