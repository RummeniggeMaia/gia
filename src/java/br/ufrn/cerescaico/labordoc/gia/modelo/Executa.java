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
public class Executa {

    @Id
    private ObjectId id;
    private String nome;
    @Reference
    private Usuario usuario;
    @Reference
    private Funcao funcao;

    public Executa() {
    }

    public Executa(String nome) {
        this.nome = nome;
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
    public String toString() {
        return "Executa{" + "id=" + id + ", nome=" + nome + '}';
    }
}
