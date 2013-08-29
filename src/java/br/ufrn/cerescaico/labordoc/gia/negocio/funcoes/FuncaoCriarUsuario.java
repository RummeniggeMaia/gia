/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.negocio.funcoes;

import br.ufrn.cerescaico.labordoc.gia.dao.UsuarioDao;
import br.ufrn.cerescaico.labordoc.gia.modelo.Usuario;
import br.ufrn.cerescaico.labordoc.gia.util.Consts;
import java.io.Serializable;

/**
 *
 * @author Rummenigge
 */
public class FuncaoCriarUsuario
        implements Funcao, Serializable {

    private final Integer id = Consts.FUNCAO_CRIAR_USUARIOS;
    private final String label = "Cadastrar usuário";
    private UsuarioDao dao;
    private Usuario usuario;

    public FuncaoCriarUsuario() {
    }

    public FuncaoCriarUsuario(UsuarioDao dao, Usuario usuario) {
        this.dao = dao;
        this.usuario = usuario;
    }
    
    @Override
    public Object executar() throws Exception {
        return dao.criar(usuario);
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public UsuarioDao getDao() {
        return dao;
    }

    public void setDao(UsuarioDao dao) {
        this.dao = dao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
