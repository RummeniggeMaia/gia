package br.ufrn.cerescaico.labordoc.gia.negocio;

import br.ufrn.cerescaico.labordoc.gia.dao.UsuarioDao;
import br.ufrn.cerescaico.labordoc.gia.modelo.Usuario;
import br.ufrn.cerescaico.labordoc.gia.util.Consts;
import br.ufrn.cerescaico.labordoc.gia.validator.UsuarioValidator;
import java.util.List;

/**
 *
 * @author Rummenigge
 */
public class UsuarioCtrl {

    private UsuarioDao usuarioDao;
    private UsuarioModel model;
    private UsuarioValidator usuarioValidator = new UsuarioValidator();

    public UsuarioCtrl() throws Exception {
        usuarioDao = new UsuarioDao();
        model = new UsuarioModel();
    }

    public UsuarioCtrl(UsuarioModel model) throws Exception {
        this.model = model;
        usuarioDao = new UsuarioDao();
    }

    public UsuarioCtrl(UsuarioDao usuarioDao, UsuarioModel model) {
        this.usuarioDao = usuarioDao;
        this.model = model;
    }

    public UsuarioDao getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public UsuarioModel getModel() {
        return model;
    }

    public void setModel(UsuarioModel model) {
        this.model = model;
    }

    public UsuarioValidator getUsuarioValidator() {
        return usuarioValidator;
    }

    public void setUsuarioValidator(UsuarioValidator usuarioValidator) {
        this.usuarioValidator = usuarioValidator;
    }

    public void editarUsuario() throws Exception {
        usuarioDao.editar(model.getUsuario());
        model.setUsuario(new Usuario());
        model.setEditarUsuario(false);
    }

    public void excluirUsuario() throws Exception {
        if (model.getDono().equals(model.getUsuarioAux())) {
            throw new Exception("O administrador não pode ser removido.");
        } else if (model.getUsuario().equals(model.getUsuarioAux())) {
            throw new Exception("Cancele a edição de "
                    + model.getUsuario().getNome() + " antes de removê-lo.");
        }
        usuarioDao.excluir(model.getUsuarioAux());
        model.getUsuarios().remove(model.getUsuarioAux());
        model.getPaginacao().setCont(model.getPaginacao().getCont() - 1);
        iniciarPaginacao();
    }

    public void pesquisarUsuarios() throws Exception {
        Usuario aux = new Usuario();
        aux.setCpf(model.getUsuario().getCpf());
        aux.setEmail(model.getUsuario().getEmail());
        aux.setMatricula(model.getUsuario().getMatricula());
        aux.setNome(model.getUsuario().getNome());
        aux.setRole(model.getUsuario().getRole());
        aux.setDataNascimento(model.getUsuario().getDataNascimento());
        aux.setSexo(model.getUsuario().getSexo());
        model.getPaginacao().setEntidade(aux);
        model.getPaginacao().setCont(
                (int) usuarioDao.contar(model.getUsuario(),
                Consts.CRITERIA_USUARIO_CONJUNTIVA));
        iniciarPaginacao();
    }

    public void realizarPesquisaUsuarios() throws Exception {
        List<Usuario> usuarios = usuarioDao.pesquisar(
                (Usuario) model.getPaginacao().getEntidade(),
                model.getPaginacao().getOffset(),
                model.getPaginacao().getLimit(),
                Consts.CRITERIA_USUARIO_CONJUNTIVA);
        model.setUsuarios(usuarios);
    }

    public void iniciarEdidarUsuario() {
        model.setEditarUsuario(true);
    }

    public void cancelarEditarUsuario() {
        model.setUsuario(new Usuario());
        model.setEditarUsuario(false);
    }

    public void iniciarPaginacao() throws Exception {
        model.getPaginacao().primeira();
        realizarPesquisaUsuarios();
    }
}
