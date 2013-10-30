package br.ufrn.cerescaico.labordoc.gia.negocio;

import br.ufrn.cerescaico.labordoc.gia.modelo.*;
import br.ufrn.cerescaico.labordoc.gia.util.Util;
import java.io.*;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.event.*;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.*;

/**
 *
 * @author Rummenigge
 */
@ManagedBean
@SessionScoped
public class AdministradorMB extends AbstractUsuarioMB
        implements Serializable {


    public AdministradorMB() {
        super();
    }

    public StreamedContent imagemStreamedContent() {
        return imagemCtrl.getStreamedContent();
    }

    public void carregarImagens() {
        try {
            imagemCtrl.carregarImagens(documentoModel.getDocAux());
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void limparImagens(AjaxBehaviorEvent abe) {
        imagemCtrl.limparImagens();
    }

    public void handleFileUpload(FileUploadEvent fue) {
        try {
            Imagem nova = new Imagem();
            UploadedFile uf = fue.getFile();
            nova.setNome(uf.getFileName());
            nova.setContentType(uf.getContentType());
            nova.setConteudo(uf.getContents());
            imagemCtrl.salvarImagem(nova);
            documentoCtrl.getDocumentoDao()
                    .editarImagens(documentoModel.getDocAux());
            documentoModel.getDocAux().getImagens().add(uf.getFileName());
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void deletarImagem() {
        try {
            String img = imagemCtrl.getModel().getImagemAux().getNome();
            imagemCtrl.deletarImagem();
            documentoCtrl.removerImagem(img);
            
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

//Documentos expert
    public void criarDocumento() {
        try {
            documentoCtrl.criarDocumento();
            Util.addMsg(null, "Documento cadastrado com sucesso.",
                    FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            Util.addMsg(null, "Houve um erro: " + ex.getMessage(),
                    FacesMessage.SEVERITY_ERROR);
        }
    }

    public void editarDocumento() {
        try {
            documentoCtrl.editarDocumento();
            Util.addMsg(null, "Documento editado com sucesso.",
                    FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            Util.addMsg(null, "Houve um erro: " + ex.getMessage(),
                    FacesMessage.SEVERITY_ERROR);
        }
    }

    public void excluirDocumento() {
        try {
            documentoCtrl.excluirDocumento();
            Util.addMsg(null, "Documento excluído com sucesso.",
                    FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            Util.addMsg(null, "Houve um erro: " + ex.getMessage(),
                    FacesMessage.SEVERITY_ERROR);
        }
    }

    public void pesquisarDocumentos() {
        try {
            documentoCtrl.pesquisarDocumentos();
        } catch (Exception ex) {
            Util.addMsg(null, "Houve um erro: " + ex.getMessage(),
                    FacesMessage.SEVERITY_WARN);
        }
    }

    public void realizarPesquisaDocumentos() {
        try {
            documentoCtrl.realizarPesquisaDocumentos();
        } catch (Exception ex) {
            Util.addMsg(null, "Houve um erro: " + ex.getMessage(),
                    FacesMessage.SEVERITY_WARN);
        }
    }

    public void iniciarEdidarDocumento() {
        documentoCtrl.iniciarEdidarDocumento();
    }

    public void cancelarEditarDocumento() {
        documentoCtrl.cancelarEditarDocumento();
    }

    public void docLimitAlterado(AjaxBehaviorEvent abe) {
        try {
            documentoCtrl.iniciarPaginacao();
        } catch (Exception ex) {
            Util.addMsg(null, ex.getMessage(), FacesMessage.SEVERITY_WARN);
        }
    }

    public void carregarDocumento(ActionEvent ae) {
        Map<String, Object> attribs = ae.getComponent().getAttributes();
        Documento doc = (Documento) attribs.get("documento");
        documentoCtrl.carregarDocumento(doc);
    }

    public void iniciarDocumentos() {
        try {
            documentoModel = new DocumentoModel();
            documentoCtrl.setModel(documentoModel);
        } catch (Exception ex) {
            Util.addMsg(null, ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void preencherDocTipos() {
        try {
            documentoModel.setTipos(
                    tipoCtrl.getTipoDao().pesquisarTodos(Tipo.class));
        } catch (Exception ex) {
            Util.addMsg(null, ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
//Tipos expert

    public void criarTipo() {
        try {
            tipoCtrl.criarTipo();
            Util.addMsg(null, "Tipo de documento criado com sucesso.",
                    FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void editarTipo() {
        try {
            tipoCtrl.editarTipo();
            Util.addMsg(null, "Tipo de documento editado com sucesso.",
                    FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void excluirTipo() {
        try {
            tipoCtrl.excluirTipo();
            Util.addMsg(null, "Tipo de documento excluído com sucesso.",
                    FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void pesquisarTipos() {
        try {
            tipoCtrl.pesquisarTipos();
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage(), FacesMessage.SEVERITY_WARN);
        }
    }

    public void realizarPesquisaTipos() {
        try {
            tipoCtrl.realizarPesquisaTipos();
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void iniciarTipos() {
        try {
            tipoModel = new TipoModel();
            tipoCtrl.setModel(tipoModel);
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage(), FacesMessage.SEVERITY_WARN);
        }
    }

    public void iniciarEdidarTipo() {
        tipoCtrl.iniciarEditarTipo();
    }

    public void cancelarEditarTipo() {
        tipoCtrl.cancelarEditarTipo();
    }

    public void tipoLimitAlterado(AjaxBehaviorEvent abe) {
        try {
            tipoCtrl.iniciarPaginacao();
        } catch (Exception ex) {
            Util.addMsg(null, ex.getMessage(), FacesMessage.SEVERITY_WARN);
        }
    }

    public void contemTipo() {
        tipoCtrl.contemTipo(null);
    }

    public void inserirCampo() {
        tipoCtrl.inserirCampo();
    }

    public void limparCampos() {
        tipoCtrl.limparCampos();
    }

    public void deletarCampo() {
        tipoCtrl.deletarCampo();
    }
//Expert usuarios

    public void editarUsuario() {
        try {
            usuarioCtrl.editarUsuario();
            Util.addMsg(null, "Usuario editado com sucesso.",
                    FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            Util.addMsg(null, ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void excluirUsuario() {
        try {
            usuarioCtrl.excluirUsuario();
            Util.addMsg(null, "Usuario excluído com sucesso.",
                    FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            Util.addMsg(null, ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void pesquisarUsuarios() {
        try {
            usuarioCtrl.pesquisarUsuarios();
        } catch (Exception ex) {
            Util.addMsg(null, ex.getMessage(), FacesMessage.SEVERITY_WARN);
        }
    }

    public void iniciarUsuarios() {
        try {
            usuarioModel = new UsuarioModel();
            usuarioCtrl.setModel(usuarioModel);
        } catch (Exception ex) {
            Util.addMsg(null, ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void iniciarEdidarUsuario() {
        usuarioCtrl.iniciarEdidarUsuario();
    }

    public void cancelarEditarUsuario() {
        usuarioCtrl.cancelarEditarUsuario();
    }

    public void realizarPesquisaUsuarios() {
        try {
            usuarioCtrl.realizarPesquisaUsuarios();
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage(), FacesMessage.SEVERITY_WARN);
        }
    }

    public void usuarioLimitAlterado(AjaxBehaviorEvent abe) {
        try {
            usuarioCtrl.iniciarPaginacao();
        } catch (Exception ex) {
            Util.addMsg(null, ex.getMessage(), FacesMessage.SEVERITY_WARN);
        }
    }
}
