/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.negocio.criteria;

import com.google.code.morphia.query.Query;

/**
 *
 * @author Rummenigge
 */
public class NullCriteria implements CriteriaStrategyIF {

    @Override
    public void operationCriteria(Object t, Query q) {}
}
