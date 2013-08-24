/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.negocio.criteria;

/**
 * Interface utilizada para aplicação do padrão Stragy no desenvolvimento
 * de criterias para melhores buscas de informações em sistema de banco de 
 * dados.
 * 
 * @author Rummenigge
 * @param <T> Tipo de entidade
 * @param <Q> Query a ser configurada
 */
public interface CriteriaStrategyIF<T, Q> {

    public void operationCriteria(T t, Q query);
}
