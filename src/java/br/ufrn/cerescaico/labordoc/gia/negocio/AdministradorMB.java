/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.negocio;

import br.ufrn.cerescaico.labordoc.gia.modelo.Campo;
import br.ufrn.cerescaico.labordoc.gia.modelo.Documento;
import br.ufrn.cerescaico.labordoc.gia.modelo.Tipo;
import br.ufrn.cerescaico.labordoc.gia.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Rummenigge
 */
@ManagedBean
@SessionScoped
public class AdministradorMB extends AbstractUsuarioMB
        implements Serializable {

    private Campo campo = new Campo();

    public AdministradorMB() {
        super(Util.fc().getExternalContext().getSessionMap());
    }

    public Campo getCampo() {
        return campo;
    }

    public void setCampo(Campo campo) {
        this.campo = campo;
    }

    public void deletarCampo() {
        tipo.getCampos().remove(campo);
    }

    public void inserirCampo() {
        tipo.getCampos().add(new Campo());
    }

    public void limparCampos() {
        tipo.getCampos().clear();
    }

    public void criarTipoDeDocumento() {
        try {
            tipo.setNome(
                    Util.capitalizeString(tipo.getNome()));
            for (Campo c : tipo.getCampos()) {
                c.setNome(Util.capitalizeString(c.getNome()));
            }
            tipoDao.criar(tipo);
            tipo = new Tipo();
            Util.addMsg(null, "Tipo de documento criado com sucesso.");
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage());
        }
    }

    public void criarDocumento() {
        for (Campo c : tipo.getCampos()) {
            documento.getCampos().put(c.getNome(), c.getValor());
        }
        documento.setTipo(tipo);
        documentoDao.criar(documento);
        documento = new Documento();
        tipo = new Tipo();
    }
}
