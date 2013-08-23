/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.dao;

import br.ufrn.cerescaico.labordoc.gia.modelo.*;
import com.google.code.morphia.query.Query;
import java.net.UnknownHostException;

/**
 *
 * @author Rummenigge
 */
public class VisitaDao extends DaoGenerico<Visita> {

    public VisitaDao() throws UnknownHostException {
        super();
    }

    @Override
    protected void criarCriteria(Visita t, Query<Visita> q, int criteria) {
    }
}
