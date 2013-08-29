package br.ufrn.cerescaico.labordoc.gia.modelo;

import com.google.code.morphia.annotations.*;
import java.io.Serializable;
import java.util.*;

/**
 *
 * @author Rummenigge
 */
@Entity(value = "documentos", noClassnameStored = true)
public class Documento extends Entidade implements Serializable {

    @Transient
    private Map<String, Object> campos = new HashMap<String, Object>();
    @Reference
    private Tipo tipo;

    public Documento() {
    }

    public Map<String, Object> getCampos() {
        return campos;
    }

    public void setCampos(Map<String, Object> campos) {
        this.campos = campos;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Documento{" + "campos=" + campos + '}';
    }
}
