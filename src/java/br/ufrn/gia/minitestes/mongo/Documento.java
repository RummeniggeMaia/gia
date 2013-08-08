/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.gia.minitestes.mongo;

import java.util.Date;

/**
 *
 * @author Rummenigge
 */
public class Documento {
    private int caixa;
    private Date data;
    private String tipo;
    private String titulares;
    private String obs;

    public Documento() {
    }

    public Documento(int caixa, Date data, String tipo, String titulares, String obs) {
        this.caixa = caixa;
        this.data = data;
        this.tipo = tipo;
        this.titulares = titulares;
        this.obs = obs;
    }

    public int getCaixa() {
        return caixa;
    }

    public void setCaixa(int caixa) {
        this.caixa = caixa;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulares() {
        return titulares;
    }

    public void setTitulares(String titulares) {
        this.titulares = titulares;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
