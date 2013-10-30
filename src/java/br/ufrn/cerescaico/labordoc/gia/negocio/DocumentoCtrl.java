package br.ufrn.cerescaico.labordoc.gia.negocio;

import br.ufrn.cerescaico.labordoc.gia.dao.DocumentoDao;
import br.ufrn.cerescaico.labordoc.gia.modelo.Campo;
import br.ufrn.cerescaico.labordoc.gia.modelo.Documento;
import br.ufrn.cerescaico.labordoc.gia.modelo.Tipo;
import br.ufrn.cerescaico.labordoc.gia.util.Consts;
import br.ufrn.cerescaico.labordoc.gia.util.Util;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;

/**
 * Util.addMsg(null, e.getMessage(), FacesMessage.SEVERITY_ERROR);
 *
 * @author Rummenigge
 */
public class DocumentoCtrl implements Serializable {

    private DocumentoModel model;
    private DocumentoDao documentoDao;

    public DocumentoCtrl() throws Exception {
        model = new DocumentoModel();
        documentoDao = new DocumentoDao();
    }

    public DocumentoCtrl(DocumentoModel model) throws Exception {
        this.model = model;
        documentoDao = new DocumentoDao();
    }

    public DocumentoCtrl(DocumentoModel model, DocumentoDao documentoDao) {
        this.model = model;
        this.documentoDao = documentoDao;
    }

    public DocumentoDao getDocumentoDao() {
        return documentoDao;
    }

    public void setDocumentoDao(DocumentoDao documentoDao) {
        this.documentoDao = documentoDao;
    }

    public DocumentoModel getModel() {
        return model;
    }

    public void setModel(DocumentoModel model) {
        this.model = model;
    }

    public void criarDocumento() throws Exception {
        for (Campo c : model.getTipo().getCampos()) {
            model.getDocumento().getCampos().put(c.getNome(), c.getValor());
            c.setValor(null);
        }
        model.getDocumento().setTipo(model.getTipo());
        documentoDao.criar(model.getDocumento());
        model.setDocumento(new Documento());
    }

    public void editarDocumento() throws Exception {
        model.getDocumento().getCampos().clear();
        for (Campo c : model.getTipo().getCampos()) {
            model.getDocumento().getCampos().put(c.getNome(), c.getValor());
            c.setValor(null);
        }
        model.getDocumento().setTipo(model.getTipo());
        documentoDao.editar(model.getDocumento());
        model.setDocumento(new Documento());
        model.setEditarDocumento(false);
    }

    public void excluirDocumento() throws Exception {
        if (model.getDocumento().equals(model.getDocAux())) {
            throw new Exception(
                    "Cancele a edição do documento antes de removê-lo.");
        }
        documentoDao.excluir(model.getDocAux());
        model.getDocumentos().remove(model.getDocAux());
        model.setDocAux(new Documento());
        model.getPaginacao().setCont(model.getPaginacao().getCont() - 1);
        iniciarPaginacao();
    }

    public void pesquisarDocumentos() throws Exception {
        model.setDocumento(new Documento());
        model.getDocumentos().clear();
        if (model.getTipo() == null) {
            return;
        }
        model.getDocumento().setTipo(model.getTipo());
        for (Campo c : model.getTipo().getCampos()) {
            model.getDocumento().getCampos().put(c.getNome(), c.getValor());
        }
        model.getPaginacao().setCont(
                documentoDao.contar(model.getDocumento(),
                Consts.CRITERIA_DOCUMENTO_CONJUNTIVA));
        model.getPaginacao().setEntidade(model.getDocumento());
        iniciarPaginacao();
    }

    public void realizarPesquisaDocumentos() throws Exception {
        List<Documento> docs = documentoDao.pesquisar(
                (Documento) model.getPaginacao().getEntidade(),
                model.getPaginacao().getOffset(),
                model.getPaginacao().getLimit(),
                Consts.CRITERIA_DOCUMENTO_CONJUNTIVA);
        model.setDocumentos(docs);
    }

    public void carregarDocumento(Documento doc) {
        try {
            for (Tipo t : model.getTipos()) {
                if (t.equals(doc.getTipo())) {
                    model.setTipo(t);
                }
            }
            for (Campo c : model.getTipo().getCampos()) {
                c.setValor(doc.getCampos().get(c.getNome()));
            }
            model.setDocumento(doc);
        } catch (Exception e) {
            Util.addMsg(null, "Erro durante a edição do documento.",
                    FacesMessage.SEVERITY_ERROR);
        }
    }

    public void iniciarEdidarDocumento() {
        model.setEditarDocumento(true);
    }

    public void cancelarEditarDocumento() {
        if (model.getTipo() != null) {
            for (Campo c : model.getTipo().getCampos()) {
                c.setValor(null);
            }
        }
        model.setDocumento(new Documento());
        model.setEditarDocumento(false);
    }

    public void iniciarPaginacao() throws Exception {
        model.getPaginacao().primeira();
        realizarPesquisaDocumentos();
    }
    
    public void removerImagem(String img) {
        model.getDocAux().getImagens().remove(img);
        documentoDao.editarImagens(model.getDocAux());
    }
}
