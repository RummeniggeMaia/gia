package br.ufrn.cerescaico.labordoc.gia.negocio;

import java.io.Serializable;

/**
 *
 * @author Rummenigge
 */
public class Paginacao implements Serializable {

    private int cont;
    private int offset;
    private int limit = 1;
    private String pagina;
    private Object entidade;

    public Paginacao() {
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }

    public Object getEntidade() {
        return entidade;
    }

    public void setEntidade(Object entidade) {
        this.entidade = entidade;
    }

    public String getPagina() {
        pagina = "" + paginaAtual() + " / " + totalDePaginas();
        return pagina;
    }

    public void setPagina(String value) {
        try {
            int pag = 0;
            if (value.matches("\\d+")) {
                pag =  Integer.parseInt(value);
            } else if (value.matches("\\d+\\s*/\\s*\\d+")) {
                value = value.replaceAll("\\s*/\\s*\\d+", "");
                pag = Integer.parseInt(value);
            } else {
                return;
            }
            pular(pag);
        } catch (Exception e) {
        }
    }

    public void primeira() {
        offset = 0;
    }

    public void anterior() {
        offset -= limit;
        if (offset < 0) {
            offset = 0;
        }
    }

    public void proxima() {
        if (offset + limit < cont) {
            offset += limit;
        }
    }

    public void ultima() {
        offset = (totalDePaginas() - 1) * limit;
    }

    public void pular(int pagina) {
        if (pagina > 0 && pagina <= totalDePaginas()) {
            offset = (pagina - 1) * limit;
        }
    }

    public int paginaAtual() {
        if (cont > 0) {
            return offset / limit + 1;
        }
        return 0;
    }

    public int totalDePaginas() {
        int quociente = cont / limit;
        return cont % limit != 0 ? quociente + 1 : quociente;
    }

    public boolean podeVoltar() {
        return paginaAtual() > 1;
    }

    public boolean podeSeguir() {
        return paginaAtual() < totalDePaginas();
    }

    public int totalLidos() {
        if (cont > 0) {
            if (offset + limit > cont) {
                return offset + (cont - offset);
            } else {
                return offset + limit;
            }
        }
        return 0;
    }
} 
