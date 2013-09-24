/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package minitestes;

import br.ufrn.cerescaico.labordoc.gia.util.Util;
import java.net.UnknownHostException;

/**
 *
 * @author Rummenigge
 */
public class Teste {

    public static void main(String[] asd) throws UnknownHostException {
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
        String x = "123.456.789-01";
        Util.pf("%s\n", x.matches("\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}"));
    }
}
