/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.util.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Rummenigge
 */
@FacesConverter(value="real")
public class RealConverter implements Converter {

    @Override
    public Object getAsObject(
            FacesContext context, 
            UIComponent component, 
            String value) {
        
        try {
            return new Double(value);
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
            return new Double(value.toString()).toString();
        } catch (Exception e) {
            return null;
        }
    }
    
}
