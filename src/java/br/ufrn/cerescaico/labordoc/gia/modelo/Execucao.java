/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.modelo;

import com.google.code.morphia.annotations.*;
import org.bson.types.ObjectId;

/**
 *
 * @author Rummenigge
 */
@Entity("execucoes")
public class Execucao {

    @Id
    private ObjectId id;
    @Reference
    private Usuario usuario;
    @Reference
    private Funcao funcao;

    public Execucao() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Execucao)) {
            return false;
        }
        Execucao outra = (Execucao) obj;
        return id.compareTo(outra.getId()) == 0;
    }

    @Override
    public String toString() {
        return "Executa{" + "id=" + id + '}';
    }
}
