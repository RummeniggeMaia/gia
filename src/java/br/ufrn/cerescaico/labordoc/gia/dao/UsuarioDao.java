/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.dao;

import br.ufrn.cerescaico.labordoc.gia.modelo.Usuario;
import br.ufrn.cerescaico.labordoc.gia.util.MongoClientUtil;
import com.mongodb.*;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Rummenigge
 */
public class UsuarioDao {

    private DB dataBase;
    private DBCollection colecaoUsuarios;

    public UsuarioDao() throws UnknownHostException {
        dataBase = MongoClientUtil.MONGO_CLIENT.getDB("giaDB");
        colecaoUsuarios = dataBase.getCollection("usuarios");
    }

    public void criar(Usuario u) throws MongoException {
        BasicDBObject usuario = new BasicDBObject()
                .append("login", u.getLogin())
                .append("senha", u.getSenha())
                .append("nome", u.getNome())
                .append("email", u.getEmail())
                .append("cpf", u.getCpf())
                .append("matricula", u.getMatricula());
        colecaoUsuarios.insert(usuario);
    }

    public void editar(Usuario u) throws MongoException {
        BasicDBObject query = new BasicDBObject()
                .append("_id", u.getId());
        BasicDBObject update = new BasicDBObject()
                .append("login", u.getLogin())
                .append("nome", u.getNome())
                .append("email", u.getEmail())
                .append("cpf", u.getCpf())
                .append("matricula", u.getMatricula());
        colecaoUsuarios.update(query, update);
    }

    public void excluir(Usuario u) {
        BasicDBObject query = new BasicDBObject()
                .append("_id", u.getId());
        colecaoUsuarios.remove(query);
    }

    public List<Usuario> pesquisar(Usuario u) throws MongoException {
        BasicDBObject query = new BasicDBObject();
        List<Usuario> usuarios = new ArrayList<Usuario>(2);
        if (u != null) {
            if (u.getLogin() != null) {
                query.append("login", u.getLogin());
            }
            if (u.getNome() != null) {
                query.append("nome", u.getNome());
            }
            if (u.getEmail() != null) {
                query.append("email", u.getEmail());
            }
            if (u.getCpf() != null) {
                query.append("cpf", u.getCpf());
            }
            if (u.getMatricula() != null) {
                query.append("matricula", u.getMatricula());
            }
        }
        if (!query.isEmpty()) {
            DBCursor cursor = colecaoUsuarios.find(query);
            while (cursor.hasNext()) {
                DBObject next = cursor.next();
                Usuario atual = new Usuario();
                atual.setId((ObjectId) next.get("_id"));
                atual.setLogin((String) next.get("login"));
                atual.setNome((String) next.get("nome"));
                atual.setEmail((String) next.get("email"));
                atual.setCpf((String) next.get("cpf"));
                atual.setMatricula((String) next.get("matricula"));
                usuarios.add(atual);
            }
        }
        return usuarios;
    }
}
