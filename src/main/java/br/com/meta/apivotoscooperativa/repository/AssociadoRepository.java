package br.com.meta.apivotoscooperativa.repository;

import br.com.meta.apivotoscooperativa.model.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AssociadoRepository extends JpaRepository<Associado, Integer> {
    @Query("select a.cpf from Associado a where a.cpf = :cpf")
    String getAssociadoWithCpf(@Param("cpf") String cpf);
}
