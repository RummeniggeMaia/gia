package br.ufrn.cerescaico.labordoc.gia.negocio;

import br.ufrn.cerescaico.labordoc.gia.dao.*;
import br.ufrn.cerescaico.labordoc.gia.modelo.*;
import br.ufrn.cerescaico.labordoc.gia.util.*;
import br.ufrn.cerescaico.labordoc.gia.util.converter.DataConverter;
import br.ufrn.cerescaico.labordoc.gia.validator.ValidarUsuarioNome;
import com.mongodb.MongoException;
import java.io.IOException;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.convert.Converter;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.validator.Validator;

/**
 *
 * @author Rummenigge
 */
@ManagedBean
@SessionScoped
public class VisitanteMB implements Serializable {

    private UsuarioDao usuarioDao;
    private Usuario usuario;
    private String data;
    private Converter dataConverter;
    private Validator validarNome;

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
            dataConverter = new DataConverter();
            data = dataConverter.getAsString(null, null, new Date());
            validarNome = new ValidarUsuarioNome();
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Converter getDataConverter() {
        return dataConverter;
    }

    public void setDataConverter(Converter dataConverter) {
        this.dataConverter = dataConverter;
    }

    public Validator getValidarNome() {
        return validarNome;
    }

    public void setValidarNome(Validator validarNome) {
        this.validarNome = validarNome;
    }

    public void contemLogin(AjaxBehaviorEvent abe) {
        try {
            Usuario u = usuarioDao
                    .pesquisarUm(usuario, Consts.CRITERIA_USUARIO_LOGIN);
            if (u != null) {
                Util.addMsg("form_criar_conta:campoLogin", 
                        "Já existe alguém com esse login, tente outro.", 
                        FacesMessage.SEVERITY_WARN);
            } else {
                Util.addMsg("form_criar_conta:campoLogin", 
                        "Login aceito", 
                        FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception ex) {
            Util.addMsg("form_criar_conta:campoLogin", 
                    "Erro durante a verificação de login", 
                    FacesMessage.SEVERITY_ERROR);
        }
    }
}
