package br.ufrn.cerescaico.labordoc.gia.negocio;

import br.ufrn.cerescaico.labordoc.gia.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rummenigge
 */
public class UsuarioModel {

    private Usuario dono;
    private Usuario usuario;
    private Usuario usuarioAux;
    private List<Usuario> usuarios;
    private Paginacao paginacao;
    private boolean editarUsuario;

    public UsuarioModel() {
        usuario = new Usuario();
        usuarioAux = new Usuario();
        usuarios = new ArrayList<Usuario>();
        paginacao = new Paginacao();
        editarUsuario = false;
    }

    public Usuario getDono() {
        return dono;
    }

    public void setDono(Usuario dono) {
        this.dono = dono;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuarioAux() {
        return usuarioAux;
    }

    public void setUsuarioAux(Usuario usuarioAux) {
        this.usuarioAux = usuarioAux;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Paginacao getPaginacao() {
        return paginacao;
    }

    public void setPaginacao(Paginacao paginacao) {
        this.paginacao = paginacao;
    }

    public boolean isEditarUsuario() {
        return editarUsuario;
    }

    public void setEditarUsuario(boolean editarUsuario) {
        this.editarUsuario = editarUsuario;
    }
}
