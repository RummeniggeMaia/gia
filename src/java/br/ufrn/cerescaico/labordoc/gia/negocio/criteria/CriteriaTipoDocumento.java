/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.negocio.criteria;

import br.ufrn.cerescaico.labordoc.gia.modelo.Documento;
import com.mongodb.DBObject;

/**
 *
 * @author Rummenigge
 */
public class CriteriaTipoDocumento
        implements CriteriaStrategyIF<Documento, DBObject> {

    @Override
    public void operationCriteria(Documento t, DBObject q) {
    }
}
