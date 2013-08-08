/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.gia.dao;

import br.com.ufrn.gia.modelo.Usuario;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rummenigge
 */
public class UsuarioDao {

    private MongoClient mongoClient;
    private DB dataBase;

    public UsuarioDao() throws UnknownHostException {
        mongoClient = new MongoClient("localhost");
        dataBase = mongoClient.getDB("giaDB");
    }

    public void criar(Usuario u) throws MongoException {
        DBCollection usuarios = dataBase.getCollection("usuarios");
        BasicDBObject usuario = new BasicDBObject()
                .append("login", u.getLogin())
                .append("senha", u.getSenha())
                .append("nome", u.getNome())
                .append("email", u.getEmail())
                .append("cpf", u.getCpf())
                .append("matricula", u.getMatricula());
        usuarios.insert(usuario);
    }

    public void editar(Usuario u) {
    }

    public void excluir(Usuario u) {
    }

    public List<Usuario> pesquisar() {
        return new ArrayList(2);
    }
}
