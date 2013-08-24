/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.modelo;

import com.google.code.morphia.annotations.Entity;
import java.io.Serializable;

/**
 *
 * @author Rummenigge
 */
public class Campo implements Serializable {
    
    private String nome;
    private Object valor;

    public Campo() {
    }

    public Campo(String nome, Object valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }
}
