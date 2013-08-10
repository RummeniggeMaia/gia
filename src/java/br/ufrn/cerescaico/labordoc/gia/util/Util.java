/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.util;

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
}
