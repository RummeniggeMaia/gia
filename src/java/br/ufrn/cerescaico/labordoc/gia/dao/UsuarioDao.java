/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.dao;

import br.ufrn.cerescaico.labordoc.gia.modelo.Usuario;
import br.ufrn.cerescaico.labordoc.gia.negocio.MongoClientSingleton;
import br.ufrn.cerescaico.labordoc.gia.util.Consts;
import com.google.code.morphia.*;
import com.google.code.morphia.query.Query;
import com.mongodb.*;
import java.net.UnknownHostException;
import java.util.*;

/**
 *
 * @author Rummenigge
 */
public class UsuarioDao<Usuario> extends DaoGenerico {

    private DB dataBase;
    private DBCollection colecaoUsuarios;
    private Datastore dataStore;

    public UsuarioDao() throws UnknownHostException {
        super();
    }

    public Key<Usuario> criar(Usuario usuario) throws MongoException {
//        BasicDBObject usuario = new BasicDBObject()
//                .append("login", u.getLogin())
//                .append("senha", u.getSenha())
//                .append("nome", u.getNome())
//                .append("email", u.getEmail())
//                .append("cpf", u.getCpf())
//                .append("matricula", u.getMatricula());
//        colecaoUsuarios.insert(usuario);
        return dataStore.save(usuario);
    }

    public void editar(Usuario usuario) throws MongoException {
//        BasicDBObject query = new BasicDBObject()
//                .append("_id", u.getId());
//        BasicDBObject update = new BasicDBObject()
//                .append("login", u.getLogin())
//                .append("nome", u.getNome())
//                .append("email", u.getEmail())
//                .append("cpf", u.getCpf())
//                .append("matricula", u.getMatricula());
//        colecaoUsuarios.update(query, update);
        dataStore.merge(usuario);
    }

    public WriteResult excluir(Usuario usuario) throws MongoException {
//        colecaoUsuarios.remove(new BasicDBObject("_id", u.getId()));
        return dataStore.delete(usuario);
    }

    public List<Usuario> pesquisar(Usuario u) throws MongoException {
//        BasicDBObject query = new BasicDBObject();
//        List<Usuario> usuarios = new ArrayList<Usuario>(2);
        Query<Usuario> q = dataStore.find(Usuario.class);
        return q.asList();
//        if (u != null) {
//            if (u.getLogin() != null) {
//                query.append("login", u.getLogin());
//            }
//            if (u.getSenha() != null) {
//                query.append("senha", u.getSenha());
//            }
//            if (u.getNome() != null) {
//                query.append("nome", u.getNome());
//            }
//            if (u.getEmail() != null) {
//                query.append("email", u.getEmail());
//            }
//            if (u.getCpf() != null) {
//                query.append("cpf", u.getCpf());
//            }
//            if (u.getMatricula() != null) {
//                query.append("matricula", u.getMatricula());
//            }
//        }
//        if (!query.isEmpty()) {
//            DBCursor cursor = colecaoUsuarios.find(query);
//            while (cursor.hasNext()) {
//                DBObject next = cursor.next();
//                Usuario atual = new Usuario();
//                atual.setId((ObjectId) next.get("_id"));
//                atual.setLogin((String) next.get("login"));
//                atual.setNome((String) next.get("nome"));
//                atual.setEmail((String) next.get("email"));
//                atual.setCpf((String) next.get("cpf"));
//                atual.setMatricula((String) next.get("matricula"));
//                usuarios.add(atual);
//            }
//        }
//        return usuarios;
    }

    public boolean autenticar(Usuario u) {
        BasicDBObject bo = new BasicDBObject(Consts.LOGIN, u.getLogin());
        DBCursor dBCurdor = colecaoUsuarios.find(bo);
        if (dBCurdor.hasNext()) {
            bo = (BasicDBObject) dBCurdor.next();
            if (bo.get(Consts.SENHA).equals(u.getSenha())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List pesquisar(Object t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
