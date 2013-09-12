/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.dao;

import br.ufrn.cerescaico.labordoc.gia.modelo.*;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.List;

/**
 *
 * @author Rummenigge
 */
public class VisitaDao extends MongoDao<Visita> implements Serializable {

    public VisitaDao() throws UnknownHostException {
        super();
    }

    @Override
    public List<Visita> pesquisar(
            Visita e, 
            int offset, 
            int limit, 
            Integer criteria) throws Exception {
        
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
