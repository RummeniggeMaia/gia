package br.ufrn.cerescaico.labordoc.gia.negocio;

import br.ufrn.cerescaico.labordoc.gia.modelo.*;
import br.ufrn.cerescaico.labordoc.gia.util.Util;
import br.ufrn.cerescaico.labordoc.gia.validator.UsuarioValidator;
import br.ufrn.cerescaico.labordoc.gia.validator.ValidatorResult;
import java.io.*;
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
public class UsuarioMB extends AbstractUsuarioMB
        implements Serializable {


    public UsuarioMB() {
        super();
    }

//Documentos expert

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

    public void docLimitAlterado(AjaxBehaviorEvent abe) {
        try {
            documentoCtrl.iniciarPaginacao();
        } catch (Exception ex) {
            Util.addMsg(null, ex.getMessage(), FacesMessage.SEVERITY_WARN);
        }
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
//Expert usuarios

    public void editarConta() {
        try {
            if (!usuarioValido()) {
                return;
            }
            usuarioCtrl.editarUsuario();
            Util.addMsg(null, "Usuário editado com sucesso.",
                    FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            Util.addMsg(null, ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void excluirConta() {
        try {
            usuarioCtrl.excluirUsuario();
            Util.addMsg(null, "Usuario excluído com sucesso.",
                    FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            Util.addMsg(null, ex.getMessage(), FacesMessage.SEVERITY_ERROR);
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
    
    private boolean usuarioValido() {
        boolean valido = true;
        UsuarioValidator usuarioValidator = usuarioCtrl.getUsuarioValidator();
        ValidatorResult vr = null;
        vr = usuarioValidator.validarNome(usuarioModel.getUsuario().getNome());
        if (!vr.isValido()) {
            Util.addMsg(
                    "form_usuarios:campo_nome",
                    vr.getCausa(),
                    FacesMessage.SEVERITY_ERROR);
            valido = false;
        }
        vr = usuarioValidator.validarEmail(usuarioModel.getUsuario().getEmail());
        if (!vr.isValido()) {
            Util.addMsg(
                    "form_usuarios:campo_email",
                    vr.getCausa(),
                    FacesMessage.SEVERITY_ERROR);
            valido = false;
        }
        vr = usuarioValidator.validarCpf(usuarioModel.getUsuario().getCpf());
        if (!vr.isValido()) {
            Util.addMsg(
                    "form_usuarios:campo_cpf",
                    vr.getCausa(),
                    FacesMessage.SEVERITY_ERROR);
            valido = false;
        }
        if (usuarioModel.getUsuario().getDataNascimento() == null) {
            Util.addMsg(
                    "form_usuarios:campo_data_nasc",
                    "Data inválida.",
                    FacesMessage.SEVERITY_ERROR);
            valido = false;
        }
        return valido;
    }
    
    //Expert imagens
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
}
