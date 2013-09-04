/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.modelo;

import com.google.code.morphia.annotations.Transient;
import java.io.Serializable;
import org.bson.types.ObjectId;

/**
 *
 * @author Rummenigge
 */
public class Campo implements Serializable {

    @Transient
    private ObjectId id = new ObjectId();
    private String nome;
    private String tipoDeDado;
    @Transient
    private Object valor;

    public Campo() {
    }

    public Campo(String nome, Object valor, String classe) {
        this.nome = nome;
        this.valor = valor;
        this.tipoDeDado = classe;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
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

    public String getTipoDeDado() {
        return tipoDeDado;
    }

    public void setTipoDeDado(String tipoDeDado) {
        this.tipoDeDado = tipoDeDado;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append(nome)
                .append(",")
                .append(valor)
                .append(",")
                .append(tipoDeDado);
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Campo other = (Campo) obj;
        if (this.id != other.id 
                && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
