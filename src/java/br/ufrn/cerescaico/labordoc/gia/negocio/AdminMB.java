package br.ufrn.cerescaico.labordoc.gia.negocio;

import br.ufrn.cerescaico.labordoc.gia.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Rummenigge
 */
@ManagedBean
@SessionScoped
public class AdminMB extends AbstractUsuarioMB implements Serializable {

    public AdminMB() {
        super(Util.fc().getExternalContext().getSessionMap());
    }
    
    
}
