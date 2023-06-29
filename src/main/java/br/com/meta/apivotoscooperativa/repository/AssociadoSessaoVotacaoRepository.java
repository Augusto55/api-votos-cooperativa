package br.com.meta.apivotoscooperativa.repository;

import br.com.meta.apivotoscooperativa.model.Associado;
import br.com.meta.apivotoscooperativa.model.AssociadoSessaoVotacao;
import br.com.meta.apivotoscooperativa.model.SessaoVotacao;
import org.springframework.data.repository.CrudRepository;

public interface AssociadoSessaoVotacaoRepository extends CrudRepository<AssociadoSessaoVotacao, Integer> {
    boolean existsByAssociadoAndSessaoVotacao(Associado associado, SessaoVotacao sessaoVotacao);
}
