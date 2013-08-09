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
public class Visita {
    
    private ObjectId id;
    private ObjectId documento;
    private Usuario usuario;
    private int totalDeVisitas;

    public Visita() {
    }

    public Visita(ObjectId documento, Usuario usuario) {
        this.documento = documento;
        this.usuario = usuario;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getDocumento() {
        return documento;
    }

    public void setDocumento(ObjectId documento) {
        this.documento = documento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getTotalDeVisitas() {
        return totalDeVisitas;
    }

    public void setTotalDeVisitas(int totalDeVisitas) {
        this.totalDeVisitas = totalDeVisitas;
    }
}
