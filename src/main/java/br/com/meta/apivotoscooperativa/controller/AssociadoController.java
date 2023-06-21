package br.com.meta.apivotoscooperativa.controller;

import br.com.meta.apivotoscooperativa.model.Associado;
import br.com.meta.apivotoscooperativa.dto.AssociadoDto;
import br.com.meta.apivotoscooperativa.service.AssociadoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/associados")
public class AssociadoController {


    private final AssociadoService associadoService;

    public AssociadoController(AssociadoService associadoService) {
        this.associadoService = associadoService;
    }

    private static final Logger logger = LoggerFactory.getLogger(Associado.class);


    @GetMapping("")
    public Iterable<Associado> listarAssociados(){
        return associadoService.listAllAssociados();
    }

    @PostMapping("")
    public ResponseEntity<?> postAssociado(@Valid @RequestBody AssociadoDto associado) {
        try {
            associadoService.saveAssociado(associado);
            return ResponseEntity.status(HttpStatus.CREATED).body("Associado cadastrado com sucesso.");
        } catch (Exception e) {
            logger.error("Erro inesperado ao salvar Associado: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar Associado.");
        }
    }
}
