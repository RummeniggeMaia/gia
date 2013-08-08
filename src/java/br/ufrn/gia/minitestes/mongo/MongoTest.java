/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.gia.minitestes.mongo;

import com.mongodb.*;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author Rummenigge
 */
public class MongoTest {

    public static void main(String[] asd) throws UnknownHostException, ParseException {
        long inicio = System.currentTimeMillis();
        MongoClient mc = new MongoClient("localhost");
        try {
//            DB db = mc.getDB("mydb");
            DB giaDB = mc.getDB("giaDB");
            criarDosc(giaDB);
////            Buscando colecoes
//            Set<String> colecoes = db.getCollectionNames();
//            for (String s : colecoes) {
//                pf("%s\n", s);
//            }

////            Criando um novo documento
//            DBCollection colecao = db.getCollection("things");
//            BasicDBObject basicDBObject = new BasicDBObject("name", "MongoDB")
//                    .append("type", "database")
//                    .append("count", 1)
//                    .append("info", new BasicDBObject("x", 203)
//                    .append("y", 102));
//           WriteResult wr = colecao.insert(basicDBObject);
//           pf("Documento inserido com sucesso:\n%s\n", wr.toString());
//
////            Pesquisando um unico documento
//            DBCollection dbC = db.getCollection("things");
//            DBObject dbO = dbC.findOne(new BasicDBObject("name", "MongoDB"));
//            exibirDoc(dbO, "");
//
//            DBCursor cursor = dbC.find();
//            while (cursor.hasNext()) {
//                System.out.println(cursor.next());
//            }
////            Inserindo varios documentos
//            DBCollection dbC = db.getCollection("things");
//            for (int i = 0; i < 100; i++) {
//                dbC.insert(new BasicDBObject("i:", i));
//            }
//            pf("100 valores cadastrados.");


        } finally {
            mc.close();
        }
        long fim = System.currentTimeMillis();
        pf("%.2f segundos.\n", (fim - inicio) / 1000.0);
    }

    private static void pf(String format, Object... args) {
        System.out.printf(format, args);
    }

    private static void exibirDoc(DBObject dbO, String tbs) {
        Set<String> set = dbO.keySet();
        for (String s : set) {
            Object o = dbO.get(s);
            pf("%s%s: %s\n", tbs, s, o.getClass().getSimpleName());
            if (o instanceof DBObject) {
                exibirDoc((DBObject) o, tbs += "\t");
            }
        }
    }
    
    private static void criarDosc(DB db) throws ParseException {
        DBCollection dbC = db.getCollection("documentos");
        Date data = new SimpleDateFormat("dd/MM/yyyy").parse("27/05/1989");
        for (int i = 0; i < 1000; i++) {
            BasicDBObject bdo = new BasicDBObject("caixa", i + 1)
                    .append("data" , data)
                    .append("tipo", "Habilitacao de casamento")
                    .append("titulares", "Barttolomeo Cemio de Medina/Maria Benyna de Medina")
                    .append("obs", "observacao");
            dbC.insert(bdo);
        }
    }
}
