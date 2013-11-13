//package minitestes.morphia;
//
//import br.ufrn.cerescaico.labordoc.gia.dao.UsuarioDao;
//import br.ufrn.cerescaico.labordoc.gia.modelo.Usuario;
//import br.ufrn.cerescaico.labordoc.gia.util.Util;
//import java.net.UnknownHostException;
//import java.text.ParseException;
//import org.bson.types.ObjectId;
//
///**
// *
// * @author Rummenigge
// */
//public class MorphiaTeste {
//
//    public static void main(String[] asd)
//            throws UnknownHostException, ParseException, Exception {
//        criar();
//        pesqusiar();
//        editar();
//        excluir();
//    }
//370	01/01/1927	Inventário	Arthur Clementino da Silva/ Francisca Mené de Araújo	Consta apenas o ano
//
//    public static void criar() throws UnknownHostException, ParseException, Exception {
//        Documento d = new Documento();
//        DocumentoDao documentoDao = new DocumentoDao();
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//        Map<String, Object> campos = d.getCampos();
//        campos.put("caixa", 200);
//        campos.put("data", sdf.parse("20/05/1956"));
//        campos.put("tipo", "Inventário");
//        campos.put("titulares", "Arthur/ Francisca");
//        Util.pf(documentoDao.criar(d).toString());
//        Usuario u = new Usuario();
//        UsuarioDao usuarioDao = new UsuarioDao();
//        u.setNome("Jao Pe de Fejao");
//        u.setLogin("jao");
//        u.setSenha("456");
//        u.setEmail("meuemail@email.com");
//        u.setCpf("000.000.00-10");
//        u.setRole(Usuario.ROLE_USER);
//        usuarioDao.criar(u);
//        TipoDao tipoDao = new TipoDao();
//        Tipo t = new Tipo();
//        t.setTipo("Habilitação de casamento");
//        List<Campo> campos = t.getCampos();
//        campos.add(new Campo("caixa"));
//        campos.add(new Campo("data"));
//        campos.add(new Campo("titulares"));
//        tipoDao.criar(t);
//
//    }
//
//    public static void editar() throws UnknownHostException, Exception {
//        UsuarioDao usuarioDao = new UsuarioDao();
//        Usuario u = new Usuario("a", "b", "c", "d", "e", "f");
//        u.setId(new ObjectId("520a853aa5ca260ef9888a34"));
//        usuarioDao.editar(u);
//        UsuarioDao dao = new UsuarioDao();
//        Usuario u = new Usuario();
//        u.setId(new ObjectId("521387ef628351ea54cb5902"));
//        dao.editar(u);
//        Usuario u = new Usuario();
//        UsuarioDao dao = new UsuarioDao();
//        Util.pf(u.getId().toString());
//        dao.criar(u);
//    }
//
//    public static void excluir() throws UnknownHostException, Exception {
//        UsuarioDao usuarioDao = new UsuarioDao();
//        Usuario u = new Usuario();
//        u.setId(new ObjectId("000000000000000000000000"));
//        usuarioDao.excluir(u);
//    }
//
//    public static void pesqusiar() throws UnknownHostException, Exception {
//        TipoDao tipoDao = new TipoDao();
//        List<Tipo> tipos = tipoDao.pesquisarTodos(Tipo.class);
//        for (Tipo t : tipos) {
//            Util.pf(t.toString());
//            for (Campo c : t.getCampos()) {
//                Util.pf("\t%s\n", c.toString());
//            }
//        }
//        DocumentoDao documentoDao = new DocumentoDao();
//        Documento d = new Documento();
//        d.setTipo("Habilitação de casamento");
//        d = documentoDao.pesquisarUm(d, Consts.CRITERIA_TIPO_DOCUMENTO);
//        Util.pf("%s\n", d);
//        UsuarioDao usuarioDao = new UsuarioDao();
//        Usuario u = new Usuario();
//        u.setLogin("chicao5");
//        u.setSenha("chico123");
//        Usuario us = usuarioDao.pesquisarUm(u, Consts.CRITERIA_AUTENTICAR);
//        Util.pf("%s\n", us.getLogin());
//        List<Usuario> us = usuarioDao.pesquisar(u, 0, 5, Consts.CRITERIA_AUTENTICAR);
//        for (Usuario a : us) {
//            Util.pf("%s\n", a.toString());
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
//        List<Usuario> us = usuarioDao.pesquisar(u, 0, 10);
//        for (Usuario atual : us) {
//            Util.pf("%s\n", atual.toString());
//        }
//        DocumentoDao documentoDao = new DocumentoDao();
//        Documento x = new Documento();
//        x.setId(new ObjectId("5218b009fd761af97e2d0d54"));
//        x.getCampos().put("tipo", "Habilitação de casamento");
//        x.getCampos().put("caixa", 1);
//        List<Documento> lista = documentoDao
//                .pesquisar(x, 0, 10, Consts.CRITERIA_DOCUMENTO_TIPO);
//        for (Documento d : lista) {
//            Util.pf("%s\n", d.toString());
//        }
//        DocumentoDao dao = new DocumentoDao();
//        Documento d = new Documento();
//        Tipo t = new Tipo();
//        t.setId(new ObjectId("52278a4c13b2e4b2208c33e5"));
//        d.setTipo(null);
//        d.getCampos().put("Caixa", 1);
//        d.getCampos().put("Caixa", 1);
//        List<Documento> lista = dao.pesquisar(d, 0, 10, Consts.CRITERIA_DOCUMENTO_CONJUNTIVA);
//        for (Documento a : lista) {
//            Util.pf("id=%s tipo.id=%s\n", a.getId(), a.getTipo().getId());
//        }
//        Usuario u = new Usuario();
//        u.setNome("Jao Pe de Fejao");
//        u.setLogin("jao");
//        u.setSenha("456");
//        u.setEmail("meuemail@email.com");
//        u.setCpf("000.000.00-10");
//        u.setRole(Usuario.ROLE_USER);
//        TesteMB testeMB = new TesteMB();
//        testeMB.setUsuario(u);
//        testeMB.getPesquisaCtrl().setLimit(2);
//        testeMB.pesquisar();
//        testeMB.getPesquisaCtrl().proxima();
//        testeMB.realizarPesquisaUsuarios();
//        List<Usuario> us = testeMB.getUsers();
//        for (Usuario a : us) {
//            Util.pf("%s\n", a.getId().toString());
//        }
//    }
//}
