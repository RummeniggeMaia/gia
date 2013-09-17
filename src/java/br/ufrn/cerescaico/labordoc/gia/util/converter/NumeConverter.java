package br.ufrn.cerescaico.labordoc.gia.util.converter;

import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Rummenigge
 */
@FacesConverter(value="nume")
public class NumeConverter implements Converter, Serializable {

    @Override
    public Object getAsObject(
            FacesContext context, 
            UIComponent component, 
            String value) {
        
        try {
            return new Integer(value);
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
            if (value instanceof Double) {
                Double aux = (Double) value;
                return new Integer(aux.intValue()).toString();
            } else if (value instanceof Float) {
                Float aux = (Float) value;
                return new Integer(aux.intValue()).toString();
            } else {
                return new Integer(value.toString()).toString();
            }
        } catch (Exception e) {
            return null;
        }
    }
    
}
