/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package minitestes;

import br.ufrn.cerescaico.labordoc.gia.modelo.Usuario;
import br.ufrn.cerescaico.labordoc.gia.dao.UsuarioDao;
import br.ufrn.cerescaico.labordoc.gia.util.MongoClientUtil;
import com.mongodb.DB;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Rummenigge
 */
public class Miniteste {

    private static final int BUFFER_SIZE = 8192;

    public static void extrairTiposDeDocumentos(String arquivo) throws Exception {
        HashMap<String, List<String>> hash = new HashMap<String, List<String>>();

        char[] texto = lerTexto(arquivo);

        String s = new String(texto);
        texto = null;
        String[] linhas = s.split("\n");
        for (int i = 0; i < linhas.length; i++) {
            String line = linhas[i];
            String[] split = line.split("\t");
            if (split.length < 4) {
                continue;
            }
            String lower = split[3].toLowerCase();
            if (hash.containsKey(lower)) {
                List<String> lista = hash.get(lower);
                lista.add(line);
            } else {
                List<String> lista = new ArrayList<String>(2);
                lista.add(line);
                hash.put(lower, lista);
            }
        }
        Iterator<String> it = hash.keySet().iterator();
        while (it.hasNext()) {
            String next = it.next();
            List<String> lista = hash.get(next);
            next = next.replaceAll("[\\\\/\"'?]", "-");
            if (next.matches("\\s*")) {
                next = "" + next.hashCode();
            }
            PrintWriter pw = new PrintWriter(new File("D:/docs/" + next + ".txt"));
            for (String linha : lista) {
                pw.print(linha);
            }
            pw.close();
        }
    }

    public static void conectarPostgres() throws Exception {
        Connection c = DriverManager.getConnection("localhost:5432", "postgres", "5698741G");
    }

    public static void ajustarDatas(String arquivo) throws Exception {
        //String meses = "(janeiro|fevereiro|março|abril|maio|junho|julho|agosto|setembro|outubro|novembro|dezembro)";

        Pattern padrao = Pattern.compile("(\\d+\t\\d*)\t\t");
        StringBuffer sb = new StringBuffer();
        Matcher matcher = padrao.matcher(new String(lerTexto(arquivo)));
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1) + "\t* de * de *\t");
        }
        matcher.appendTail(sb);
        PrintWriter pw = new PrintWriter(new File("d:/saida2.txt"));
        pw.println(sb.toString());
        pw.close();
    }

    public static char[] lerTexto(String arquivo) throws Exception {
        File f = new File(arquivo);
        FileReader fr = new FileReader(f);

        char[] texto = new char[(int) f.length()];
        int bytesLidos = 0;
        int i = 0;
        while ((bytesLidos = fr.read(texto, i, BUFFER_SIZE)) != -1) {
            i += bytesLidos;
        }
        fr.close();
        return texto;
    }

    public static void main(String[] asd) throws Exception {
        /*long inicio = System.currentTimeMillis();

        extrairTiposDeDocumentos("D:\\Catalogação LABORDOC\\Catalogacao.txt");
        //conectarPostgres();
        long fim = System.currentTimeMillis();
        System.out.println("Tempo: " + (fim - inicio) / 1000.0 + " s");*/
        /*long i = System.currentTimeMillis();
        for (int in = 0; in < 1000; in++);
        long f = System.currentTimeMillis();
        System.out.println(i + " " + f);*/
//        DB db = MongoClientUtil.MONGO_CLIENT.getDB("giaDB");
//        System.out.println(db.collectionExists("usuarios"));
        
        Usuario u = new Usuario("A", "B", "C", "D", "E", "F");
        UsuarioDao dao = new UsuarioDao();
//        dao.criar(u);
        
    }
}
