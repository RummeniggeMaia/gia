/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.negocio;

import java.io.Serializable;

/**
 *
 * @author Rummenigge
 */
public class PesquisaCtrl implements Serializable {
    
    private Integer criteria;
    private int offset = 0;
    private int limit = 1;

    public PesquisaCtrl() {
    }

    public Integer getCriteria() {
        return criteria;
    }

    public void setCriteria(Integer criteria) {
        this.criteria = criteria;
    }
    
    public int proximoOffset() {
        int oldOffset = offset;
        offset += limit;
        return oldOffset;
    }

    public int getOffset() {
        return offset;
    }

    
    public void setOffset(int offset) {
        this.offset = offset;
    }
    
    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
