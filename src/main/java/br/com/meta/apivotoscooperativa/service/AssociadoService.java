package br.com.meta.apivotoscooperativa.service;

import br.com.meta.apivotoscooperativa.model.Associado;
import br.com.meta.apivotoscooperativa.dto.AssociadoDto;
import br.com.meta.apivotoscooperativa.repository.AssociadoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class AssociadoService {

    @Autowired
    AssociadoRepository associadoRepository;

    public Iterable<Associado> listAllAssociados(){
        return associadoRepository.findAll();
    }

    public Associado findById(Integer id) {
        Optional<Associado> associadoOptional = associadoRepository.findById(id);
        return associadoOptional.orElse(null);
    }

    @Transactional
    public void saveAssociado(AssociadoDto associado){
        associadoRepository.save(new Associado(associado));
    }
}
