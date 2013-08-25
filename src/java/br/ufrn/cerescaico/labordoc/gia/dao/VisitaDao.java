/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.dao;

import br.ufrn.cerescaico.labordoc.gia.modelo.*;
import com.google.code.morphia.query.Query;
import java.io.Serializable;
import java.net.UnknownHostException;

/**
 *
 * @author Rummenigge
 */
public class VisitaDao extends MongoDao<Visita> implements Serializable {

    public VisitaDao() throws UnknownHostException {
        super();
    }

    @Override
    protected void criarCriteria(Visita t, Object q, int criteria) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
