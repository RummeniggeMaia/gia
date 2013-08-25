/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package minitestes.morphia;

import br.ufrn.cerescaico.labordoc.gia.dao.DocumentoDao;
import br.ufrn.cerescaico.labordoc.gia.dao.UsuarioDao;
import br.ufrn.cerescaico.labordoc.gia.modelo.Documento;
import br.ufrn.cerescaico.labordoc.gia.modelo.Usuario;
import br.ufrn.cerescaico.labordoc.gia.util.Consts;
import br.ufrn.cerescaico.labordoc.gia.util.Util;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import org.bson.types.ObjectId;

/**
 *
 * @author Rummenigge
 */
public class MorphiaTeste {

    public static void main(String[] asd) 
            throws UnknownHostException, ParseException {
//        criar();
        pesqusiar();
//        editar();
//        excluir();
    }
//370	01/01/1927	Inventário	Arthur Clementino da Silva/ Francisca Mené de Araújo	Consta apenas o ano
    public static void criar() throws UnknownHostException, ParseException {
        Documento d = new Documento();
        DocumentoDao documentoDao = new DocumentoDao();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Map<String, Object> campos = d.getCampos();
        campos.put("caixa", 370);
        campos.put("data", sdf.parse("01/01/1927"));
        campos.put("tipo", "Inventário");
        campos.put("titulares", "Arthur Clementino da Silva/ Francisca Mené de Araújo");
        Util.pf(documentoDao.criar(d).toString());
    }

    public static void editar() throws UnknownHostException {
//        UsuarioDao usuarioDao = new UsuarioDao();
//        Usuario u = new Usuario("a", "b", "c", "d", "e", "f");
//        u.setId(new ObjectId("520a853aa5ca260ef9888a34"));
//        usuarioDao.editar(u);
        UsuarioDao dao = new UsuarioDao();
        Usuario u = new Usuario();
        u.setId(new ObjectId("521387ef628351ea54cb5902"));
        dao.editar(u);
    }

    public static void excluir() throws UnknownHostException {
        UsuarioDao usuarioDao = new UsuarioDao();
        Usuario u = new Usuario();
        u.setId(new ObjectId("000000000000000000000000"));
        usuarioDao.excluir(u);
    }

    public static void pesqusiar() throws UnknownHostException {
//        DocumentoDao documentoDao = new DocumentoDao();
//        Documento d = new Documento();
//        d.setTipo("Habilitação de casamento");
//        d = documentoDao.pesquisarUm(d, Consts.CRITERIA_TIPO_DOCUMENTO);
//        Util.pf("%s\n", d);
//        UsuarioDao usuarioDao = new UsuarioDao();
//        List<Usuario> us = usuarioDao.pesquisar(us, offset, limit)
//        for (Usuario u : us) {
//            Util.pf("%s\n", u.toString());
//        }
//        UsuarioDao usuarioDao = new UsuarioDao();
//        Usuario u = new Usuario();
//        u.setLogin("fghjkl");
//        u.setSenha("bnm,");
//        Usuario us = usuarioDao.pesquisarUm(u, Consts.CRITERIA_AUTENTICAR);
//        Util.pf("%s\n", us);
//        }
//        u.setNome("C");
//        u.setEmail("J");
//        u.setEmail("J");
//        u.setCpf("E");
//        u.setMatricula("L");
////        List<Usuario> us = usuarioDao.pesquisar(u, 0, 10);
//        for (Usuario atual : us) {
//            Util.pf("%s\n", atual.toString());
//        }
        DocumentoDao documentoDao = new DocumentoDao();
        Documento x = new Documento();
        //x.setId(new ObjectId("5218b009fd761af97e2d0d54"));
        x.getCampos().put("tipo", "Habilitação de casamento civil");
        x.getCampos().put("caixa", 1);
        List<Documento> lista = documentoDao
                .pesquisar(x, 0, 10, Consts.CRITERIA_DOCUMENTO);
        for (Documento d : lista) {
            Util.pf("%s\n", d.toString());
        }
    }
}
