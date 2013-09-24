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
public class ValidarUsuarioCPF implements Validator, Serializable {

    @Override
    public void validate(
            FacesContext context, 
            UIComponent component, 
            Object value) throws ValidatorException {
        
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "");
        String cpf = (String) value;
        if (!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")) {
            fm.setDetail("CPF inválido. Preencha o campo completamente");
            throw new ValidatorException(fm);
        }
    }
}
