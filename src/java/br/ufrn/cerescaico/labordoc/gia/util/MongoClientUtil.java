/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.util;

import com.mongodb.MongoClient;
import java.net.UnknownHostException;

/**
 *
 * @author Rummenigge
 */
public class MongoClientUtil {

    static {
        try {
            MONGO_CLIENT = new MongoClient("localhost");
        } catch (UnknownHostException ex) {
            ex.printStackTrace(System.err);
        }
    }
    public static MongoClient MONGO_CLIENT;
}
