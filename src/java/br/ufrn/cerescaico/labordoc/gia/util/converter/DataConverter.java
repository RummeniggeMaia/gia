/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.util.converter;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Rummenigge
 */
@FacesConverter(value="data")
public class DataConverter implements Converter {

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public Object getAsObject(
            FacesContext context,
            UIComponent component,
            String value) {

        try {
            return sdf.parse(value);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getAsString(
            FacesContext context,
            UIComponent component,
            Object value) {


        try {
            Date d = (Date) value;
            return sdf.format(d);
        } catch (Exception e) {
            return null;
        }
    }
}
