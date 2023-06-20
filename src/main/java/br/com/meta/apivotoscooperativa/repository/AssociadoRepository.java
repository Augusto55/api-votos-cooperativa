package br.com.meta.apivotoscooperativa.repository;

import br.com.meta.apivotoscooperativa.model.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.client.RestOperations;

public interface AssociadoRepository extends JpaRepository<Associado, Integer> {

}
