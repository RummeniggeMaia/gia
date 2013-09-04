package br.ufrn.cerescaico.labordoc.gia.negocio;

import br.ufrn.cerescaico.labordoc.gia.dao.*;
import br.ufrn.cerescaico.labordoc.gia.modelo.*;
import br.ufrn.cerescaico.labordoc.gia.util.*;
import com.mongodb.MongoException;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.*;

/**
 *
 * @author Rummenigge
 */
@ManagedBean
@SessionScoped
public class VisitanteMB extends AbstractUsuarioMB implements Serializable {

    public VisitanteMB() {
        try {
            usuarioDao = new UsuarioDao();
            usuario = new Usuario();
            usuarios = new ArrayList<Usuario>(2);
        } catch (UnknownHostException ex) {
            Util.addMsg(null, ex.getMessage());
        } catch (MongoException me) {
            Util.addMsg(null, me.getMessage());
        }
    }

    public String criarUsuario() {
        try {
            usuarioDao.criar(usuario);
            usuario = new Usuario();
            return Consts.USUARIO_CRIADO;
        } catch (MongoException me) {
            Util.addMsg(null, me.getMessage());
            return Consts.HOME;
        }
    }

    public String entrarNoSistema() {
        Usuario aux = usuarioDao
                .pesquisarUm(usuario, Consts.CRITERIA_AUTENTICAR);
        usuario = new Usuario();
        if (aux != null) {
            Map<String, Object> sessionMap =
                    Util.fc().getExternalContext().getSessionMap();
            sessionMap.put(Consts.USUARIO_LOGADO, aux);
            sessionMap.put(Consts.USUARIO_DAO, usuarioDao);
            String role = aux.getRole();
            return role;
        }
        Util.addMsg(null, "Login ou senha não constam no sistema.");
        return Consts.HOME;
    }/*

    @Override
    public Documento getDocumento() {
        throw new UnsupportedOperationException("Operação não suportada");
    }

    @Override
    public List<Documento> getDocumentos() {
        throw new UnsupportedOperationException("Operação não suportada");
    }

    @Override
    public void setDocumento(Documento documento) {
        throw new UnsupportedOperationException("Operação não suportada");
    }

    @Override
    public void pesquisarDocumentos() {
        throw new UnsupportedOperationException("Operação não suportada");
    }

    public DocumentoDao getDocumentoDao() {
        throw new UnsupportedOperationException("Operação não suportada");
    }

    @Override
    public Usuario getDono() {
        throw new UnsupportedOperationException("Operação não suportada");
    }

    public void setDocumentoDao(DocumentoDao documentoDao) {
        throw new UnsupportedOperationException("Operação não suportada");
    }

    public PesquisaCtrl getPesquisaCtrl() {
        throw new UnsupportedOperationException("Operação não suportada");
    }

    @Override
    public TipoDeDocumento getTipo() {
        throw new UnsupportedOperationException("Operação não suportada");
    }

    public TipoDeDocumentoDao getTipoDeDocumentoDao() {
        throw new UnsupportedOperationException("Operação não suportada");
    }

    @Override
    public List<TipoDeDocumento> getTipos() {
        throw new UnsupportedOperationException("Operação não suportada");
    }

    public UsuarioDao getUsuarioDao() {
        throw new UnsupportedOperationException("Operação não suportada");
    }

    @Override
    public Usuario getUsuario() {
        throw new UnsupportedOperationException("Operação não suportada");
    }

    @Override
    public List<Usuario> getUsuarios() {
        throw new UnsupportedOperationException("Operação não suportada");
    }

    public void setDocumentos(List<Documento> documentos) {
        throw new UnsupportedOperationException("Operação não suportada");
    }

    @Override
    public void setDono(Usuario dono) {
        throw new UnsupportedOperationException("Operação não suportada");
    }

    public void setPesquisaCtrl(PesquisaCtrl pesquisaCtrl) {
        throw new UnsupportedOperationException("Operação não suportada");
    }

    @Override
    public void setTipo(TipoDeDocumento tipo) {
        throw new UnsupportedOperationException("Operação não suportada");
    }

    public void setTipoDeDocumentoDao(TipoDeDocumentoDao tipoDao) {
        this.tipoDao = tipoDao;
    }

    public void setTipos(List<TipoDeDocumento> tipos) {
        throw new UnsupportedOperationException("Operação não suportada");
    }

    @Override
    public void setUsuario(Usuario usuario) {
        throw new UnsupportedOperationException("Operação não suportada");
    }

    public void setUsuarioDao(UsuarioDao usuarioDao) {
        throw new UnsupportedOperationException("Operação não suportada");
    }

    public void setUsuarios(List<Usuario> usuarios) {
        throw new UnsupportedOperationException("Operação não suportada");
    }*/
}
