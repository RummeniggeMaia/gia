/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.modelo;

import com.google.code.morphia.annotations.*;
import java.io.Serializable;
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
    private String role;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    @Transient
    public static final String ROLE_USER = "role_user";
    @Transient
    public static final String ROLE_ADMIN = "role_admin";

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id);
        sb.append(",");
        sb.append(cpf);
        sb.append(",");
        sb.append(email);
        sb.append(",");
        sb.append(login);
        sb.append(",");
        sb.append(matricula);
        sb.append(",");
        sb.append(nome);
        sb.append(",");
        sb.append(role);
        sb.append(",");
        sb.append(senha);
        return sb.toString();
    }
}
