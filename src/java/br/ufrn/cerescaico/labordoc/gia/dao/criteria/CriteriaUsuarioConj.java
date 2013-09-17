package br.ufrn.cerescaico.labordoc.gia.dao.criteria;

import br.ufrn.cerescaico.labordoc.gia.modelo.Usuario;
import br.ufrn.cerescaico.labordoc.gia.util.Consts;
import com.google.code.morphia.query.Criteria;
import com.google.code.morphia.query.Query;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rummenigge
 */
public class CriteriaUsuarioConj
        implements CriteriaStrategyIF<Usuario, Query<Usuario>> {

    @Override
    public void operationCriteria(Usuario entity, Query<Usuario> query) {
        if (entity == null) {
            return;
        }
        List<Criteria> criterias = new ArrayList<Criteria>();
        if (entity.getNome() != null && !entity.getNome().isEmpty()) {
            criterias.add(query.criteria(Consts.CAMPO_NOME)
                    .equal(entity.getNome()));
        }
        if (entity.getEmail() != null && !entity.getEmail().isEmpty()) {
            criterias.add(query.criteria(Consts.CAMPO_EMAIL)
                    .equal(entity.getEmail()));
        }
        if (entity.getCpf() != null && !entity.getCpf().isEmpty()) {
            criterias.add(query.criteria(Consts.CAMPO_CPF)
                    .equal(entity.getCpf()));
        }
        if (entity.getMatricula() != null && !entity.getMatricula().isEmpty()) {
            criterias.add(query.criteria(Consts.CAMPO_MATRICULA)
                    .equal(entity.getMatricula()));
        }
        if (entity.getRole() != null && !entity.getRole().isEmpty()) {
            criterias.add(query.criteria(Consts.CAMPO_ROLE)
                    .equal(entity.getRole()));
        }
        Criteria[] vet = new Criteria[criterias.size()];
        for (int i = 0; i < vet.length; i++) {
            vet[i] = criterias.get(i);
        }
        query.and(vet);
    }
}
