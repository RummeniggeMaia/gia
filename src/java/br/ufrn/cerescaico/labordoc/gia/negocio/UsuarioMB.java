package br.ufrn.cerescaico.labordoc.gia.negocio;

import br.ufrn.cerescaico.labordoc.gia.modelo.*;
import br.ufrn.cerescaico.labordoc.gia.util.*;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;

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

    public String sairDoSistema() {
        Util.fc().getExternalContext().invalidateSession();
        return Consts.HOME;
    }

    public void editarConta() {
        try {
            usuarioDao.editar(dono);
            Util.addMsg(null, "Dados editados com sucesso.", 
                    FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public String excluirConta() {
        try {
            usuarioDao.excluir(dono);
            Util.addMsg(null, "Conta excluída com sucesso.", 
                    FacesMessage.SEVERITY_INFO);
            return sairDoSistema();
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage(), FacesMessage.SEVERITY_ERROR);
            return "";
        }
    }

    public void pesquisarDocumentos() {
        pesquisaCtrl.setOffset(0);
        pesquisaCtrl.setLimit(10);
        documento.setTipo(tipo);
        for (Campo c : tipo.getCampos()) {
            documento.getCampos().put(c.getNome(), c.getValor());
        }
        try {
            documentos = documentoDao.pesquisar(
                    documento, 
                    pesquisaCtrl.getOffset(), 
                    pesquisaCtrl.getLimit(), 
                    Consts.CRITERIA_DOCUMENTO);
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage(), FacesMessage.SEVERITY_WARN);
        }
    }
}
