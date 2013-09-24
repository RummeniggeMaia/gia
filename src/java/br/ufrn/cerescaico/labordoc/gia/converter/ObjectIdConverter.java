/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.bson.types.ObjectId;

/**
 *
 * @author Rummenigge
 */
@FacesConverter(value="objectIdConverter")
public class ObjectIdConverter implements Converter {

    @Override
    public Object getAsObject(
            FacesContext context, 
            UIComponent component, 
            String value) {
        
        if (ObjectId.isValid(value)) {
            return new ObjectId(value);
        }
        return null;
    }
    
    @Override
    public String getAsString(
            FacesContext context, 
            UIComponent component, 
            Object value) {
        
        return value == null ? null : value.toString();
    }
}
