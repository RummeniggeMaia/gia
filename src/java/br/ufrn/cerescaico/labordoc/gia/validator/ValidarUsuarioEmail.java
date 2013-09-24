/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.validator;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Rummenigge
 */
public class ValidarUsuarioEmail implements Validator, Serializable {

    @Override
    public void validate(
            FacesContext context, 
            UIComponent component, 
            Object value) throws ValidatorException {
        
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "");
        String email = (String) value;
        if (!email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
            fm.setDetail("Email inválido.");
            throw new ValidatorException(fm);
        }
    }
}
