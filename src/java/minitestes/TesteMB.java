/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package minitestes;

import br.ufrn.cerescaico.labordoc.gia.dao.TipoDao;
import br.ufrn.cerescaico.labordoc.gia.modelo.Tipo;
import br.ufrn.cerescaico.labordoc.gia.modelo.Usuario;
import br.ufrn.cerescaico.labordoc.gia.util.converter.TipoConverter;
import br.ufrn.cerescaico.labordoc.gia.util.converter.UsuarioConverter;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Rummenigge
 */
@ManagedBean
@ViewScoped
public class TesteMB {

    private List<String> lista;
    private List<String> headers;
    private Tipo tipo = new Tipo();
    private List<Tipo> tipos;
    private Usuario usuario;
    private List<Usuario> users = new ArrayList<Usuario>();
    private TipoConverter tc;
    private UsuarioConverter uc = new UsuarioConverter(users);

    public TesteMB() {
        usuario = new Usuario();
        users.add(new Usuario("A", "B", "C", "D", "E", "F"));
        users.add(new Usuario("G", "H", "I", "J", "K", "L"));
        users.add(new Usuario("M", "N", "O", "P", "Q", "R"));
        try {
            tipos = new TipoDao().pesquisarTodos(Tipo.class);
            tc = new TipoConverter(tipos);
        } catch (Exception e) {
        }
    }

    public List<String> getLista() {
        return lista;
    }

    public void setLista(List<String> lista) {
        this.lista = lista;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public List<Tipo> getTipos() {
        return tipos;
    }

    public void setTipos(List<Tipo> tipos) {
        this.tipos = tipos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsers() {
        return users;
    }

    public void setUsers(List<Usuario> users) {
        this.users = users;
    }

    public UsuarioConverter getUc() {
        return uc;
    }

    public void setUc(UsuarioConverter uc) {
        this.uc = uc;
    }

    public TipoConverter getTc() {
        return tc;
    }

    public void setTc(TipoConverter tc) {
        this.tc = tc;
    }
}
