package br.ufrn.cerescaico.labordoc.gia.validator;

import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Rummenigge
 */
public class ValidarData implements Validator, Serializable {

    @Override
    public void validate(
            FacesContext context,
            UIComponent component,
            Object value) throws ValidatorException {

//        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "");
//        String data = (String) value;
//        try {
//            new SimpleDateFormat().parse(data);
//        } catch (Exception e) {
//            fm.setDetail("Data inválida. Siga o padrão: dd/mm/aaaa");
//            throw new ValidatorException(fm);
//        }
    }
}
