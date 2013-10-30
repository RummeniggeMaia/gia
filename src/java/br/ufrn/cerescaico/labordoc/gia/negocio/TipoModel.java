package br.ufrn.cerescaico.labordoc.gia.negocio;

import br.ufrn.cerescaico.labordoc.gia.dao.TipoDao;
import br.ufrn.cerescaico.labordoc.gia.modelo.Campo;
import br.ufrn.cerescaico.labordoc.gia.modelo.Tipo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rummenigge
 */
public class TipoModel implements Serializable {

    private Tipo tipo;
    private TipoDao tipoDao;
    private List<Tipo> tipos;
    private Tipo tipoAux;
    private boolean editarTipo;
    private Campo campo;
    private Paginacao paginacao;
    
    public TipoModel() throws Exception {
        tipo = new Tipo();
        tipoDao = new TipoDao();
        tipos = new ArrayList<Tipo>();
        tipoAux = new Tipo();
        campo = new Campo();
        editarTipo = false;
        paginacao = new Paginacao();
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public TipoDao getTipoDao() {
        return tipoDao;
    }

    public void setTipoDao(TipoDao tipoDao) {
        this.tipoDao = tipoDao;
    }

    public List<Tipo> getTipos() {
        return tipos;
    }

    public void setTipos(List<Tipo> tipos) {
        this.tipos = tipos;
    }

    public Tipo getTipoAux() {
        return tipoAux;
    }

    public void setTipoAux(Tipo tipoAux) {
        this.tipoAux = tipoAux;
    }

    public boolean isEditarTipo() {
        return editarTipo;
    }

    public void setEditarTipo(boolean editarTipo) {
        this.editarTipo = editarTipo;
    }

    public Campo getCampo() {
        return campo;
    }

    public void setCampo(Campo campo) {
        this.campo = campo;
    }

    public Paginacao getPaginacao() {
        return paginacao;
    }

    public void setPaginacao(Paginacao paginacao) {
        this.paginacao = paginacao;
    }
}
