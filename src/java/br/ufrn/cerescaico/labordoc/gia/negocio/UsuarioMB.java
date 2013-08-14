/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.negocio;

import br.ufrn.cerescaico.labordoc.gia.modelo.Usuario;
import br.ufrn.cerescaico.labordoc.gia.util.Util;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rummenigge
 */
@ManagedBean
@RequestScoped
public class UsuarioMB {

    /**
     * Creates a new instance of UsuarioMB
     */
    public Usuario usuario;

    public UsuarioMB() {
        usuario = (Usuario) Util.getFacesSession().getAttribute("logado");
    }

    public void editarConta() {
    }

    public void excluirConta() {
    }

    public void pesquisarDocumento() {
    }

    public String sairDoSistema() {
        HttpSession sessao = Util.getFacesSession();
        sessao.removeAttribute("logado");
        sessao.invalidate();
        usuario = new Usuario();
        return "home";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
