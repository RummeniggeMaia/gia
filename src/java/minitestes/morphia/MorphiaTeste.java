/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package minitestes.morphia;

import br.ufrn.cerescaico.labordoc.gia.dao.UsuarioDao;
import br.ufrn.cerescaico.labordoc.gia.modelo.Usuario;
import br.ufrn.cerescaico.labordoc.gia.negocio.criteria.CriteriaStrategyIF;
import br.ufrn.cerescaico.labordoc.gia.negocio.criteria.NullCriteria;
import br.ufrn.cerescaico.labordoc.gia.util.Consts;
import br.ufrn.cerescaico.labordoc.gia.util.Util;
import java.net.UnknownHostException;
import org.bson.types.ObjectId;

/**
 *
 * @author Rummenigge
 */
public class MorphiaTeste {

    public static void main(String[] asd) throws UnknownHostException {
//        criar();
        pesqusiar();
//        editar();
//        excluir();
    }

    public static void criar() throws UnknownHostException {
//        ObjectId i1 = new ObjectId("52165a710dcac14014f0e6d6");
//        ObjectId i2 = new ObjectId("52165a710dcac14014f0e6d6");
//        Util.pf("%b\n", i1.equals(i2));
//        ExecucaoDao dao = new ExecucaoDao();
//        Usuario u = new Usuario();
//        Funcao f = new Funcao();
//        u.setId(new ObjectId("520a853aa5ca260ef9888a34"));
//        f.setId(new ObjectId("5215478a004b5fffc4aaea42"));
//        Execucao e = new Execucao("editar_usuario");
//        e.setUsuario(u);
//        e.setFuncao(f);
//        dao.criar(e);
//        UsuarioDao usuarioDao = new UsuarioDao();
//        Usuario u = new Usuario();
//        u.setId(new ObjectId(Consts.NULL_OBJECT_ID));
//        u.getFuncoes().add(Consts.FUNCAO_CRIAR_USUARIOS);
//        usuarioDao.criar(u);
//        Util.pf("%s | %s", k.toString(), k.getKindClass());
        CriteriaStrategyIF<Usuario> c = (CriteriaStrategyIF) new NullCriteria();
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
//        UsuarioDao usuarioDao = new UsuarioDao();
//        List<Usuario> us = usuarioDao.pesquisar(us, offset, limit)
//        for (Usuario u : us) {
//            Util.pf("%s\n", u.toString());
//        }
        UsuarioDao usuarioDao = new UsuarioDao();
        Usuario u = new Usuario();
        u.setLogin("fghjkl");
        u.setSenha("bnm,");
        Usuario us = usuarioDao.pesquisarUm(u, Consts.CRITERIA_AUTENTICAR);
        Util.pf("%s\n", us);
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
    }
}
