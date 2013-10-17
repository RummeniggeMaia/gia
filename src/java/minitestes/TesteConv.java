package minitestes;

import br.ufrn.cerescaico.labordoc.gia.modelo.Usuario;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Rummenigge
 */
public class TesteConv implements Converter {

    @Override
            public Object getAsObject(FacesContext context, UIComponent component, String value) {
                for (Usuario u : TesteMB.users) {
                    if (u.getNome().equals(value.split(",")[5])) {
                        return u;
                    }
                }
                return null;
            }

            @Override
            public String getAsString(FacesContext context, UIComponent component, Object value) {
                return value.toString();
            }

}
