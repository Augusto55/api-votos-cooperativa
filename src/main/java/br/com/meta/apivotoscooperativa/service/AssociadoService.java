package br.com.meta.apivotoscooperativa.service;

import br.com.meta.apivotoscooperativa.model.Associado;
import br.com.meta.apivotoscooperativa.repository.AssociadoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AssociadoService {

    @Autowired
    AssociadoRepository associadoRepository;

    public Iterable<Associado> listAllAssociados(){
        return associadoRepository.findAll();
    }

    public void saveAssociado(Associado associado){
        associadoRepository.save(associado);
    }
}
