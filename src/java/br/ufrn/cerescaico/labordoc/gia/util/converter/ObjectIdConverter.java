/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.util.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.bson.types.ObjectId;

/**
 *
 * @author Rummenigge
 */
public class ObjectIdConverter implements Converter {

    @Override
    public Object getAsObject(
            FacesContext context, 
            UIComponent component, 
            String value) {
        
        return new ObjectId(value);
    }

    @Override
    public String getAsString(
            FacesContext context, 
            UIComponent component, 
            Object value) {
        
        return value.toString();
    }
}
