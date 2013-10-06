/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package minitestes;

import br.ufrn.cerescaico.labordoc.gia.dao.DocumentoDao;
import br.ufrn.cerescaico.labordoc.gia.dao.ImagemDao;
import br.ufrn.cerescaico.labordoc.gia.modelo.Imagem;
import br.ufrn.cerescaico.labordoc.gia.negocio.AdministradorMB;
import br.ufrn.cerescaico.labordoc.gia.util.Util;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.mongodb.gridfs.GridFSInputFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 *
 * @author Rummenigge
 */
public class Teste {

    public static void main(String[] asd) throws Exception {
//        UsuarioMB umb = new UsuarioMB(null);
//        umb.setPesquisaCtrl(new PesquisaCtrl());
//        umb.setDocumento(new Documento());
//        umb.setDocumentos(new ArrayList<Documento>());
//        umb.setTipoDao(new TipoDao());
//        Tipo t = umb.getTipoDao().pesquisarTodos(Tipo.class).get(0);
//        umb.setTipo(t);
//        t.getCampos().get(0).setValor(1);
//        t.getCampos().get(1).setValor(new Date());
//        t.getCampos().get(2).setValor("titus");
//        umb.pesquisarDocumentos();
//        Util.pf("%s\n", umb.getDocumento());
//        Util.pf(Util.capitalizeString("sfsdKLJdjkf jdlk s djlf iLIJfdl"));
//        Tipo t = new Tipo();
//        t.setId(new ObjectId());
//        t.setTipo("tipo_teste");
//        t.setId(new ObjectId());
//        List<Campo> campos = new ArrayList<Campo>();
//        campos.add(new Campo("C1", 1, "int"));
//        campos.add(new Campo("C2", "String", "str"));
//        campos.add(new Campo(
//                "C3", 
//                new SimpleDateFormat("dd/MM/yyyy").format(new Date()), 
//                "data"));
//        t.setCampos(campos);
//        Util.pf(t.toString());
////        Campo c = new Campo();
////        c.setNome("N");
////        c.setValor(1);
////        Util.pf(c.toString());
//        Documento d = new Documento();
//        d.setId(new ObjectId());
//        d.setTipo(t);
//        d.getCampos().put("nome", "nome1");
//        d.getCampos().put("caixa", 1);
//        d.getCampos().put("asdja", new Date());
////        Util.pf(d.toString());
//        TipoDao tipoDao = new TipoDao();
//        Tipo t = tipoDao.pesquisarTodos(Tipo.class).get(0);
//        TipoConverter tc = new TipoConverter();
//        Tipo tp = (Tipo) tc.getAsObject(null, null, t.toString());
//        Util.pf("%s\n", tp.getNome());
//        Usuario x = new Usuario();
//        Usuario y = new Usuario();
//        y.setNome("asd");
//        x = y;
//        y.setNome("qwe");
//        y = null;
//        Util.pf("%s\n", x.getNome());
//        Util.pf("%d\n", 3 % 5);
//        String x = "123.456.789-01";
//        Util.pf("%s\n", x.matches("\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}"));
//        MongoClient mc = MongoClientSingleton.getInstance();
//        DB db = mc.getDB("test");
//        DBCollection dbc = db.getCollection("pessoa");
//        
//        BasicDBObject bdbo = new BasicDBObject();
//        bdbo.append("endereco.logradouro", "rua X");
//        DBCursor c = dbc.find(bdbo);
//        while (c.hasNext()) {
//            Util.pf(c.next().toString());
//        }
//        DocumentoDao dao = new DocumentoDao();
//        GridFS gfs = dao.getGridFS();
//        GridFSDBFile fSFile = gfs.findOne("violao");
//        Util.pf("%s\n", fSFile.getUploadDate());
//        GridFSDBFile bFile = gfs.findOne("violao.jpg");
//        System.out.println(bFile);
//        ImagemDao dao = new ImagemDao();
//        File f1 = new File("d:/output/DSC05749.JPG");
//        File f2 = new File("d:/output/DSC05751.JPG");
//        File f3 = new File("d:/output/DSC05752.JPG");
//        File f4 = new File("d:/output/DSC05753.JPG");
//        File f5 = new File("d:/output/DSC05754.JPG");
//        FileInputStream fis = new FileInputStream(f1);
//        Imagem i = new Imagem(f1.getName(), fis);
//        dao.criar(i);
//        fis = new FileInputStream(f2);
//        i = new Imagem(f2.getName(), fis);
//        dao.criar(i);
//        fis = new FileInputStream(f3);
//        i = new Imagem(f3.getName(), fis);
//        dao.criar(i);
//        fis = new FileInputStream(f4);
//        i = new Imagem(f4.getName(), fis);
//        dao.criar(i);
//        fis = new FileInputStream(f5);
//        i = new Imagem(f5.getName(), fis);
//        dao.criar(i);
        File f = new File(AdministradorMB.class.getResource("./_temp/").toURI());
        ImagemDao dao = new ImagemDao();
        Imagem i = dao.pesquisar(new Imagem("DSC05751.JPG", null), 0, 0, 0).get(0);
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(f));
        InputStreamReader isr = new InputStreamReader(i.getInputStream());
        char[] buffer = new char[4096];
        int pos = 0;
        while ((pos = isr.read(buffer, pos, buffer.length)) != 0) {
            osw.write(buffer, pos - buffer.length, buffer.length);
        }
        osw.close();
        isr.close();
    }
}
