/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package minitestes;

import br.ufrn.cerescaico.labordoc.gia.dao.UsuarioDao;
import br.ufrn.cerescaico.labordoc.gia.modelo.Usuario;
import br.ufrn.cerescaico.labordoc.gia.negocio.PaginacaoCtrl;
import br.ufrn.cerescaico.labordoc.gia.util.Consts;
import br.ufrn.cerescaico.labordoc.gia.util.Util;
import br.ufrn.cerescaico.labordoc.gia.converter.NumeConverter;
import br.ufrn.cerescaico.labordoc.gia.validator.ValidarUsuarioCPF;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.event.ActionEvent;
import javax.faces.validator.Validator;

/**
 *
 * @author Rummenigge
 */
@ManagedBean
@SessionScoped
public class TesteMB implements Serializable {

    private Usuario usuario;
    private UsuarioDao usuarioDao;
    private List<Usuario> users;
    private PaginacaoCtrl paginacaoCtrl;
    private Converter ic = new NumeConverter();
    private Validator v = new ValidarUsuarioCPF();
    private Converter c;

    public TesteMB() {
        c = new Converter() {

            @Override
            public Object getAsObject(FacesContext context, UIComponent component, String value) {
                return value == null ? "null" : value.trim();
            }

            @Override
            public String getAsString(FacesContext context, UIComponent component, Object value) {
                return value.toString();
            }
        };
//        try {
//            usuario = new Usuario();
//            usuarioDao = new UsuarioDao();
//            users = new ArrayList<Usuario>();
//            paginacaoCtrl = new PaginacaoCtrl();
//        } catch (Exception e) {
//            Util.addMsg(null, e.getMessage(), FacesMessage.SEVERITY_ERROR);
//        }
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

    public void pesquisar() {
        try {
            Usuario aux = new Usuario();
            aux.setCpf(usuario.getCpf());
            aux.setEmail(usuario.getEmail());
            aux.setMatricula(usuario.getMatricula());
            aux.setNome(usuario.getNome());
            aux.setRole(usuario.getRole());
            paginacaoCtrl.setEntidade(aux);
            paginacaoCtrl.setCont(
                    (int) usuarioDao.contar(usuario,
                    Consts.CRITERIA_USUARIO_CONJUNTIVA));
            paginacaoCtrl.primeira();
            realizarPesquisaUsuarios();
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public PaginacaoCtrl getPesquisaCtrl() {
        return paginacaoCtrl;
    }

    public void setPesquisaCtrl(PaginacaoCtrl pesquisaCtrl) {
        this.paginacaoCtrl = pesquisaCtrl;
    }

    public UsuarioDao getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public Converter getIc() {
        return ic;
    }

    public void setIc(Converter ic) {
        this.ic = ic;
    }

    public void realizarPesquisaUsuarios() {
        try {
            users = usuarioDao.pesquisar(
                    (Usuario) paginacaoCtrl.getEntidade(),
                    paginacaoCtrl.getOffset(),
                    paginacaoCtrl.getLimit(),
                    Consts.CRITERIA_USUARIO_CONJUNTIVA);
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void paginar(ActionEvent ae) {
        String cmd = ae.getComponent().getId();
        if (cmd.equals("primeira")) {
            paginacaoCtrl.primeira();
        } else if (cmd.equals("anterior")) {
            paginacaoCtrl.anterior();
        } else if (cmd.equals("proxima")) {
            paginacaoCtrl.proxima();
        } else if (cmd.equals("ultima")) {
            paginacaoCtrl.ultima();
        }
    }

    public Validator getV() {
        return v;
    }

    public void setV(Validator v) {
        this.v = v;
    }

    public Converter getC() {
        return c;
    }

    public void setC(Converter c) {
        this.c = c;
    }
}
