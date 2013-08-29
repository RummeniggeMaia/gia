package br.ufrn.cerescaico.labordoc.gia.negocio;

import br.ufrn.cerescaico.labordoc.gia.dao.*;
import br.ufrn.cerescaico.labordoc.gia.modelo.*;
import br.ufrn.cerescaico.labordoc.gia.util.*;
import com.mongodb.MongoException;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.List;
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
    }

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
    public void pesqusiarDocumentos() {
        throw new UnsupportedOperationException("Operação não suportada");
    }
}
