/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package minitestes.morphia;

import br.ufrn.cerescaico.labordoc.gia.dao.UsuarioDao;
import br.ufrn.cerescaico.labordoc.gia.modelo.Usuario;
import br.ufrn.cerescaico.labordoc.gia.util.Util;
import com.google.code.morphia.Key;
import com.mongodb.WriteResult;
import java.net.UnknownHostException;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Rummenigge
 */
public class MorphiaTeste {

    public static void main(String[] asd) throws UnknownHostException {
//        criar();
//        pesqusiar();
//        editar();
        excluir();
    }

    public static void criar() throws UnknownHostException {
        UsuarioDao usuarioDao = new UsuarioDao();
        Usuario u = new Usuario("A", "B", "C", "D", "E", "F");
        u.setId(new ObjectId("000000000000000000000000"));
        Key<Usuario> k = usuarioDao.criar(u);
        Util.pf("%s | %s", k.toString(), k.getKindClass());
    }

    public static void editar() throws UnknownHostException {
        UsuarioDao usuarioDao = new UsuarioDao();
        Usuario u = new Usuario("a", "b", "c", "d", "e", "f");
        u.setId(new ObjectId("520a853aa5ca260ef9888a34"));
        usuarioDao.editar(u);
    }

    public static void excluir() throws UnknownHostException {
        UsuarioDao usuarioDao = new UsuarioDao();
        Usuario u = new Usuario();
        u.setId(new ObjectId("000000000000000000000000"));
        usuarioDao.excluir(u);
    }

    public static void pesqusiar() throws UnknownHostException {
        UsuarioDao usuarioDao = new UsuarioDao();
        List<Usuario> us = usuarioDao.pesquisar(null);
        for (Usuario u : us) {
            Util.pf("%s\n", u.toString());
        }
    }
}
