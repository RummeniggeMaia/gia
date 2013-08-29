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
@Entity(value = "usuarios", noClassnameStored = true)
public class Usuario extends Entidade implements Serializable {

    @Indexed(unique = true)
    private String login;
    private String senha;
    private String nome;
    private String email;
    @Indexed(unique = true)
    private String cpf;
    private String matricula;
    @Transient
    private List<Integer> funcoes = new ArrayList<Integer>(2);
    private String role = ROLE_USER;

    public Usuario() {
    }

    public Usuario(ObjectId id) {
        super(id);
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", login=" + login + ", senha="
                + senha + ", nome=" + nome + ", email=" + email + ", cpf="
                + cpf + ", matricula=" + matricula + ", funcoes=" + funcoes
                + '}';
    }
    @Transient
    public static final String ROLE_USER = "role_user";
    @Transient
    public static final String ROLE_ADMIN = "role_admin";
}
