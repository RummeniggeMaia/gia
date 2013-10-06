package br.ufrn.cerescaico.labordoc.gia.modelo;

import java.io.InputStream;
import java.io.Serializable;

/**
 *
 * @author Rummenigge
 */
public class Imagem implements Serializable {

    private String nome;
    private InputStream inputStream;

    public Imagem() {
    }

    public Imagem(String nome, InputStream inputStream) {
        this.nome = nome;
        this.inputStream = inputStream;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}
