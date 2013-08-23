/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.negocio;

import br.ufrn.cerescaico.labordoc.gia.dao.UsuarioDao;
import br.ufrn.cerescaico.labordoc.gia.modelo.Usuario;
import br.ufrn.cerescaico.labordoc.gia.util.Consts;
import br.ufrn.cerescaico.labordoc.gia.util.Util;
import com.mongodb.MongoException;
import java.net.UnknownHostException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rummenigge
 */
@ManagedBean
@SessionScoped
public class UsuarioMB {

    /**
     * Creates a new instance of UsuarioMB
     */
    public Usuario usuario;
    public UsuarioDao usuarioDao;

    public UsuarioMB() {
        try {
            usuario = (Usuario) Util
                    .getFacesSession()
                    .getAttribute(Consts.LOGADO);
            usuarioDao = new UsuarioDao();
        } catch (UnknownHostException uhe) {
            Util.addMsg(null, uhe.getMessage());
        }
    }

    public void editarConta() {
        try {
            usuarioDao.editar(usuario);
        } catch (MongoException ex) {
            Util.addMsg(null, ex.getMessage());
        }
    }

    public void excluirConta() {
        try {
            usuarioDao.excluir(usuario);
        } catch (MongoException ex) {
            Util.addMsg(null, ex.getMessage());
        }
    }

    public void pesquisarDocumentos() {
    }

    public String excluirUsuarios() {
        return "";
    }

    public void pesquisarUsuarios() {
    }

    public String sairDoSistema() {
        HttpSession sessao = Util.getFacesSession();
        sessao.removeAttribute(Consts.LOGADO);
        sessao.invalidate();
        usuario = new Usuario();
        return Consts.HOME;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
