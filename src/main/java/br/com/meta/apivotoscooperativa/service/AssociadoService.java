package br.com.meta.apivotoscooperativa.service;

import br.com.meta.apivotoscooperativa.exception.InvalidCpfException;
import br.com.meta.apivotoscooperativa.model.Associado;
import br.com.meta.apivotoscooperativa.dto.AssociadoDto;
import br.com.meta.apivotoscooperativa.repository.AssociadoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class AssociadoService {


    private final AssociadoRepository associadoRepository;

    public AssociadoService(AssociadoRepository associadoRepository) {
        this.associadoRepository = associadoRepository;
    }

    public Iterable<Associado> listAllAssociados(){
        return associadoRepository.findAll();
    }

    public Associado findById(Integer id) {
        Optional<Associado> associadoOptional = associadoRepository.findById(id);
        return associadoOptional.orElse(null);
    }

    private String formatCpf(AssociadoDto associado){
        return associado.cpf().replaceAll("[^\\sa-zA-Z0-9]", "");
    }

    @Transactional
    public void saveAssociado(AssociadoDto associadoDto){
        String cpf = formatCpf(associadoDto);
        if(cpf.length() != 11) throw new InvalidCpfException("O cpf do associado deve ter 11 caracteres");
        Associado associado = new Associado(associadoDto);
        associado.setCpf(cpf);
        if (isValidCPF(cpf)){
            associadoRepository.save(associado);
        }
    }

    public boolean isValidCPF(String cpf) {
        String url = "https://api.nfse.io/validate/NaturalPeople/taxNumber/" + cpf;
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<Map> result = restTemplate.getForEntity(url, Map.class);

            if (result.getStatusCode() == HttpStatus.OK) {
                Map<String, Boolean> body = result.getBody();
                boolean valid = ( body != null && Boolean.TRUE.equals(body.get("valid")));
                if (valid) {
                    return true;
                } else {
                    throw new InvalidCpfException("Cpf Invalido");
                }
            }
            return false;

        } catch (HttpClientErrorException.NotFound e) {
            throw new InvalidCpfException("Cpf Invalido");
        }
    }
}
