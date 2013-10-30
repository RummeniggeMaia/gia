package br.ufrn.cerescaico.labordoc.gia.negocio;

import br.ufrn.cerescaico.labordoc.gia.modelo.Documento;
import br.ufrn.cerescaico.labordoc.gia.modelo.Tipo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rummenigge
 */
public class DocumentoModel implements Serializable {

    private Documento documento;
    private List<Documento> documentos;
    private Documento docAux;
    private boolean editarDocumento;
    private Tipo tipo;
    private List<Tipo> tipos;
    private Paginacao paginacao;

    public DocumentoModel() throws Exception {
        documento = new Documento();
        documentos = new ArrayList<Documento>();
        docAux = new Documento();
        editarDocumento = false;
        tipo = new Tipo();
        tipos = new ArrayList<Tipo>();
        paginacao = new Paginacao();
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    public Documento getDocAux() {
        return docAux;
    }

    public void setDocAux(Documento docAux) {
        this.docAux = docAux;
    }

    public boolean isEditarDocumento() {
        return editarDocumento;
    }

    public void setEditarDocumento(boolean editarDocumento) {
        this.editarDocumento = editarDocumento;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public List<Tipo> getTipos() {
        return tipos;
    }

    public void setTipos(List<Tipo> tipos) {
        this.tipos = tipos;
    }

    public Paginacao getPaginacao() {
        return paginacao;
    }

    public void setPaginacao(Paginacao paginacao) {
        this.paginacao = paginacao;
    }
}
