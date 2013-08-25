/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.dao.criteria;

/**
 * Interface utilizada para aplicação do padrão Stragy no desenvolvimento
 * de criterias para melhores buscas de informações em sistema de banco de 
 * dados.
 * 
 * @author Rummenigge
 * @param <E> Entidade a ser buscada
 * @param <Q> Consulta a ser configurada de acordo com os criterios exigidos
 */
public interface CriteriaStrategyIF<E, Q> {

    public void operationCriteria(E entity, Q query);
}
