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
import javax.faces.bean.*;

/**
 *
 * @author Rummenigge
 */
@ManagedBean
@SessionScoped()
public class VisitanteMB implements Serializable {

    private UsuarioDao usuarioDao;
    //private VisitaDao visitaDao;
    private Usuario novoUsuario;
    private Usuario usuario;

    public VisitanteMB() throws UnknownHostException, MongoException {
        usuarioDao = new UsuarioDao();
        usuario = new Usuario();
        novoUsuario = new Usuario();
        // visitaDao = new VisitaDao();
        /*Visita novoAcesso = new Visita();
         novoAcesso.setId(new ObjectId(Consts.NULL_OBJECT_ID));
         visitaDao.editar(novoAcesso);*/
    }

    public String criarUsuario() {
        try {
            usuarioDao.criar(novoUsuario);
            novoUsuario = new Usuario();
            return Consts.CRIADO;
        } catch (MongoException me) {
            return Consts.NAO_CRIADO;
        }
    }

    public String entrarNoSistema() {
        List<Usuario> lista = usuarioDao.pesquisar(usuario);
        if (!lista.isEmpty()) {
            Usuario u = lista.get(0);
            Util.getFacesSession().setAttribute("logado", u);
            return "usuarioLogado";
        }
        return "home";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
