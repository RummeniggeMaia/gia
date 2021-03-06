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
                Consts.CRITERIA_DOCUMENTO, new CriteriaDocumento(dataStore));
        criterias.put(
                Consts.CRITERIA_DOCUMENTO_CONJUNTIVA, 
                new CriteriaDocumentoConj(dataStore));
    }

    @Override
    public Object criar(Documento e) throws Exception {
        e.setId(null);
        DBRef bRef = new DBRef(
                dataStore.getDB(), 
                Consts.COLECAO_TIPOS, 
                e.getTipo().getId());
        BasicDBObject dBObject = new BasicDBObject(e.getCampos());
        dBObject.append(Consts.CAMPO_TIPO, bRef);
        WriteResult wr =
                dataStore.getCollection(Documento.class).insert(dBObject);
        return wr;
    }

    @Override
    public Object editar(Documento e) throws Exception {
        DBRef bRef = new DBRef(
                dataStore.getDB(), 
                Consts.COLECAO_TIPOS, 
                e.getTipo().getId());
        BasicDBObject update = new BasicDBObject(e.getCampos());
        update.append(Consts.CAMPO_TIPO, bRef);
        BasicDBObject query = new BasicDBObject(Consts._ID, e.getId());
        return dataStore.getCollection(Documento.class).update(query, update);
    }

    @Override
    public List<Documento> pesquisar(
            Documento e,
            int offset,
            int limit,
            Integer criteria) throws Exception {

        List<Documento> documentos = new ArrayList<Documento>();
        if (e == null) {
            return documentos;
        }
        DBObject dBObject = new BasicDBObject();
        CriteriaStrategyIF aux = criterias.get(criteria);
        criteriaStrategyIF = (aux == null) ? nullCriteria : aux;
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
            DBRef ref = (DBRef) campos.get(Consts.CAMPO_TIPO);
            atual.getTipo().setId((ObjectId) ref.getId());
            campos.remove(Consts.CAMPO_TIPO);
            atual.setCampos(campos);
            documentos.add(atual);
        }
        return documentos;
    }
    
    public int contar(
            Documento e,
            Integer criteria) {
        
        DBObject dBObject = new BasicDBObject();
        CriteriaStrategyIF aux = criterias.get(criteria);
        criteriaStrategyIF = (aux == null) ? nullCriteria : aux;
        criteriaStrategyIF.operationCriteria(e, dBObject);
        DBCollection dBCollection = dataStore.getCollection(Documento.class);
        DBCursor dBCursor = dBCollection.find(dBObject);
        return dBCursor.count();
    }
    
//    public void editarImagens(Documento d) {
//        BasicDBObject update = new BasicDBObject(
//                "$set", new BasicDBObject("imagens", d.getImagens()));
//        BasicDBObject query = new BasicDBObject(Consts._ID, d.getId());
//        dataStore.getCollection(Documento.class).update(query, update);
//    }
}
