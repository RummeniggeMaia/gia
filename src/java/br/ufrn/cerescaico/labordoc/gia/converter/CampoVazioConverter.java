package br.ufrn.cerescaico.labordoc.gia.converter;

import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Rummenigge
 */
public class CampoVazioConverter implements Converter, Serializable {

    @Override
    public Object getAsObject(
            FacesContext context,
            UIComponent component,
            String value) {

        return value == null ? "null" : value.trim();
    }

    @Override
    public String getAsString(
            FacesContext context,
            UIComponent component,
            Object value) {
        return value == null ? "" : value.toString();
    }
}
