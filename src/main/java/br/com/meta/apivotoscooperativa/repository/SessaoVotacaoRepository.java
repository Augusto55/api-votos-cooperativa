package br.com.meta.apivotoscooperativa.repository;

import br.com.meta.apivotoscooperativa.model.SessaoVotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Integer> {

    @Query("select s.id from SessaoVotacao s order by s.id desc limit 1")
    Integer getLastId();
}
