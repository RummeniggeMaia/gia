/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.dao;

import br.ufrn.cerescaico.labordoc.gia.modelo.*;
import br.ufrn.cerescaico.labordoc.gia.util.*;
import com.mongodb.*;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Rummenigge
 */
public class VisitaDao {

    private DB dataBase;
    private DBCollection colecaoVisitas;

    public VisitaDao() {
        dataBase = MongoClientUtil.MONGO_CLIENT.getDB(Consts.BANCO);
        colecaoVisitas = dataBase.getCollection(Consts.COLECAO_VISITAS);
    }

    public void criar(Visita v) throws MongoException {
        BasicDBObject visita = new BasicDBObject();
        visita.append(Consts.DOCUMENTO, v.getDocumento().getId());
        visita.append(Consts.USUARIO, v.getUsuario().getId());
        visita.append(Consts.TOTAL_DE_VISITAS, v.getTotalDeVisitas());
        colecaoVisitas.insert(visita);
    }

    public void editar(Visita v) throws MongoException {
        BasicDBObject query = new BasicDBObject(Consts._ID, v.getId());
        //A atualização do o objeto visita apenas incrementa o total de visitas
        //no determinado documento
        BasicDBObject update = new BasicDBObject(
                "$inc", 
                new BasicDBObject(Consts.TOTAL_DE_VISITAS, 1));
        colecaoVisitas.update(query, update);
    }

    public void excluir(Visita v) throws MongoException {
        colecaoVisitas.remove(new BasicDBObject(Consts._ID, v.getId()));
    }

    public List<Visita> pesquisar(Visita v) {
        BasicDBObject query = new BasicDBObject();
        List<Visita> visitas = new ArrayList<Visita>(2);
        if (v != null) {
            if (v.getUsuario() != null) {
                query.append(Consts.USUARIO, v.getUsuario().getId());
            }
            if (v.getDocumento() != null) {
                query.append(Consts.DOCUMENTO, v.getDocumento().getId());
            }
        }
        if (!query.isEmpty()) {
            DBCursor cursor = colecaoVisitas.find(query);
            while (cursor.hasNext()) {
                DBObject next = cursor.next();
                Visita atual = new Visita();
                atual.setId((ObjectId) next.get(Consts._ID));
                
                Documento d = new Documento();
                d.setId((ObjectId) next.get(Consts.DOCUMENTO));
                atual.setDocumento(d);
                
                Usuario u = new Usuario();
                u.setId((ObjectId) next.get(Consts.USUARIO));
                atual.setUsuario(u);
                
                visitas.add(atual);
            }
        }
        return visitas;
    }
}
