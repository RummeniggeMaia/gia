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
public class ValidarUsuarioNome implements Validator, Serializable {

    public ValidarUsuarioNome() {
    }

    @Override
    public void validate(
            FacesContext context,
            UIComponent component,
            Object value) throws ValidatorException {

        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "");
        String nome = (String) value;
        if (nome.isEmpty()) {
            fm.setSummary("Campo vazio.");
            throw new ValidatorException(fm);
        } else if (nome.length() < 5) {
            fm.setSummary("O nome deve conter no mínimo 5 letras.");
            throw new ValidatorException(fm);
        } else if (!nome.matches("[a-zA-Z ]+")) {
            fm.setSummary("Caracteres inválidos no nome. Evite acentos, números, e caracteres especiais.");
            throw new ValidatorException(fm);
        }
    }
}
