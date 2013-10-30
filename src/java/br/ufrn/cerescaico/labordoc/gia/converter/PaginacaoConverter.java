/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.converter;

import br.ufrn.cerescaico.labordoc.gia.negocio.Paginacao;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Rummenigge
 */
public class PaginacaoConverter implements Converter, Serializable {

    private Paginacao pc;

    public PaginacaoConverter(Paginacao pc) {
        this.pc = pc;
    }

    @Override
    public Object getAsObject(
            FacesContext context,
            UIComponent component,
            String value) {
        try {
            if (value == null) {
                return 0;
            } else if (value.matches("\\d+")) {
                return Integer.parseInt(value);
            } else if (value.matches("\\d+\\s*/\\s*\\d+")) {
                value = value.replaceAll("\\s*/\\s*\\d+", "");
                return Integer.parseInt(value);
            }
            throw new Exception();
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public String getAsString(
            FacesContext context,
            UIComponent component,
            Object value) {

        return "" + pc.paginaAtual() + " / " + pc.totalDePaginas();
    }
}
