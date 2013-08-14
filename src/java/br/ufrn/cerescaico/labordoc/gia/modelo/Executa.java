/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.modelo;

import org.bson.types.ObjectId;

/**
 *
 * @author Rummenigge
 */
public class Executa {
    
    private ObjectId id;
    private String nome;

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

    @Override
    public String toString() {
        return "Executa{" + "id=" + id + ", nome=" + nome + '}';
    }
}