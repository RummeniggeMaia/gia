/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.negocio;

import br.ufrn.cerescaico.labordoc.gia.dao.UsuarioDao;
import br.ufrn.cerescaico.labordoc.gia.modelo.Usuario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Rummenigge
 */
@ManagedBean
@RequestScoped
public class VisitanteMB {

    private UsuarioDao usuarioDao;
    private Usuario visitante;
    private Usuario usuario;
    

    public VisitanteMB() {
    }

    public void criarUsuario() {
    }
}
