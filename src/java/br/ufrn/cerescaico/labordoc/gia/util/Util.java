/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rummenigge
 */
public class Util {

    /**
     * Mesmo que System.out.printf(), usado apenas para não ter que digitar
     * System.out.println().
     *
     * @param format Formato.
     * @param args Argumentos.
     */
    public static void pf(String format, Object... args) {
        System.out.printf(format, args);
    }

    public static HttpSession getFacesSession() {
        HttpServletRequest httpRequest = (HttpServletRequest) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequest();
        return httpRequest.getSession();
    }
    
    public static void addMsg(String clientId, String message) {
        FacesContext
                .getCurrentInstance()
                .addMessage(clientId, new FacesMessage(message));
    }
    
    /**
     * Acessa o FacesContex.
     * @return Instância atual do FacesContext
     */
    public static FacesContext fc() {
        return FacesContext.getCurrentInstance();
    }
}
