package br.com.meta.apivotoscooperativa.controller;

import br.com.meta.apivotoscooperativa.exception.InvalidCpfException;
import br.com.meta.apivotoscooperativa.model.Associado;
import br.com.meta.apivotoscooperativa.dto.AssociadoDto;
import br.com.meta.apivotoscooperativa.service.AssociadoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/associados")
public class AssociadoController {


    private final AssociadoService associadoService;


    private static final Logger logger = LoggerFactory.getLogger(Associado.class);

    public AssociadoController(AssociadoService associadoService) {
        this.associadoService = associadoService;
    }


    @GetMapping("")
    public Iterable<Associado> listarAssociados(){
        return associadoService.listAllAssociados();
    }

    @Transactional
    @PostMapping("")
    public ResponseEntity<?> postAssociado(@Valid @RequestBody AssociadoDto associado) {
        try {
            associadoService.saveAssociado(associado);
            return ResponseEntity.status(HttpStatus.CREATED).body("Associado cadastrado com sucesso.");
        }catch (InvalidCpfException ex) {
            logger.error("Erro no cpf do associado: ", ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
