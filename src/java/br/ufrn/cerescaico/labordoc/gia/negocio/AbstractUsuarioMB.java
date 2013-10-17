package br.ufrn.cerescaico.labordoc.gia.negocio;

import br.ufrn.cerescaico.labordoc.gia.converter.*;
import br.ufrn.cerescaico.labordoc.gia.dao.*;
import br.ufrn.cerescaico.labordoc.gia.modelo.*;
import br.ufrn.cerescaico.labordoc.gia.util.*;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.convert.Converter;

/**
 *
 * @author Rummenigge
 */
public abstract class AbstractUsuarioMB {

    protected Usuario dono;
    protected Usuario usuario;
    protected List<Usuario> usuarios;
    protected UsuarioDao usuarioDao;
    protected Documento documento;
    protected List<Documento> documentos;
    protected DocumentoDao documentoDao;
    protected Tipo tipo;
    protected TipoDao tipoDao;
    protected List<Tipo> tipos;
    //controle
    protected Map<String, Converter> converters;
    protected PaginacaoCtrl paginacaoCtrl = new PaginacaoCtrl();

    public AbstractUsuarioMB() {
        try {
            Map<String, Object> sessionMap = Util.getSessionMap();
            dono = (Usuario) sessionMap.get(Consts.USUARIO_LOGADO);
            if (dono == null) {
                throw new IllegalArgumentException(
                        "Houve um erro durante o login, acesso negado.");
            }
            VisitanteMB vMB =
                    (VisitanteMB) sessionMap.get("visitanteMB");
            usuarioDao = vMB.getUsuarioDao();
            usuario = new Usuario();
            usuarios = new ArrayList<Usuario>(2);
            documentoDao = new DocumentoDao();
            documento = new Documento();
            documentos = new ArrayList<Documento>(2);
            tipoDao = new TipoDao();
            tipo = new Tipo();
            tipos = tipoDao.pesquisarTodos(Tipo.class);
            converters = new HashMap<String, Converter>();
            converters.put(Consts.INTEIRO, new NumeConverter());
            converters.put(Consts.REAL, new RealConverter());
            converters.put(Consts.DATA, new DataConverter());
            converters.put(Consts.TEXTO, new TextConverter());
            converters.put("tipoConverter", new TipoConverter(tipos));
        } catch (Exception ex) {
            Util.addMsg(null, ex.getMessage(), FacesMessage.SEVERITY_FATAL);
        }
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

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public UsuarioDao getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    public DocumentoDao getDocumentoDao() {
        return documentoDao;
    }

    public void setDocumentoDao(DocumentoDao documentoDao) {
        this.documentoDao = documentoDao;
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

    public PaginacaoCtrl getPaginacaoCtrl() {
        return paginacaoCtrl;
    }

    public void setPaginacaoCtrl(PaginacaoCtrl pesquisaCtrl) {
        this.paginacaoCtrl = pesquisaCtrl;
    }

    public Map<String, Converter> getConverters() {
        return converters;
    }

    public void setConverters(Map<String, Converter> converters) {
        this.converters = converters;
    }
    
    public void close() {
        usuarioDao.close();
        usuarioDao = null;
        documentoDao.close();
        documentoDao = null;
        tipoDao.close();
        tipoDao = null;
    }
    
    public String sairDoSistema() {
        Util.fc().getExternalContext().invalidateSession();
        return Consts.HOME;
    }
    
    public List<String> completeTipoNome(String q) {
        List<String> lista = new ArrayList<String>();
        for (Tipo t : tipos) {
            if (t.getNome().matches(q + ".*")) {
                lista.add(t.getNome());
            }
        }
        return lista;
    }
}
