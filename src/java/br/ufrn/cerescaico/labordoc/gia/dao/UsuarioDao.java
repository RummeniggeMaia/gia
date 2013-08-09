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

/**
 *
 * @author Rummenigge
 */
public class UsuarioDao {

    private DB dataBase;
    private DBCollection usuarios;

    public UsuarioDao() throws UnknownHostException {
        dataBase = MongoClientUtil.MONGO_CLIENT.getDB("giaDB");
        usuarios = dataBase.getCollection("usuarios");
    }

    public void criar(Usuario u) throws MongoException {
        BasicDBObject usuario = new BasicDBObject()
                .append("login", u.getLogin())
                .append("senha", u.getSenha())
                .append("nome", u.getNome())
                .append("email", u.getEmail())
                .append("cpf", u.getCpf())
                .append("matricula", u.getMatricula());
        usuarios.insert(usuario);
    }

    public void editar(Usuario u) throws MongoException {
        BasicDBObject query = new BasicDBObject()
                .append("_id", u.getId());
        BasicDBObject update = new BasicDBObject()
                .append("login", u.getLogin())
                .append("senha", u.getSenha())
                .append("nome", u.getNome())
                .append("email", u.getEmail())
                .append("cpf", u.getCpf())
                .append("matricula", u.getMatricula());
        usuarios.update(query, update);
    }

    public void excluir(Usuario u) {
        BasicDBObject query = new BasicDBObject()
                .append("_id", u.getId());
        usuarios.remove(query);
    }

    public List<Usuario> pesquisar() {
        return new ArrayList(2);
    }
}
