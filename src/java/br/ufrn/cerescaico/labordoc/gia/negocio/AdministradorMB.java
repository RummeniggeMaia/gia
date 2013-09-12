package br.ufrn.cerescaico.labordoc.gia.negocio;

import br.ufrn.cerescaico.labordoc.gia.modelo.*;
import br.ufrn.cerescaico.labordoc.gia.util.Consts;
import br.ufrn.cerescaico.labordoc.gia.util.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.*;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Rummenigge
 */
@ManagedBean
@SessionScoped
public class AdministradorMB extends AbstractUsuarioMB
        implements Serializable {

    private Campo campo = new Campo();
    private Tipo tipoAux = new Tipo();
    private Usuario usuarioAux = new Usuario();
    private Documento docAux = new Documento();

    public AdministradorMB() {
        super();
    }

    public Campo getCampo() {
        return campo;
    }

    public void setCampo(Campo campo) {
        this.campo = campo;
    }

    public Tipo getTipoAux() {
        return tipoAux;
    }

    public void setTipoAux(Tipo tipoAux) {
        this.tipoAux = tipoAux;
    }

    public Usuario getUsuarioAux() {
        return usuarioAux;
    }

    public void setUsuarioAux(Usuario usuarioAux) {
        this.usuarioAux = usuarioAux;
    }

    public Documento getDocAux() {
        return docAux;
    }

    public void setDocAux(Documento docAux) {
        this.docAux = docAux;
    }

    public void deletarCampo() {
        tipo.getCampos().remove(campo);
    }

    public void inserirCampo() {
        tipo.getCampos().add(new Campo());
    }

    public void limparCampos() {
        tipo.getCampos().clear();
    }

    public void pesquisarTipos() {
        try {
            tipos = tipoDao.pesquisarTodos(Tipo.class);
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage());
        }
    }

    public void criarTipoDeDocumento() {
        try {
            tipo.setNome(
                    Util.capitalizeString(tipo.getNome()));
            for (Campo c : tipo.getCampos()) {
                c.setNome(Util.capitalizeString(c.getNome()));
            }
            tipoDao.criar(tipo);
            tipo = new Tipo();
            Util.addMsg(null, "Tipo de documento criado com sucesso.");
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage());
        }
    }

    public void criarDocumento() {
        try {
            for (Campo c : tipo.getCampos()) {
                documento.getCampos().put(c.getNome(), c.getValor());
                c.setValor(null);
            }
            documento.setTipo(tipo);
            documentoDao.criar(documento);
            documento = new Documento();
            tipo = new Tipo();
            Util.addMsg(null, "Documento cadastrado com sucesso.");
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage());
        }
    }

    public void excluirDocumento() {
        try {
            documentos.remove(docAux);
            documentoDao.excluir(docAux);
            docAux = new Documento();
            Util.addMsg(null, "Documento excluído com sucesso.");
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage());
        }
    }

    public void editarDocumento() {
        try {
            documentoDao.editar(documento);
            documento = new Documento();
            Util.addMsg(null, "Documento editado com sucesso.");
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage());
        }
    }

    public void pesquisarDocumentos() {
        documento.setTipo(tipo);
        for (Campo c : tipo.getCampos()) {
            documento.getCampos().put(c.getNome(), c.getValor());
        }
        try {
            documentos = documentoDao.pesquisar(
                    documento,
                    0,
                    10,
                    Consts.CRITERIA_DOCUMENTO);
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage());
        }
    }

    public void carregarDocumento(ActionEvent ae) {
        Map<String, Object> attribs = ae.getComponent().getAttributes();
        Documento doc = (Documento) attribs.get("documento");
        for (Tipo t : tipos) {
            if (t.equals(doc.getTipo())) {
                tipo = t;
            }
        }
        for (Campo c : tipo.getCampos()) {
            c.setValor(doc.getCampos().get(c.getNome()));
        }
        documento = doc;
    }

    public void excluirTipo() {
        try {
            tipos.remove(tipoAux);
            tipoDao.excluir(tipoAux);
            tipoAux = new Tipo();
            Util.addMsg(null, "Tipo de documento excluído com sucesso.");
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage());
        }
    }

    public void editarTipo() {
        try {
            tipoDao.editar(tipo);
            Util.addMsg(null, "Tipo de documento editado com sucesso.");
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage());
        }
    }

    public void iniciarTipos() {
        try {
            tipo = new Tipo();
            tipoAux = new Tipo();
            pesquisarTipos();
            pesquisaCtrl = new PesquisaCtrl();
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage());
        }

    }

    public void iniciarUsuarios() {
        usuario = new Usuario();
        usuarioAux = new Usuario();
        usuarios = new ArrayList<Usuario>();
        pesquisaCtrl = new PesquisaCtrl();
    }

    public void pesquisarUsuarios() {
        try {
            usuarios = usuarioDao.pesquisar(
                    usuario, 
                    0, 
                    10, 
                    Consts.CRITERIA_USUARIO_CONJUTIVA);
        } catch (Exception ex) {
            Util.addMsg(null, ex.getMessage());
        }
    }

    public void editarUsuario() {
        try {
            usuarioDao.editar(usuario);
            usuario = new Usuario();
            Util.addMsg(null, "Usuario editado com sucesso");
        } catch (Exception ex) {
            Util.addMsg(null, ex.getMessage());
        }
    }

    public void excluirUsuario() {
        try {
            if (dono.equals(usuarioAux)) {
                Util.addMsg(null, "O administrador não pode ser removido.");
                return;
            }
            usuarioDao.excluir(usuarioAux);
            usuarios.remove(usuarioAux);
            Util.addMsg(null, "Usuario excluído com sucesso");
        } catch (Exception ex) {
            Util.addMsg(null, ex.getMessage());
        }
    }
}
