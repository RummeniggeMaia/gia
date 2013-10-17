package br.ufrn.cerescaico.labordoc.gia.modelo;

import java.io.Serializable;

/**
 *
 * @author Rummenigge
 */
public class Imagem implements Serializable {

    private String nome;
    private String contentType;
    private String width;
    private String height;
    private byte[] conteudo;

    public Imagem() {
    }

    public Imagem(String width, String height) {
        this.width = width;
        this.height = height;
    }

    public Imagem(String nome, String contentType, String width, String height, 
            byte[] conteudo) {
        this.nome = nome;
        this.contentType = contentType;
        this.width = width;
        this.height = height;
        this.conteudo = conteudo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public byte[] getConteudo() {
        return conteudo;
    }

    public void setConteudo(byte[] conteudo) {
        this.conteudo = conteudo;
    }
}
