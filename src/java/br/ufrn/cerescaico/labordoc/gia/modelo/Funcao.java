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
@Entity("funcoes")
public class Funcao {

    @Id
    private ObjectId id;
    private String nome;

    public Funcao() {
    }

    public Funcao(String nome) {
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
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Funcao)) {
            return false;
        }
        Funcao outra = (Funcao) obj;
        return id.compareTo(outra.getId()) == 0;
    }

    @Override
    public String toString() {
        return "Funcao{" + "id=" + id + ", nome=" + nome + '}';
    }
}
