/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.modelo;

import com.google.code.morphia.annotations.Id;
import org.bson.types.ObjectId;

/**
 *
 * @author Rummenigge
 */
public class Entidade {
    
    @Id()
    protected ObjectId id;

    public Entidade() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
