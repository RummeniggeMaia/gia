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
public interface CriteriaStrategyIF<T> {

    public void operationCriteria(T t, Query<T> q);
}
