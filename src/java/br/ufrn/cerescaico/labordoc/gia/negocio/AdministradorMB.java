package br.ufrn.cerescaico.labordoc.gia.negocio;

import br.ufrn.cerescaico.labordoc.gia.modelo.*;
import br.ufrn.cerescaico.labordoc.gia.util.Consts;
import br.ufrn.cerescaico.labordoc.gia.util.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.application.FacesMessage;
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
    private boolean editarUsuario;
    private boolean editarDocumento;
    private boolean editarTipo;

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

    public boolean isEditarUsuario() {
        return editarUsuario;
    }

    public boolean isEditarDocumento() {
        return editarDocumento;
    }

    public boolean isEditarTipo() {
        return editarTipo;
    }

    public void deletarCampo() {
        tipo.getCampos().remove(campo);
    }

    public void inserirCampo() {
        tipo.getCampos().add(new Campo());
    }

    public void limparCampos() {
        if (tipo != null) {
            tipo.getCampos().clear();
        }
    }

    public void pesquisarTipos() {
        try {
            tipos = tipoDao.pesquisarTodos(Tipo.class);
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage(), FacesMessage.SEVERITY_WARN);
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
            Util.addMsg(null, "Tipo de documento criado com sucesso.",
                    FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage(), FacesMessage.SEVERITY_ERROR);
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
            Util.addMsg(null, "Documento cadastrado com sucesso.",
                    FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void excluirDocumento() {
        try {
            if (documento.equals(docAux)) {
                throw new Exception("Cancele a edição do documento antes de removê-lo.");
            }
            documentos.remove(docAux);
            documentoDao.excluir(docAux);
            docAux = new Documento();
            Util.addMsg(null, "Documento excluído com sucesso.",
                    FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void editarDocumento() {
        try {
            documento.getCampos().clear();
            for (Campo c : tipo.getCampos()) {
                documento.getCampos().put(c.getNome(), c.getValor());
                c.setValor(null);
            }
            documento.setTipo(tipo);
            documentoDao.editar(documento);
            documento = new Documento();
            editarDocumento = false;
            Util.addMsg(null, "Documento editado com sucesso.",
                    FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void pesquisarDocumentos() {
        try {
            documento = new Documento();
            documentos.clear();
            if (tipo == null) {
                return;
            }
            documento.setTipo(tipo);
            for (Campo c : tipo.getCampos()) {
                documento.getCampos().put(c.getNome(), c.getValor());
            }
            paginacaoCtrl.setCont(
                    documentoDao.contar(documento, 
                    Consts.CRITERIA_DOCUMENTO_CONJUNTIVA));
            paginacaoCtrl.setEntidade(documento);
            paginacaoCtrl.primeira();
            realizarPesquisaDocumentos();
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage(), FacesMessage.SEVERITY_WARN);
        }
    }

    public void carregarDocumento(ActionEvent ae) {
        try {
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
        } catch (Exception e) {
            Util.addMsg(null, "Erro durante a edição do documento.",
                    FacesMessage.SEVERITY_ERROR);
        }
    }

    public void excluirTipo() {
        try {
            tipos.remove(tipoAux);
            tipoDao.excluir(tipoAux);
            tipoAux = new Tipo();
            Util.addMsg(null, "Tipo de documento excluído com sucesso.",
                    FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void editarTipo() {
        try {
            tipoDao.editar(tipo);
            Util.addMsg(null, "Tipo de documento editado com sucesso.",
                    FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void iniciarTipos() {
        try {
            tipo = new Tipo();
            tipoAux = new Tipo();
            pesquisarTipos();
            paginacaoCtrl = new PaginacaoCtrl();
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage(), FacesMessage.SEVERITY_WARN);
        }

    }

    public void iniciarUsuarios() {
        usuarioAux = new Usuario();
        usuarios = new ArrayList<Usuario>();
        paginacaoCtrl = new PaginacaoCtrl();
        cancelarEditarUsuario();
    }

    public void iniciarDocumentos() {
        documento = new Documento();
        documentos = new ArrayList<Documento>();
        paginacaoCtrl = new PaginacaoCtrl();
        cancelarEditarDocumento();
        docAux = new Documento();
    }

    public void pesquisarUsuarios() {
        try {
            Usuario aux = new Usuario();
            aux.setCpf(usuario.getCpf());
            aux.setEmail(usuario.getEmail());
            aux.setMatricula(usuario.getMatricula());
            aux.setNome(usuario.getNome());
            aux.setRole(usuario.getRole());
            paginacaoCtrl.setEntidade(aux);
            paginacaoCtrl.setCont(
                    (int) usuarioDao.contar(usuario,
                    Consts.CRITERIA_USUARIO_CONJUNTIVA));
            paginacaoCtrl.primeira();
            realizarPesquisaUsuarios();
        } catch (Exception ex) {
            Util.addMsg(null, ex.getMessage(), FacesMessage.SEVERITY_WARN);
        }
    }

    public void editarUsuario() {
        try {
            usuarioDao.editar(usuario);
            usuario = new Usuario();
            editarUsuario = false;
            Util.addMsg(null, "Usuario editado com sucesso.",
                    FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            Util.addMsg(null, ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void excluirUsuario() {
        try {
            if (dono.equals(usuarioAux)) {
                throw new Exception("O administrador não pode ser removido.");
            } else if (usuario.equals(usuarioAux)) {
                throw new Exception("Cancele a edição de "
                        + usuario.getNome() + " antes de removê-lo.");
            }
            usuarioDao.excluir(usuarioAux);
            usuarios.remove(usuarioAux);
            Util.addMsg(null, "Usuario excluído com sucesso.",
                    FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            Util.addMsg(null, ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void iniciarEdidarUsuario() {
        editarUsuario = true;
    }

    public void cancelarEditarUsuario() {
        usuario = new Usuario();
        editarUsuario = false;
    }

    public void iniciarEdidarDocumento() {
        editarDocumento = true;
    }

    public void cancelarEditarDocumento() {
        if (tipo != null) {
            for (Campo c : tipo.getCampos()) {
                c.setValor(null);
            }
        }
        documento = new Documento();
        editarDocumento = false;
    }

    public void iniciarEdidarTipo() {
        editarTipo = true;
    }

    public void cancelarEditarTipo() {
        tipo = new Tipo();
        editarTipo = false;
    }

    public void realizarPesquisaUsuarios() {
        try {
            usuarios = usuarioDao.pesquisar(
                    (Usuario) paginacaoCtrl.getEntidade(),
                    paginacaoCtrl.getOffset(),
                    paginacaoCtrl.getLimit(),
                    Consts.CRITERIA_USUARIO_CONJUNTIVA);
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void realizarPesquisaDocumentos() {
        try {
            documentos = documentoDao.pesquisar(
                    (Documento) paginacaoCtrl.getEntidade(),
                    paginacaoCtrl.getOffset(),
                    paginacaoCtrl.getLimit(),
                    Consts.CRITERIA_DOCUMENTO_CONJUNTIVA);
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void paginar(ActionEvent ae) {
        String cmd = ae.getComponent().getId();
        if (cmd.equals("primeira")) {
            paginacaoCtrl.primeira();
        } else if (cmd.equals("anterior")) {
            paginacaoCtrl.anterior();
        } else if (cmd.equals("proxima")) {
            paginacaoCtrl.proxima();
        } else if (cmd.equals("ultima")) {
            paginacaoCtrl.ultima();
        }
    }
}
