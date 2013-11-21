package br.ufrn.cerescaico.labordoc.gia.negocio;

import br.ufrn.cerescaico.labordoc.gia.dao.TipoDao;
import br.ufrn.cerescaico.labordoc.gia.modelo.Campo;
import br.ufrn.cerescaico.labordoc.gia.modelo.Tipo;
import br.ufrn.cerescaico.labordoc.gia.util.Consts;
import br.ufrn.cerescaico.labordoc.gia.validator.TipoValidator;
import java.util.List;

/**
 *
 * @author Rummenigge
 */
public class TipoCtrl {

    private TipoModel model;
    private TipoDao tipoDao;
    private TipoValidator tipoValidator = new TipoValidator();

    public TipoCtrl() throws Exception {
        model = new TipoModel();
        tipoDao = new TipoDao();
    }

    public TipoCtrl(TipoModel tipoModel) throws Exception {
        this.model = tipoModel;
        tipoDao = new TipoDao();
    }

    public TipoCtrl(TipoModel model, TipoDao tipoDao) {
        this.model = model;
        this.tipoDao = tipoDao;
    }

    public TipoModel getModel() {
        return model;
    }

    public void setModel(TipoModel model) {
        this.model = model;
    }

    public TipoDao getTipoDao() {
        return tipoDao;
    }

    public void setTipoDao(TipoDao tipoDao) {
        this.tipoDao = tipoDao;
    }

    public void criarTipo() throws Exception {
        tipoDao.criar(model.getTipo());
        model.setTipo(new Tipo());
    }

    public TipoValidator getTipoValidator() {
        return tipoValidator;
    }

    public void setTipoValidator(TipoValidator tipoValidator) {
        this.tipoValidator = tipoValidator;
    }

    public void excluirTipo() throws Exception {
        if (model.getTipoAux().equals(model.getTipo())) {
            throw new Exception("Cancele a edição do tipo "
                    + model.getTipoAux().getNome() + " para poder removê-lo.");
        }
        tipoDao.excluir(model.getTipoAux());
        model.getTipos().remove(model.getTipoAux());
        model.setTipoAux(new Tipo());
        model.getPaginacao().setCont(model.getPaginacao().getCont() - 1);
        iniciarPaginacao();
    }

    public void editarTipo() throws Exception {
        tipoDao.editar(model.getTipo());
        cancelarEditarTipo();
    }

    public void pesquisarTipos() throws Exception {
        Tipo aux = new Tipo();
        aux.setNome(model.getTipo().getNome());
        model.getPaginacao().setEntidade(aux);
        model.getPaginacao().setCont(
                (int) tipoDao.contar(model.getTipo(),
                Consts.CRITERIA_TIPO_NOME));
        iniciarPaginacao();
    }

    public void realizarPesquisaTipos() throws Exception {
        List<Tipo> tipos = tipoDao.pesquisar(
                model.getTipo(),
                model.getPaginacao().getOffset(),
                model.getPaginacao().getLimit(),
                Consts.CRITERIA_TIPO_NOME);
        model.setTipos(tipos);
    }

    public void iniciarEditarTipo() {
        model.setEditarTipo(true);
    }

    public void cancelarEditarTipo() {
        model.setTipo(new Tipo());
        model.setEditarTipo(false);
    }

    public void deletarCampo() {
        model.getTipo().getCampos().remove(model.getCampo());
    }

    public void inserirCampo() {
        model.getTipo().getCampos().add(new Campo());
    }

    public void limparCampos() {
        if (model.getTipo() != null) {
            model.getTipo().getCampos().clear();
        }
    }

    public void iniciarPaginacao() throws Exception {
        model.getPaginacao().primeira();
        realizarPesquisaTipos();
    }

    public boolean contemTipo() {
        try {
            Tipo t = tipoDao.pesquisarUm(
                    model.getTipo(), Consts.CRITERIA_TIPO_NOME_UNICO);
            return t != null;
        } catch (Exception e) {
            return false;
        }
    }
}
