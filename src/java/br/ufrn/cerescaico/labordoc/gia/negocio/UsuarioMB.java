package br.ufrn.cerescaico.labordoc.gia.negocio;

import br.ufrn.cerescaico.labordoc.gia.util.*;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Rummenigge
 */
@ManagedBean
@SessionScoped
public class UsuarioMB extends AbstractUsuarioMB implements Serializable {

    public UsuarioMB() {
        super();
    }

    public void editarConta() {
        try {
//            usuarioDao.editar(dono);
//            Util.addMsg(null, "Dados editados com sucesso.", 
//                    FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public String excluirConta() {
        try {
//            usuarioDao.excluir(dono);
//            Util.addMsg(null, "Conta excluída com sucesso.", 
//                    FacesMessage.SEVERITY_INFO);
            return sairDoSistema();
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage(), FacesMessage.SEVERITY_ERROR);
            return "";
        }
    }
    
    public void iniciarDocumentos() {
//        documento = new Documento();
//        documentos = new ArrayList<Documento>();
//        paginacaoCtrl = new Paginacao();
    }

    public void pesquisarDocumentos() {
        try {
//            documento = new Documento();
//            documentos.clear();
//            if (tipo == null) {
//                return;
//            }
//            documento.setTipo(tipo);
//            for (Campo c : tipo.getCampos()) {
//                documento.getCampos().put(c.getNome(), c.getValor());
//            }
//            paginacaoCtrl.setCont(
//                    documentoDao.contar(documento, 
//                    Consts.CRITERIA_DOCUMENTO_CONJUNTIVA));
//            paginacaoCtrl.setEntidade(documento);
//            paginacaoCtrl.primeira();
//            realizarPesquisaDocumentos();
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage(), FacesMessage.SEVERITY_WARN);
        }
    }
    
    public void realizarPesquisaDocumentos() {
        try {
//            documentos = documentoDao.pesquisar(
//                    (Documento) paginacaoCtrl.getEntidade(),
//                    paginacaoCtrl.getOffset(),
//                    paginacaoCtrl.getLimit(),
//                    Consts.CRITERIA_DOCUMENTO_CONJUNTIVA);
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void paginar(ActionEvent ae) {
        String cmd = ae.getComponent().getId();
        if (cmd.equals("primeira")) {
            paginacaoCtrl.primeira();
        } else if (cmd.equals("anterior")) {
            paginacaoCtrl.anterior();
        } else if (cmd.equals("proxima")) {
            paginacaoCtrl.proxima();
        } else if (cmd.equals("ultima")) {
            paginacaoCtrl.ultima();
        }
    }
}
