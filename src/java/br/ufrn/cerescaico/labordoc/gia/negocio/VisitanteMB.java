package br.ufrn.cerescaico.labordoc.gia.negocio;

import br.ufrn.cerescaico.labordoc.gia.converter.CampoVazioConverter;
import br.ufrn.cerescaico.labordoc.gia.converter.DataConverter;
import br.ufrn.cerescaico.labordoc.gia.dao.UsuarioDao;
import br.ufrn.cerescaico.labordoc.gia.dao.criteria.CriteriaUsuarioConj;
import br.ufrn.cerescaico.labordoc.gia.modelo.*;
import br.ufrn.cerescaico.labordoc.gia.util.*;
import br.ufrn.cerescaico.labordoc.gia.validator.*;
import com.mongodb.MongoException;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private String confirmSenha;
    private Converter dataConverter;
    private Converter campoVazioConverter;
    private Map<String, Validator> validadores;
    private UsuarioValidator usuarioValidator = new UsuarioValidator();

    public VisitanteMB() {
        try {
            usuarioDao = new UsuarioDao();
            usuario = new Usuario();
            dataConverter = new DataConverter();
            campoVazioConverter = new CampoVazioConverter();
            data = dataConverter.getAsString(null, null, new Date());
            validadores = new HashMap<String, Validator>();
            validadores.put("login", new ValidarUsuarioLogin());
            validadores.put("nome", new ValidarUsuarioNome());
            validadores.put("email", new ValidarUsuarioEmail());
            validadores.put("cpf", new ValidarUsuarioCPF());
        } catch (UnknownHostException ex) {
            Util.addMsg(null, ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        } catch (MongoException me) {
            Util.addMsg(null, me.getMessage(), FacesMessage.SEVERITY_ERROR);
        } catch (IOException ie) {
            Util.addMsg(null, ie.getMessage(), FacesMessage.SEVERITY_ERROR);
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

    public Converter getCampoVazioConverter() {
        return campoVazioConverter;
    }

    public Map<String, Validator> getValidadores() {
        return validadores;
    }

    public void setValidadores(Map<String, Validator> validadores) {
        this.validadores = validadores;
    }

    public String getConfirmSenha() {
        return confirmSenha;
    }

    public void setConfirmSenha(String confirmSenha) {
        this.confirmSenha = confirmSenha;
    }

    public void criarUsuario() {
        try {
            if (!usuarioValido()) {
                return;
            }
            usuario.setRole(Usuario.ROLE_USER);
            criptografar(usuario);
            usuarioDao.criar(usuario);
            usuario = new Usuario();
            Util.addMsg(null, "Conta criada com sucesso.",
                    FacesMessage.SEVERITY_INFO);
        } catch (Exception me) {
            contemLogin(null);
            if (((MongoException.DuplicateKey) me).getCode() == 11000) {
                if (me.getMessage().indexOf("$cpf") != -1) {
                    Util.addMsg("form_criar_conta:campo_cpf",
                        "Já existe alguém com esse cpf.",
                        FacesMessage.SEVERITY_ERROR);
                }
            } else {
                Util.addMsg(null, me.getMessage(),
                        FacesMessage.SEVERITY_ERROR);
            }
        }
    }

    public String entrarNoSistema() {
        try {
            criptografar(usuario);
            Usuario aux = usuarioDao
                    .pesquisarUm(usuario, Consts.CRITERIA_AUTENTICAR);
            usuario = new Usuario();
            if (aux != null) {
                Map<String, Object> sessionMap = Util.getSessionMap();
                sessionMap.put(Consts.USUARIO_LOGADO, aux);
                String role = aux.getRole();
                usuarioDao.setBuscaConjutiva(new CriteriaUsuarioConj(aux));
                return role;
            }
            throw new Exception("Login ou senha não constam no sistema.");
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage(), FacesMessage.SEVERITY_ERROR);
            return Consts.HOME;
        }
    }

    public void contemLogin(AjaxBehaviorEvent abe) {
        try {
            Usuario u = usuarioDao
                    .pesquisarUm(usuario, Consts.CRITERIA_USUARIO_LOGIN);
            if (u != null) {
                Util.addMsg("form_criar_conta:campo_login",
                        "Já existe alguém com esse login, tente outro.",
                        FacesMessage.SEVERITY_ERROR);
            } else {
                ValidatorResult vr = usuarioValidator
                        .validarLogin(usuario.getLogin());
                if (vr.isValido()) {
                    Util.addMsg("form_criar_conta:campo_login",
                            "Login aceito",
                            FacesMessage.SEVERITY_INFO);
                } else {
                    Util.addMsg("form_criar_conta:campo_login",
                            vr.getCausa(),
                            FacesMessage.SEVERITY_ERROR);
                }
            }
        } catch (Exception ex) {
            Util.addMsg("form_criar_conta:campo_login",
                    "Erro durante a verificação de login",
                    FacesMessage.SEVERITY_ERROR);
        }
    }

    private boolean usuarioValido() {
        boolean valido = true;
        ValidatorResult vr = null;
        vr = usuarioValidator.validarLogin(usuario.getLogin());
        if (!vr.isValido()) {
            Util.addMsg(
                    "form_criar_conta:campo_login",
                    vr.getCausa(),
                    FacesMessage.SEVERITY_ERROR);
            valido = false;
        }
        vr = usuarioValidator.validarSenha(usuario.getSenha());
        if (!vr.isValido()) {
            Util.addMsg(
                    "form_criar_conta:campo_senha",
                    vr.getCausa(),
                    FacesMessage.SEVERITY_ERROR);
            valido = false;
        }
        vr = usuarioValidator.confimarSenhas(usuario.getSenha(), confirmSenha);
        if (!vr.isValido()) {
            Util.addMsg(
                    "form_criar_conta:campo_confirm_senha",
                    "Senhas não coincidem.",
                    FacesMessage.SEVERITY_ERROR);
            valido = false;
        }
        vr = usuarioValidator.validarNome(usuario.getNome());
        if (!vr.isValido()) {
            Util.addMsg(
                    "form_criar_conta:campo_nome",
                    vr.getCausa(),
                    FacesMessage.SEVERITY_ERROR);
            valido = false;
        }
        vr = usuarioValidator.validarEmail(usuario.getEmail());
        if (!vr.isValido()) {
            Util.addMsg(
                    "form_criar_conta:campo_email",
                    vr.getCausa(),
                    FacesMessage.SEVERITY_ERROR);
            valido = false;
        }
        vr = usuarioValidator.validarCpf(usuario.getCpf());
        if (!vr.isValido()) {
            Util.addMsg(
                    "form_criar_conta:campo_cpf",
                    vr.getCausa(),
                    FacesMessage.SEVERITY_ERROR);
            valido = false;
        }
        if (usuario.getDataNascimento() == null) {
            Util.addMsg(
                    "form_criar_conta:campo_data_nasc",
                    "Data inválida.",
                    FacesMessage.SEVERITY_ERROR);
            valido = false;
        }
        return valido;
    }
    
    private void criptografar(Usuario e) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.reset();
            String loginSenha = e.getLogin() + e.getSenha();
            messageDigest.update(loginSenha.getBytes("UTF-8"));
            e.setSenha(new String(
                    messageDigest.digest(loginSenha.getBytes("UTF-8"))));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(
                    UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(
                    UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
