package br.ufrn.cerescaico.labordoc.gia.negocio;

import br.ufrn.cerescaico.labordoc.gia.dao.*;
import br.ufrn.cerescaico.labordoc.gia.modelo.*;
import br.ufrn.cerescaico.labordoc.gia.util.*;
import com.mongodb.MongoException;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.List;
import javax.faces.bean.*;
import org.bson.types.ObjectId;

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
            usuarioDao = new UsuarioDao();
            usuario = usuarioDao.pesquisarUm(
                    new Usuario(new ObjectId(Consts.NULL_OBJECT_ID)));
        } catch (UnknownHostException uhe) {
            Util.addMsg(null, uhe.getMessage());
        } catch (MongoException me) {
            Util.addMsg(null, me.getMessage());
        }
    }

    public String criarUsuario() {
        try {
            List<Integer> funcoes = usuario.getFuncoes();
            funcoes.add(Consts.FUNCAO_PESQUISAR_DOCUMENTOS);
            funcoes.add(Consts.FUNCAO_EDITAR_PERFIL);
            funcoes.add(Consts.FUNCAO_EXCLUIR_CONTA);
            usuarioDao.criar(usuario);
            usuario = new Usuario();
            return Consts.CRIADO;
        } catch (MongoException me) {
            Util.addMsg(null, me.getMessage());
            return "";
        }
    }

    public String entrarNoSistema() {
        Usuario u = usuarioDao
                .pesquisarUm(usuario, Consts.CRITERIA_AUTENTICAR);
        if (u != null) {
            Util.getFacesSession().setAttribute(Consts.LOGADO, u);
            return Consts.USUARIO_LOGADO;
        }
        Util.addMsg(null, "Login ou senha não consta no sistema.");
        return Consts.HOME;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
