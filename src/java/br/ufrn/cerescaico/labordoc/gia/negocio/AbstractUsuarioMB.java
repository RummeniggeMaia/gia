package br.ufrn.cerescaico.labordoc.gia.negocio;

import br.ufrn.cerescaico.labordoc.gia.converter.*;
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

    protected DocumentoModel documentoModel;
    protected DocumentoCtrl documentoCtrl; 
    protected TipoModel tipoModel;
    protected TipoCtrl tipoCtrl;
    protected UsuarioModel usuarioModel;
    protected UsuarioCtrl usuarioCtrl;
    protected ImagemModel imagemModel;
    protected ImagemCtrl imagemCtrl;
    //controle
    protected Map<String, Converter> converters;

    public AbstractUsuarioMB() {
        try {
            Map<String, Object> sessionMap = Util.getSessionMap();
            Usuario dono = (Usuario) sessionMap.get(Consts.USUARIO_LOGADO);
            if (dono == null) {
                throw new IllegalArgumentException(
                        "Houve um erro durante o login, acesso negado.");
            }
            VisitanteMB vMB =
                    (VisitanteMB) sessionMap.get("visitanteMB");
            
            usuarioModel = new UsuarioModel();
            usuarioModel.setDono(dono);
            usuarioCtrl = new UsuarioCtrl(vMB.getUsuarioDao(), usuarioModel); 
            documentoModel = new DocumentoModel();
            documentoCtrl = new DocumentoCtrl(documentoModel);
            tipoModel = new TipoModel();
            tipoCtrl = new TipoCtrl(tipoModel);
            imagemModel = new ImagemModel();
            imagemCtrl = new ImagemCtrl(imagemModel);
            
            converters = new HashMap<String, Converter>();
            converters.put(Consts.INTEIRO, new NumeConverter());
            converters.put(Consts.REAL, new RealConverter());
            converters.put(Consts.DATA, new DataConverter());
            converters.put(Consts.TEXTO, new TextConverter());
            converters.put("tipoConverter", new TipoConverter());  
        } catch (Exception ex) {
            Util.addMsg(null, ex.getMessage(), FacesMessage.SEVERITY_FATAL);
        }
    }

    public Map<String, Converter> getConverters() {
        return converters;
    }

    public void setConverters(Map<String, Converter> converters) {
        this.converters = converters;
    }

    public DocumentoModel getDocumentoModel() {
        return documentoModel;
    }

    public void setDocumentoModel(DocumentoModel documentoModel) {
        this.documentoModel = documentoModel;
    }

    public TipoModel getTipoModel() {
        return tipoModel;
    }

    public void setTipoModel(TipoModel tipoModel) {
        this.tipoModel = tipoModel;
    }

    public UsuarioModel getUsuarioModel() {
        return usuarioModel;
    }

    public void setUsuarioModel(UsuarioModel usuarioModel) {
        this.usuarioModel = usuarioModel;
    }

    public ImagemModel getImagemModel() {
        return imagemModel;
    }

    public void setImagemModel(ImagemModel imagemModel) {
        this.imagemModel = imagemModel;
    }
    
//    public void close() {
//        usuarioDao.close();
//        usuarioDao = null;
//    }
    
    public String sairDoSistema() {
        Util.fc().getExternalContext().invalidateSession();
        return Consts.HOME;
    }
    
//    public List<String> completeTipoNome(String q) {
//        List<String> lista = new ArrayList<String>();
//        for (Tipo t : tipos) {
//            if (t.getNome().matches(q + ".*")) {
//                lista.add(t.getNome());
//            }
//        }
//        return lista;
//    }
}
