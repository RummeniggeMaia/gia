package br.ufrn.cerescaico.labordoc.gia.negocio;

import br.ufrn.cerescaico.labordoc.gia.modelo.*;
import br.ufrn.cerescaico.labordoc.gia.util.*;
import java.io.Serializable;
import javax.faces.bean.*;

/**
 *
 * @author Rummenigge
 */
@ManagedBean
@SessionScoped
public class UsuarioMB extends AbstractUsuarioMB implements Serializable {

    public UsuarioMB() {
        super(Util.fc().getExternalContext().getSessionMap());
    }

    public String sairDoSistema() {
        Util.fc().getExternalContext().invalidateSession();
        return Consts.HOME;
    }

    public void editarConta() {
        try {
            usuarioDao.editar(dono);
            Util.addMsg(null, "Dados editados com sucesso.");
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage());
        }
    }

    public String excluirConta() {
        try {
            usuarioDao.excluir(dono);
            return sairDoSistema();
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage());
            return "";
        }
    }

    public void pesquisarDocumentos() {
        pesquisaCtrl.setCriteria(Consts.CRITERIA_DOCUMENTO);
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
                    pesquisaCtrl.getCriteria());
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage());
        }
    }
}
