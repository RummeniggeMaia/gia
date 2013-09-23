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

    private String clientId;

    public ValidarUsuarioNome() {
    }

    public ValidarUsuarioNome(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public void validate(
            FacesContext context,
            UIComponent component,
            Object value) throws ValidatorException {

        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "");
        if (value == null || !(value instanceof String)) {
            fm.setDetail("Nome inexistente.");
            throw new ValidatorException(fm);
        }
        String nome = (String) value;
        if (nome.isEmpty()) {
            fm.setDetail("Esse campo deve ser preenchido.");
            throw new ValidatorException(fm);
        } else if (nome.length() < 5) {
            fm.setDetail("Nome deve conter no mínimo 5 letras.");
            throw new ValidatorException(fm);
        }
    }
}
