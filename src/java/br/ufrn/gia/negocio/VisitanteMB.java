/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufrn.gia.negocio;

import br.com.ufrn.gia.modelo.Usuario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Rummenigge
 */
@ManagedBean
@RequestScoped
public class VisitanteMB {

    private Usuario visitante;
    private Usuario usuario;
    

    public VisitanteMB() {
    }

    public void criarUsuario() {
    }
}
