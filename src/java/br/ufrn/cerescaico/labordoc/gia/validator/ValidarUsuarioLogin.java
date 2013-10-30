package br.ufrn.cerescaico.labordoc.gia.validator;

import br.ufrn.cerescaico.labordoc.gia.modelo.Usuario;
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
public class ValidarUsuarioLogin implements Validator, Serializable {

    public ValidarUsuarioLogin() {
    }
    
    @Override
    public void validate(
            FacesContext context,
            UIComponent component,
            Object value) throws ValidatorException {

        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "");
        String login = (String) value;
        if (login.isEmpty()) {
            fm.setSummary("Campo vazio.");
            throw new ValidatorException(fm);
        } else if (login.length() < 4) {
            fm.setSummary("O login deve conter no mínimo 4 caracteres.");
            throw new ValidatorException(fm);
        } else if (!login.matches("[a-zA-Z]+[a-zA-Z0-9_]*")) {
            fm.setSummary("Login inválido.");
            throw new ValidatorException(fm);
        }
     }
}
