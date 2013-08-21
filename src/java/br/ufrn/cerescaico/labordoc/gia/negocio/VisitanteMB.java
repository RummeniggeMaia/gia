/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.negocio;

import br.ufrn.cerescaico.labordoc.gia.dao.*;
import br.ufrn.cerescaico.labordoc.gia.modelo.*;
import br.ufrn.cerescaico.labordoc.gia.util.*;
import com.mongodb.MongoException;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

/**
 *
 * @author Rummenigge
 */
@ManagedBean
@SessionScoped()
public class VisitanteMB implements Serializable {

    private UsuarioDao usuarioDao;
    private Usuario visitante;

    public VisitanteMB() {
        try {
            usuarioDao = new UsuarioDao();
            visitante = new Usuario();
        } catch (UnknownHostException uhe) {
            Util.addMsg(null, uhe.getMessage());
        } catch (MongoException me) {
            Util.addMsg(null, me.getMessage());
        }
    }

    public String criarUsuario() {
        try {
            usuarioDao.criar(visitante);
            visitante = new Usuario();
            return Consts.CRIADO;
        } catch (MongoException me) {
            FacesContext
                    .getCurrentInstance()
                    .addMessage(null, new FacesMessage(me.getMessage()));
            return "";
        }
    }

    public String entrarNoSistema() {
        List<Usuario> lista = usuarioDao.pesquisar(visitante);
        if (!lista.isEmpty()) {
            Usuario u = lista.get(0);
            Util.getFacesSession().setAttribute(Consts.LOGADO, u);
            return Consts.USUARIO_LOGADO;
        }
        return Consts.HOME;
    }

    public Usuario getVisitante() {
        return visitante;
    }

    public void setVisitante(Usuario usuario) {
        this.visitante = usuario;
    }
}
