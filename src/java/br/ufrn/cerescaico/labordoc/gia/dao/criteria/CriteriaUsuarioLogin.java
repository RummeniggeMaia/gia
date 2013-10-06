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
public class CriteriaUsuarioLogin
        implements CriteriaStrategyIF<Usuario, Query<Usuario>> {

    @Override
    public void operationCriteria(Usuario entity, Query<Usuario> query) {
        if (entity == null) {
            return;
        }
        query.and(query.criteria("login").equal(entity.getLogin()));
    }
}
