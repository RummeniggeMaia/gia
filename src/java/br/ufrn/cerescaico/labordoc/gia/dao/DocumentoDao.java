/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.dao;

import br.ufrn.cerescaico.labordoc.gia.modelo.Documento;
import br.ufrn.cerescaico.labordoc.gia.negocio.criteria.*;
import br.ufrn.cerescaico.labordoc.gia.util.Consts;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rummenigge
 */
public class DocumentoDao extends DaoGenerico<Documento>
        implements Serializable {

    private CriteriaStrategyIF<Documento, DBObject> criteriaStrategyIF;
    private CriteriaTipoDocumento criteriaTipoDocumento =
            new CriteriaTipoDocumento();
    private NullCriteria nullCriteria = new NullCriteria();

    public DocumentoDao() throws UnknownHostException {
        super();
        criteriaStrategyIF = nullCriteria;
    }

    @Override
    public void criar(Documento t) {
        DBCollection dBCollection =
                dataStore.getCollection(Documento.class);

        BasicDBObject basicDBObject = new BasicDBObject(t.getCampos());
        dBCollection.insert(basicDBObject);
    }

    @Override
    public List<Documento> pesquisar(
            Documento t,
            int offset,
            int limit,
            int criteria) {

        List<Documento> documentos = new ArrayList<Documento>(2);
        DBCollection dBCollection = dataStore.getCollection(Documento.class);
        BasicDBObject basicDBObject = new BasicDBObject(t.getCampos());
        DBCursor dBCursor = dBCollection.find(basicDBObject);
        criarCriteria(t, dBCollection, criteria);
        dBCursor.limit(limit);
        dBCursor.skip(offset);
        while (dBCursor.hasNext()) {
            DBObject atual = dBCursor.next();
            Documento d = new Documento();
            d.setCampos(atual.toMap());
            documentos.add(d);
        }
        return documentos;
    }

    @Override
    protected void criarCriteria(
            Documento t,
            Object q,
            int criteria) {

        if (criteria == Consts.CRITERIA_TIPO_DOCUMENTO) {
            criteriaStrategyIF = criteriaTipoDocumento;
        }
        criteriaStrategyIF.operationCriteria(t, q);
    }
}
