/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.modelo;

import br.ufrn.cerescaico.labordoc.gia.negocio.Campo;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Rummenigge
 */
public class Documento {
    
    private ObjectId id;
    private List<Campo> campos;

    public Documento() {
    }

    public Documento(List<Campo> campos) {
        this.campos = campos;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public List<Campo> getCampos() {
        return campos;
    }

    public void setCampos(List<Campo> campos) {
        this.campos = campos;
    }

    @Override
    public String toString() {
        return "Documento{" + "id=" + id + ", campos=" + campos + '}';
    }
}
