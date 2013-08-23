/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.modelo;

import com.google.code.morphia.annotations.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Rummenigge
 */
@Entity(value="usuarios", noClassnameStored=true)
public class Usuario implements Serializable {

    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private String login;
    private String senha;
    private String nome;
    @Indexed(unique = true)
    private String email;
    @Indexed(unique = true)
    private String cpf;
    private String matricula;
    @Embedded
    private List<Integer> funcoes = new ArrayList<Integer>(2);

    public Usuario() {
    }

    public Usuario(ObjectId id) {
        this.id = id;
    }

    public Usuario(String login, String senha, String nome, String email,
            String cpf, String matricula) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.matricula = matricula;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public List<Integer> getFuncoes() {
        return funcoes;
    }

    public void setFuncoes(List<Integer> funcoes) {
        this.funcoes = funcoes;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Usuario)) {
            return false;
        }
        Usuario outro = (Usuario) obj;
        return id.compareTo(outro.getId()) == 0;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", login=" + login + ", senha=" 
                + senha + ", nome=" + nome + ", email=" + email + ", cpf=" 
                + cpf + ", matricula=" + matricula + ", funcoes=" + funcoes 
                + '}';
    }

    
}
