/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.dao;

import java.util.List;

/**
 *
 * @author Rummenigge
 */
public interface DaoIF<E> {
    
    public Object criar(E e);
    
    public Object editar(E e);
    
    public Object excluir(E e);
    
    public List<E> pesquisar(E e, int offset, int limit, Integer criteria);
}
