/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.dao;

import br.ufrn.cerescaico.labordoc.gia.modelo.Documento;
import br.ufrn.cerescaico.labordoc.gia.modelo.Usuario;
import br.ufrn.cerescaico.labordoc.gia.modelo.Visita;
import br.ufrn.cerescaico.labordoc.gia.util.MongoClientUtil;
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
        dataBase = MongoClientUtil.MONGO_CLIENT.getDB("giaDB");
        colecaoVisitas = dataBase.getCollection("visitas");
    }

    public void criar(Visita v) throws MongoException {
        BasicDBObject visita = new BasicDBObject();
        visita.append("documento", v.getDocumento().getId());
        visita.append("usuario", v.getUsuario().getId());
        visita.append("total_de_visitas", v.getTotalDeVisitas());
        colecaoVisitas.insert(visita);
    }

    public void editar(Visita v) throws MongoException {
        BasicDBObject query = new BasicDBObject("_id", v.getId());
        BasicDBObject update = new BasicDBObject(
                "$inc", 
                new BasicDBObject("totalDeVisitas", 1));  
        colecaoVisitas.update(query, update);
        /*BasicDBObject update = new BasicDBObject()
                .append("usuario", v.getUsuario().getId())
                .append("documento", v.getDocumento().getId())
                .append("totalDeVisitas", v.getTotalDeVisitas());
        colecaoVisitas.update(query, update);*/
    }

    public void excluir(Visita v) throws MongoException {
        colecaoVisitas.remove(new BasicDBObject("_id", v.getId()));
    }

    public List<Visita> pesquisar(Visita v) {
        BasicDBObject query = new BasicDBObject();
        List<Visita> visitas = new ArrayList<Visita>(2);
        if (v != null) {
            if (v.getUsuario() != null) {
                query.append("usuario", v.getUsuario().getId());
            }
            if (v.getDocumento() != null) {
                query.append("documento", v.getDocumento().getId());
            }
        }
        if (!query.isEmpty()) {
            DBCursor cursor = colecaoVisitas.find(query);
            while (cursor.hasNext()) {
                DBObject next = cursor.next();
                Visita atual = new Visita();
                atual.setId((ObjectId) next.get("_id"));
                
                Documento d = new Documento();
                d.setId((ObjectId) next.get("documento"));
                atual.setDocumento(d);
                
                Usuario u = new Usuario();
                u.setId((ObjectId) next.get("usuario"));
                atual.setUsuario(u);
                
                visitas.add(atual);
            }
        }
        return visitas;
    }
}
