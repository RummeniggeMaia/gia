/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.dao.criteria;

import br.ufrn.cerescaico.labordoc.gia.modelo.Usuario;
import br.ufrn.cerescaico.labordoc.gia.util.Consts;
import com.google.code.morphia.query.Query;

/**
 *
 * @author Rummenigge
 */
public class CriteriaUsuarioId 
    implements CriteriaStrategyIF<Usuario, Query<Usuario>> {

    @Override
    public void operationCriteria(Usuario t, Query<Usuario> q) {
        q.and(q.criteria(Consts._ID).equal(t.getId()));
    }
}
