/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.dao;

import br.ufrn.cerescaico.labordoc.gia.dao.criteria.*;
import br.ufrn.cerescaico.labordoc.gia.modelo.Documento;
import br.ufrn.cerescaico.labordoc.gia.util.Consts;
import com.mongodb.*;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.*;
import org.bson.types.ObjectId;

/**
 *
 * @author Rummenigge
 */
public class DocumentoDao extends MongoDao<Documento>
        implements Serializable {

    private CriteriaStrategyIF<Documento, DBObject> criteriaStrategyIF;
    private NullCriteria nullCriteria = new NullCriteria();
    private Map<Integer, CriteriaStrategyIF> criterias;

    public DocumentoDao() throws UnknownHostException {
        super();
        criteriaStrategyIF = nullCriteria;
        criterias = new HashMap<Integer, CriteriaStrategyIF>();
        criterias.put(
                Consts.CRITERIA_DOCUMENTO_ID, new CriteriaDocumentoId());
        criterias.put(
                Consts.CRITERIA_DOCUMENTO_TIPO, new CriteriaDocumentoTipo());
        criterias.put(
                Consts.CRITERIA_DOCUMENTO, new CriteriaDocumento());
    }

    @Override
    public Object criar(Documento e) {
        DBObject dBObject = new BasicDBObject(e.getCampos());
        WriteResult wr = 
                dataStore.getCollection(Documento.class).insert(dBObject);
        return wr;
    }

    @Override
    public List<Documento> pesquisar(
            Documento e,
            int offset,
            int limit,
            Integer criteria) {

        List<Documento> documentos = new ArrayList<Documento>();
        if (e == null) {
            return documentos;
        }
        DBObject dBObject = new BasicDBObject();
        criteriaStrategyIF = criterias.get(criteria);
        criteriaStrategyIF.operationCriteria(e, dBObject);

        DBCollection dBCollection = dataStore.getCollection(Documento.class);
        DBCursor dBCursor = dBCollection.find(dBObject);
        dBCursor.skip(offset);
        dBCursor.limit(limit);
        while (dBCursor.hasNext()) {
            Documento atual = new Documento();
            Map<String, Object> campos = dBCursor.next().toMap();
            atual.setId((ObjectId) campos.get(Consts._ID));
            campos.remove(Consts._ID);
            atual.setCampos(campos);
            documentos.add(atual);
        }
        return documentos;
    }
}

/*
 * private CriteriaStrategyIF<Documento, DBObject> criteriaStrategyIF;
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
 */
