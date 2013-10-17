/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.converter;

import br.ufrn.cerescaico.labordoc.gia.modelo.Campo;
import br.ufrn.cerescaico.labordoc.gia.modelo.Tipo;
import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.bson.types.ObjectId;

/**
 *
 * @author Rummenigge
 */
public class TipoConverter implements Converter {

    private List<Tipo> tipos = new ArrayList<Tipo>();

    public TipoConverter() {
    }

    public TipoConverter(List<Tipo> tipos) {
        this.tipos = tipos;
    }

    @Override
    public Object getAsObject(
            FacesContext context,
            UIComponent component,
            String value) {
        
        try {
            Tipo tipo = new Tipo();
            String[] split = value.split("-");
            String[] props = split[0].split(",");


            tipo.setId(new ObjectId(props[0]));
            tipo.setNome(props[1]);

            if (split.length <= 1) {
                return tipo;
            }
            String[] camposProps = split[1].split(",");
            List<Campo> campos = new ArrayList<Campo>();
            for (int i = 0; i < camposProps.length; i += 3) {
                campos.add(new Campo(
                        camposProps[i],
                        camposProps[i + 1],
                        camposProps[i + 2]));
            }
            tipo.setCampos(campos);
            return tipo;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getAsString(
            FacesContext context,
            UIComponent component,
            Object value) {

        return value == null ? "" : value.toString();
    }

    public List<Tipo> getTipos() {
        return tipos;
    }

    public void setTipos(List<Tipo> tipos) {
        this.tipos = tipos;
    }
}
