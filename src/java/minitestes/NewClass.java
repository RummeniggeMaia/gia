/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package minitestes;

import java.io.Serializable;

/**
 *
 * @author Rummenigge
 */
public class NewClass implements Serializable {

    private String nome;

    public NewClass() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
