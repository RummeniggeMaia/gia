/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.modelo;

import com.google.code.morphia.annotations.*;
import java.io.Serializable;
import org.bson.types.ObjectId;

/**
 *
 * @author Rummenigge
 */
@Entity(value = "entidades", noClassnameStored = true)
public class Visita extends Entidade implements Serializable {

    @Reference
    private Documento documento;
    @Reference
    private Usuario usuario;
    private int totalDeVisitas;

    public Visita() {
    }

    public Visita(Documento documento, Usuario usuario) {
        this.documento = documento;
        this.usuario = usuario;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
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

    @Override
    public String toString() {
        return "Visita{" + "id=" + id + ", documento=" + documento
                + ", usuario=" + usuario + ", totalDeVisitas="
                + totalDeVisitas + '}';
    }
}
