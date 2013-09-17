package br.ufrn.cerescaico.labordoc.gia.util;

import java.util.Map;
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
        HttpServletRequest httpRequest = (HttpServletRequest) fc()
                .getExternalContext()
                .getRequest();
        return httpRequest.getSession();
    }

    /**
     * Aessa o mapa de sessões
     * @return map de sessões do JSF
     */
    public static Map<String, Object> getSessionMap() {
        return fc().getExternalContext().getSessionMap();
    }
    /**
     * Adiciona uma mensagem no faces context
     * @param clientId pode ser null
     * @param message Mensagem
     */
    public static void addMsg(String clientId, String message, 
            FacesMessage.Severity s) {
        FacesContext
                .getCurrentInstance()
                .addMessage(clientId, new FacesMessage(s, message, message));
    }

    /**
     * Acessa o FacesContex.
     *
     * @return Instância atual do FacesContext
     */
    public static FacesContext fc() {
        return FacesContext.getCurrentInstance();
    }

    public static String capitalizeString(String string) {
        if (string.length() <= 1 || string == null) {
            return string;
        }
        char[] chars = string.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return String.valueOf(chars);
//        boolean found = false;
//        for (int i = 0; i < chars.length; i++) {
//            if (!found && Character.isLetter(chars[i])) {
//                chars[i] = Character.toUpperCase(chars[i]);
//                found = true;
//            } else if (Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == '\'') {
//                found = false;
//            }
//        }
    }
}
