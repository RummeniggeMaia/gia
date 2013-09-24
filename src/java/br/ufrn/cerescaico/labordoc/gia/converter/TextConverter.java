/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Rummenigge
 */
@FacesConverter(value = "text")
public class TextConverter implements Converter {

    @Override
    public Object getAsObject(
            FacesContext context,
            UIComponent component,
            String value) {

        return value;
    }

    @Override
    public String getAsString(
            FacesContext context,
            UIComponent component,
            Object value) {


        return value == null
                ? ""
                : value.toString().equals("null")
                ? ""
                : value.toString();
    }
}
