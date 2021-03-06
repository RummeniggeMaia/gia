/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package minitestes;

import br.ufrn.cerescaico.labordoc.gia.dao.UsuarioDao;
import br.ufrn.cerescaico.labordoc.gia.modelo.Usuario;
import br.ufrn.cerescaico.labordoc.gia.negocio.Paginacao;
import br.ufrn.cerescaico.labordoc.gia.util.Consts;
import br.ufrn.cerescaico.labordoc.gia.util.Util;
import br.ufrn.cerescaico.labordoc.gia.converter.NumeConverter;
import br.ufrn.cerescaico.labordoc.gia.converter.TipoConverter;
import br.ufrn.cerescaico.labordoc.gia.dao.TipoDao;
import br.ufrn.cerescaico.labordoc.gia.modelo.Tipo;
import br.ufrn.cerescaico.labordoc.gia.validator.ValidarUsuarioCPF;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.convert.Converter;
import javax.faces.event.ActionEvent;
import javax.faces.validator.Validator;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Rummenigge
 */
@ManagedBean
@SessionScoped
public class TesteMB implements Serializable {

    private Usuario usuario;
    private UsuarioDao usuarioDao;
    public static List<Usuario> users = new ArrayList<Usuario>();
    private Paginacao paginacaoCtrl;
    private Converter ic = new NumeConverter();
    private Validator v = new ValidarUsuarioCPF();
    private Converter c = new TesteConv();
    private StreamedContent img;
    private TipoDao tipoDao;
    private Tipo tipo;
    private List<Tipo> tipos;
    private Converter tipoConverter;

    public StreamedContent getImg() {
        return img;
    }
    
    public TesteMB() {
        try {
            tipoDao = new TipoDao();
            tipo = new Tipo();
            tipos = new ArrayList<Tipo>();
//            tipoConverter = new TipoConverter(tipos);
            tipos = tipoDao.pesquisarTodos(Tipo.class);
    //        try {
    //            GridFS fS = new DocumentoDao().getGridFS();
    //            GridFSDBFile dBFile = fS.findOne("violao");
    //            img = new DefaultStreamedContent(dBFile.getInputStream(), "image/jpeg");
    //        } catch (Exception ex) {
    //            Logger.getLogger(TesteMB.class.getName()).log(Level.SEVERE, null, ex);
    //        }
    //        try {
    //            usuario = new Usuario();
    //            usuarioDao = new UsuarioDao();
    //            users = new ArrayList<Usuario>();
    //            paginacaoCtrl = new Paginacao();
    //        } catch (Exception e) {
    //            Util.addMsg(null, e.getMessage(), FacesMessage.SEVERITY_ERROR);
    //        }
    //        Usuario u1 = new Usuario();
    //        u1.setNome("joao");
    //        Usuario u2 = new Usuario();
    //        u2.setNome("chico");
    //        Usuario u3 = new Usuario();
    //        u3.setNome("jose");
    //        Usuario u4 = new Usuario();
    //        u4.setNome("tereza");
    //        Usuario u5 = new Usuario();
    //        u5.setNome("tico");
    //        users.add(u1);
    //        users.add(u2);
    //        users.add(u3);
    //        users.add(u5);
    //        users.add(u5);
        } catch (Exception ex) {
            Logger.getLogger(TesteMB.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public Paginacao getPesquisaCtrl() {
        return paginacaoCtrl;
    }

    public void setPesquisaCtrl(Paginacao pesquisaCtrl) {
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
    
    public List<String> completeUsuarioNome(String q) {
        List<String> lista = new ArrayList<String>();
        for (Usuario u : users) {
            if (u.getNome().matches(q + ".*")) {
                lista.add(u.getNome());
            }
        }
        return lista;
    }
    
    public List<String> completeTipoNome(String q) {
        List<String> lista = new ArrayList<String>();
        for (Tipo t : tipos) {
            if (t.getNome().matches(q + ".*")) {
                lista.add(t.getNome());
            }
        }
        return lista;
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

    public Converter getTipoConverter() {
        return tipoConverter;
    }

    public void setTipoConverter(Converter tipoConverter) {
        this.tipoConverter = tipoConverter;
    }
}
