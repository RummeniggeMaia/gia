package br.ufrn.cerescaico.labordoc.gia.modelo;

import com.google.code.morphia.annotations.*;
import java.io.Serializable;
import java.util.*;
import org.bson.types.ObjectId;

/**
 *
 * @author Rummenigge
 */
@Entity(value = "documentos", noClassnameStored = true)
public class Documento extends Entidade implements Serializable {

    @Reference
    private Tipo tipo = new Tipo();
    @Transient
    private Map<String, Object> campos = new HashMap<String, Object>();

    public Documento() {
    }

    public Documento(ObjectId id, Tipo tipo) {
        super(id);
        this.tipo = tipo;
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
        return id.toString();
//        StringBuilder sb = new StringBuilder()
//                .append(id);
//        sb.append("|")
//                .append(tipo)
//                .append("|");
//        Iterator<String> it = campos.keySet().iterator();
//        String key = "";
//        Object valor = null;
//        for (int i = 0; i < campos.size() - 1; i++) {
//            key = it.next();
//            valor = campos.get(key);
//            sb.append(key)
//                    .append(",")
//                    .append(valor)
//                    .append(",");
//        }
//        key = it.next();
//        valor = campos.get(key);
//        sb.append(key)
//                .append(",")
//                .append(valor);
//        return sb.toString();
    }
}
