/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.modelo;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Rummenigge
 */
@Entity
public class Tipo extends Entidade implements Serializable {
    
    private String tipo;
    @Embedded
    private List<Object> campos;

    public Tipo() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Object> getCampos() {
        return campos;
    }

    public void setCampos(List<Object> campos) {
        this.campos = campos;
    }
}
