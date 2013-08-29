/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.negocio.funcoes;

import br.ufrn.cerescaico.labordoc.gia.dao.UsuarioDao;
import br.ufrn.cerescaico.labordoc.gia.modelo.Usuario;

/**
 *
 * @author Rummenigge
 */
public class FuncaoFactory {
    
    public Funcao criarFuncaoCriarUsuario(
            UsuarioDao usuarioDao, 
            Usuario usuario) {
        return new FuncaoCriarUsuario();
    }
}
