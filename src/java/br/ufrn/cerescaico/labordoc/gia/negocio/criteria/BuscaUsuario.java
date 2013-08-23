/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.negocio.criteria;

import br.ufrn.cerescaico.labordoc.gia.modelo.Usuario;
import br.ufrn.cerescaico.labordoc.gia.util.Consts;
import com.google.code.morphia.query.Query;

/**
 *
 * @author Rummenigge
 */
public class BuscaUsuario implements CriteriaStrategyIF<Usuario> {

    @Override
    public void operationCriteria(Usuario t, Query<Usuario> q) {
        q.or(q.criteria(Consts.CAMPO_NOME)
                .notEqual(null)
                .and(q.criteria(Consts.CAMPO_NOME)
                .equal(t.getNome())),
                q.criteria(Consts.CAMPO_EMAIL)
                .notEqual(null)
                .and(q.criteria(Consts.CAMPO_EMAIL)
                .equal(t.getEmail())),
                q.criteria(Consts.CAMPO_MATRICULA)
                .notEqual(null)
                .and(q.criteria(Consts.CAMPO_MATRICULA)
                .equal(t.getMatricula())),
                q.criteria(Consts.CAMPO_CPF)
                .notEqual(null)
                .and(q.criteria(Consts.CAMPO_CPF)
                .equal(t.getCpf())));
    }
}
