/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.negocio;

import br.ufrn.cerescaico.labordoc.gia.dao.DocumentoDao;
import br.ufrn.cerescaico.labordoc.gia.dao.UsuarioDao;
import br.ufrn.cerescaico.labordoc.gia.modelo.Documento;
import br.ufrn.cerescaico.labordoc.gia.modelo.Usuario;
import br.ufrn.cerescaico.labordoc.gia.util.Consts;
import br.ufrn.cerescaico.labordoc.gia.util.Util;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Rummenigge
 */
public abstract class AbstractUsuarioMB implements Serializable {
    
    protected Usuario dono = new Usuario();
    protected Usuario usuario;
    protected List<Usuario> usuarios;
    protected UsuarioDao usuarioDao;
    protected Documento documento;
    protected List<Documento> documentos;
    protected DocumentoDao documentoDao;
    //controle
    protected PesquisaCtrl pesquisaCtrl = new PesquisaCtrl();

    public AbstractUsuarioMB() {
    }

    public AbstractUsuarioMB(Map<String, Object> sessionMap) {
        try {
            dono = (Usuario) sessionMap.get(Consts.USUARIO_LOGADO);
            usuarioDao = (UsuarioDao) sessionMap.get(Consts.USUARIO_DAO);
            if (dono == null || usuarioDao == null) {
                throw new IllegalArgumentException(
                        "Usuário ou DAO nulo.");
            }
            documentoDao = new DocumentoDao();
            documentos = new ArrayList<Documento>(2);
            documento = new Documento();
            
        } catch (UnknownHostException uhe) {
            Util.addMsg(null, uhe.getMessage());
        } catch (IllegalArgumentException iae) {
            Util.addMsg(null, iae.getMessage());
        }
    }
    
    public void pesqusiarDocumentos() {
        documentos = documentoDao.pesquisar(
                documento, 
                pesquisaCtrl.proximoOffset(), 
                pesquisaCtrl.getLimit(), 
                pesquisaCtrl.getCriteria());
    }

    public Usuario getDono() {
        return dono;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public Documento getDocumento() {
        return documento;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDono(Usuario dono) {
        this.dono = dono;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }
}
