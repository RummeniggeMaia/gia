/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.dao.criteria;

import java.io.Serializable;

/**
 *
 * @author Rummenigge
 */
public class NullCriteria implements CriteriaStrategyIF, Serializable {

    @Override
    public void operationCriteria(Object t, Object query) {
    }
}
