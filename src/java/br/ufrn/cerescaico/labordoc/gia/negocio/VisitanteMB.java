package br.ufrn.cerescaico.labordoc.gia.negocio;

import br.ufrn.cerescaico.labordoc.gia.dao.*;
import br.ufrn.cerescaico.labordoc.gia.modelo.*;
import br.ufrn.cerescaico.labordoc.gia.util.*;
import com.mongodb.MongoException;
import java.io.IOException;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;

/**
 *
 * @author Rummenigge
 */
@ManagedBean
@SessionScoped
public class VisitanteMB implements Serializable {

    private UsuarioDao usuarioDao;
    private Usuario usuario;

    public VisitanteMB() {
        try {
            Map<String, Object> map = Util
                    .getSessionMap();
            Object mb = map.get("visitanteMB");
            VisitanteMB vMB = null;
            if (mb != null && mb instanceof VisitanteMB) {
                vMB = (VisitanteMB) mb;
                usuarioDao = vMB.getUsuarioDao();
                usuario = (Usuario) map.get(Consts.USUARIO_LOGADO);
            } else {
                usuarioDao = new UsuarioDao();
                usuario = new Usuario();
            }
        } catch (UnknownHostException ex) {
            Util.addMsg(null, ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        } catch (MongoException me) {
            Util.addMsg(null, me.getMessage(), FacesMessage.SEVERITY_ERROR);
        } catch (IOException ie) {
            Util.addMsg(null, ie.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public String criarUsuario() {
        try {
            usuario.setRole(Usuario.ROLE_USER);
            usuarioDao.criar(usuario);
            usuario = new Usuario();
            Util.addMsg(null, "Usuário criado com sucesso.", 
                    FacesMessage.SEVERITY_INFO);
            return Consts.USUARIO_CRIADO;
        } catch (Exception me) {
            Util.addMsg(null, me.getMessage(), FacesMessage.SEVERITY_ERROR);
            return Consts.HOME;
        }
    }

    public String entrarNoSistema() {
        try {
            Usuario aux = usuarioDao
                    .pesquisarUm(usuario, Consts.CRITERIA_AUTENTICAR);
            usuario = new Usuario();
            if (aux != null) {
                Map<String, Object> sessionMap = Util.getSessionMap();
                sessionMap.put(Consts.USUARIO_LOGADO, aux);
                String role = aux.getRole();
                return role;
            }
            throw new Exception("Login ou senha não constam no sistema.");
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage(), FacesMessage.SEVERITY_ERROR);
            return Consts.HOME;
        }
    }

    public UsuarioDao getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
