/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package minitestes.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Rummenigge
 */
public class MongoTest2 {

    public static void main(String[] asd) throws UnknownHostException, ParseException {
        MongoClient mc = new MongoClient("localhost");
        
        DB giaDB = mc.getDB("giaDB");
        
        DBCollection dbCollection = giaDB.getCollection("documentos");
        
        Date data = new SimpleDateFormat("dd/MM/yyyy").parse("27/05/1989");
//        BasicDBObject bdbo = new BasicDBObject("caixa", 430)
//                    .append("data" , data)
//                    .append("tipo", "Executiva fiscal")
//                    .append("titulares", "Jose Inacio Filho")
//                    .append("obs", "observacao");
//        
//        dbCollection.insert(bdbo);
//        
        BasicDBObject bdbo = new BasicDBObject("DataNasc.sexo", 'M');
        
        dbCollection.update(bdbo, new BasicDBObject("birth", 1));
        
        DBCursor dbCursor = dbCollection.find(bdbo);
        
        while (dbCursor.hasNext()) {
            System.out.println(dbCursor.next().toString());
        }
        
        mc.close();
    }
}
