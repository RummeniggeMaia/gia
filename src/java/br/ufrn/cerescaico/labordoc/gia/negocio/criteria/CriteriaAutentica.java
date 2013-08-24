/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.negocio.criteria;

import br.ufrn.cerescaico.labordoc.gia.modelo.Usuario;
import com.google.code.morphia.query.Query;

/**
 *
 * @author Rummenigge
 */
public class CriteriaAutentica implements CriteriaStrategyIF<Usuario> {

    @Override
    public void operationCriteria(Usuario t, Object query) {
        Query<Usuario> q = (Query<Usuario>) query;
        q.and(
                q.criteria("login").equal(t.getLogin()),
                q.criteria("senha").equal(t.getSenha()));
    }
}
