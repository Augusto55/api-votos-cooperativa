package br.com.meta.apivotoscooperativa.controller;

import br.com.meta.apivotoscooperativa.commons.enums;
import br.com.meta.apivotoscooperativa.dto.PautaRegisterDto;
import br.com.meta.apivotoscooperativa.model.Pauta;
import br.com.meta.apivotoscooperativa.service.PautaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

@RestController
@RequestMapping("/pautas")
public class PautaController {
    private final PautaService pautaService;

    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    private static final Logger logger = LoggerFactory.getLogger(PautaController.class);

    @GetMapping("")
    public ResponseEntity<?> listarPautas() {
            Iterable<Pauta> pautas = pautaService.listAllPautas();
            return ResponseEntity.ok(pautas);
    }

    @PostMapping("")
    @Transactional
    public ResponseEntity<Pauta> adicionarPauta(@Valid @RequestBody PautaRegisterDto pautaRegisterDto) {
        Pauta pauta = new Pauta(pautaRegisterDto);
        try {
            pautaService.isValidPauta(pauta);
            if(!Objects.equals(pauta.getResultadoSessao(),enums.PautaStatus.PENDENTE))
                pauta.setResultadoSessao(enums.PautaStatus.PENDENTE);
            pautaService.savePauta(pauta);
            return new ResponseEntity<>(pauta, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Erro inesperado ao cadastrar pauta: ", e);
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
