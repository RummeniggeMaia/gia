/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.modelo;

import com.google.code.morphia.annotations.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Rummenigge
 */
@Entity(value = "tipos", noClassnameStored = true)
public class Tipo extends Entidade implements Serializable {

    @Indexed(unique=true)
    private String nome;
    @Embedded
    private List<Campo> campos = new ArrayList<Campo>(2);
    
    public Tipo() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String tipo) {
        this.nome = tipo;
    }

    public List<Campo> getCampos() {
        return campos;
    }

    public void setCampos(List<Campo> campos) {
        this.campos = campos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append(id)
                .append(",")
                .append(nome);
        if (campos.isEmpty()) {
            return sb.toString();
        }
        sb.append("-");
        int i = 0;
        for (i = 0; i < campos.size() - 1; i++) {
            sb.append(campos.get(i));
            sb.append(",");
        }
        sb.append(campos.get(i));
        return sb.toString();
    }

    public Tipo clonar() {
        Tipo clone = new Tipo();
        clone.setId(new ObjectId(id.toString()));
        clone.setNome(nome);
        for (Campo c : campos) {
            clone.getCampos().add(
                    new Campo(
                    c.getNome(), 
                    null, 
                    c.getTipoDeDado()));
        }
        return clone;
    }
}
