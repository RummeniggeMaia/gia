/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.modelo;

import com.google.code.morphia.annotations.*;
import java.io.Serializable;
import java.util.Date;
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
    private Date dataNascimento;
    private String sexo;

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

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    @Transient
    public static final String ROLE_USER = "role_user";
    @Transient
    public static final String ROLE_ADMIN = "role_admin";

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append(id)
                .append(",")
                .append(cpf)
                .append(",")
                .append(email)
                .append(",")
                .append(login)
                .append(",")
                .append(matricula)
                .append(",")
                .append(nome)
                .append(",")
                .append(role)
                .append(",")
                .append(senha)
                .append(",")
                .append(dataNascimento)
                .append(",")
                .append(sexo);
        return sb.toString();
    }
}
