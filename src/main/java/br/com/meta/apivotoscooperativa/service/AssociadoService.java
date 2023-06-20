package br.com.meta.apivotoscooperativa.service;

import br.com.meta.apivotoscooperativa.dto.CPFValidationResponse;
import br.com.meta.apivotoscooperativa.model.Associado;
import br.com.meta.apivotoscooperativa.dto.AssociadoDto;
import br.com.meta.apivotoscooperativa.repository.AssociadoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Transactional
public class AssociadoService {

    @Autowired
    AssociadoRepository associadoRepository;
    @Autowired
    private RestTemplate restTemplate;

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

    public boolean isValidCPF(String cpf) {
        String url = "https://api.nfse.io/validate/NaturalPeople/taxNumber/" + cpf;

        try {
            CPFValidationResponse response = restTemplate.getForObject(url, CPFValidationResponse.class);
            if (response != null) {
                return true;
            }
        } catch (HttpClientErrorException.NotFound ex) {
            // CPF not found, associate is not eligible
        }

        return false;
    }
}
