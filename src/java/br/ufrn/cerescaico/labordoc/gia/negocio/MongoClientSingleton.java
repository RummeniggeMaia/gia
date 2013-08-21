/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.labordoc.gia.negocio;

import com.mongodb.*;
import java.net.UnknownHostException;

/**
 * Classe usada para ter acesso a apenas um recurso de banco de dados que
 * gerencia todas as conexões do sistema. Essa classe segue o padrão de projeto
 * Silgleton que a criação de apenas uma instância de um MongoClient.
 *
 * @author Rummenigge
 */
public class MongoClientSingleton {

    private static MongoClient MONGO_CLIENT;

    private MongoClientSingleton() {
    }

    /**
     * Acessa a instância do MongoClient criada no endereço de servidor
     * <b>localhost</b>, na porta padrão.
     *
     * @return Uma instãncia da classe MongoClient.
     * @throws UnknownHostException
     */
    public static MongoClient getInstance()
            throws UnknownHostException {
        //Aplicação do padrão Double Checked Locked
        if (MONGO_CLIENT == null) {
            synchronized (MongoClientSingleton.class) {
                if (MONGO_CLIENT == null) {
                    MONGO_CLIENT = new MongoClient("localhost");
                    return MONGO_CLIENT;
                }
            }
        }
        return MONGO_CLIENT;
    }

    /**
     * Acessa a instância do MongoClient criada no endereço de servidor.
     *
     * @param serverAddress
     * @param mongoClientOptions
     * @return Uma instãncia da classe MongoClient.
     * @throws UnknownHostException
     */
    public static MongoClient getInstance(
            ServerAddress serverAddress,
            MongoClientOptions mongoClientOptions)
            throws UnknownHostException {
        //Aplicação do padrão Double Checked Locked
        if (MONGO_CLIENT == null) {
            synchronized (MongoClientSingleton.class) {
                if (MONGO_CLIENT == null) {
                    MONGO_CLIENT = new MongoClient(
                            serverAddress, 
                            mongoClientOptions);
                    return MONGO_CLIENT;
                }
            }
        }
        return MONGO_CLIENT;
    }
}
