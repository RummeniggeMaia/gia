/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.converter;

import br.ufrn.cerescaico.labordoc.gia.modelo.Usuario;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Rummenigge
 */
public class UsuarioConverter implements Converter {

    private List<Usuario> list;

    public UsuarioConverter(List<Usuario> list) {
        this.list = list;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            for (Usuario u : list) {
                if (u.getCpf().equals(value)) {
                    return u;
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value == null ? null : value.toString();
    }
}
