/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package minitestes;

import br.ufrn.cerescaico.labordoc.gia.dao.ImagemDao;
import br.ufrn.cerescaico.labordoc.gia.modelo.Imagem;
import java.io.File;
import java.io.FileInputStream;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

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
//        
//        FileInputStream fis = new FileInputStream(f1);
//        Imagem i = new Imagem();
//        StreamedContent sc = new DefaultStreamedContent(fis, "image/jpeg", f1.getName());
//        i.setStreamedContent(sc);
//        dao.criar(i);
//        fis.close();
//        
//        fis = new FileInputStream(f2);
//        i = new Imagem();
//        sc = new DefaultStreamedContent(fis, "image/jpeg", f2.getName());
//        i.setStreamedContent(sc);
//        dao.criar(i);
//        fis.close();
//        
//        fis = new FileInputStream(f3);
//        i = new Imagem();
//        sc = new DefaultStreamedContent(fis, "image/jpeg", f3.getName());
//        i.setStreamedContent(sc);
//        dao.criar(i);
//        fis.close();
//        
//        fis = new FileInputStream(f4);
//        i = new Imagem();
//        sc = new DefaultStreamedContent(fis, "image/jpeg", f4.getName());
//        i.setStreamedContent(sc);
//        dao.criar(i);
//        fis.close();
//        
//        fis = new FileInputStream(f5);
//        i = new Imagem();
//        sc = new DefaultStreamedContent(fis, "image/jpeg", f5.getName());
//        i.setStreamedContent(sc);
//        dao.criar(i);
//        fis.close();
        
//        File f = new File(AdministradorMB.class.getResource("./_temp/").toURI() + "\\img.jpg");
//        ImagemDao dao = new ImagemDao();
//        Imagem i = dao.pesquisar(new Imagem("DSC05751.JPG", null), 0, 0, 0).get(0);
//        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(f));
//        InputStreamReader isr = new InputStreamReader(i.getInputStream());
//        char[] buffer = new char[4096];
//        int pos = 0;
//        while ((pos = isr.read(buffer, pos, buffer.length)) != 0) {
//            osw.write(buffer, pos - buffer.length, buffer.length);
//        }
//        osw.close();
//        isr.close();
//        InputStream is = null;
//
//        try {
//            // new input stream created
//            is = new FileInputStream("D:/teste.txt");
//            BufferedInputStream bis = new BufferedInputStream(is);
//
//            System.out.println("Characters printed:");
//            // create new buffered reader
//
//            bis.mark(0);
//            // reads and prints BufferedReader
//            System.out.println((char) bis.read());
//            System.out.println((char) bis.read());
//
//            // mark invoked at this position
////            bis.mark(0);
//            System.out.println("mark() invoked");
//            System.out.println((char) bis.read());
//            System.out.println((char) bis.read());
//
//            // reset() repositioned the stream to the mark
//            if (bis.markSupported()) {
//                bis.reset();
//                System.out.println("reset() invoked");
//                System.out.println((char) bis.read());
//                System.out.println((char) bis.read());
//            } else {
//                System.out.print("InputStream does not support reset()");
//            }
//        } catch (Exception e) {
//
//            // if any I/O error occurs
//            e.printStackTrace();
//        } finally {
//
//            // releases system resources associated with this stream
//            if (is != null) {
//                is.close();
//            }
//        }
    }
}
