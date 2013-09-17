package br.ufrn.cerescaico.labordoc.gia.dao;

import br.ufrn.cerescaico.labordoc.gia.dao.criteria.*;
import br.ufrn.cerescaico.labordoc.gia.modelo.Usuario;
import br.ufrn.cerescaico.labordoc.gia.util.Consts;
import com.google.code.morphia.query.*;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.security.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rummenigge
 */
public class UsuarioDao extends MongoDao<Usuario> implements Serializable {

    private CriteriaStrategyIF<Usuario, Query<Usuario>> criteriaStrategyIF;
    private CriteriaUsuario buscaUsuario = new CriteriaUsuario();
    private CriteriaUsuarioId buscaUsuarioId =
            new CriteriaUsuarioId();
    private CriteriaUsuarioConj buscaConjutiva = new CriteriaUsuarioConj();
    private CriteriaAutentica autenticaCriteria = new CriteriaAutentica();
    private NullCriteria nullCriteria = new NullCriteria();

    public UsuarioDao() throws UnknownHostException {
        super();
        criteriaStrategyIF = nullCriteria;
    }

    @Override
    public Object criar(Usuario e) throws Exception {
        e.setId(null);
        return super.criar(e);
    }

    @Override
    public List<Usuario> pesquisar(
            Usuario e,
            int offset,
            int limit,
            Integer criteria) {

        Query<Usuario> query = dataStore.find(Usuario.class);
        mudarCriteria(criteria);
        query.offset(offset);
        query.limit(limit);
        criteriaStrategyIF.operationCriteria(e, query);
        return query.asList();
    }
    
    public long contar(
            Usuario e,
            Integer criteria) {
        
        Query<Usuario> query = dataStore.find(Usuario.class);
        mudarCriteria(criteria);
        criteriaStrategyIF.operationCriteria(e, query);
        return query.countAll();
    }

    private void mudarCriteria(Integer criteria) {
        if (criteria == Consts.CRITERIA_USUARIO_ID) {
            criteriaStrategyIF = buscaUsuarioId;
        } else if (criteria == Consts.CRITERIA_AUTENTICAR) {
            criteriaStrategyIF = autenticaCriteria;
        } else if (criteria == Consts.CRITERIA_USUARIO) {
            criteriaStrategyIF = buscaUsuario;
        } else if (criteria == Consts.CRITERIA_USUARIO_CONJUNTIVA) {
            criteriaStrategyIF = buscaConjutiva;
        } else {
            criteriaStrategyIF = nullCriteria;
        }
    }

    private void criptografar(Usuario e) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.reset();
            String loginSenha = e.getLogin() + e.getSenha();
            messageDigest.update(loginSenha.getBytes("UTF-8"));
            e.setSenha(new String(
                    messageDigest.digest(loginSenha.getBytes("UTF-8"))));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(
                    UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(
                    UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
