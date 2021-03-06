package br.ufrn.cerescaico.labordoc.gia.modelo;

import com.google.code.morphia.annotations.Id;
import org.bson.types.ObjectId;

/**
 *
 * @author Rummenigge
 */
public abstract class Entidade {
    
    @Id()
    protected ObjectId id = new ObjectId();
    

    public Entidade() {
    }

    public Entidade(ObjectId id) {
        this.id = id;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Entidade) {
            Entidade e = (Entidade) obj;
            return id.compareTo(e.getId()) == 0;
        }
        return false;
    }
}
