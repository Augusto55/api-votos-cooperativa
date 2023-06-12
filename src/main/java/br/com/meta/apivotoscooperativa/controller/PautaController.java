package br.com.meta.apivotoscooperativa.controller;

import br.com.meta.apivotoscooperativa.model.Pauta;
import br.com.meta.apivotoscooperativa.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/pautas")
public class PautaController {
    @Autowired
    private PautaService pautaService;

    private static final Logger logger = LoggerFactory.getLogger(PautaController.class);

    @GetMapping("/")
    public ResponseEntity<?> listarPautas() {
        try {
            Iterable<Pauta> pautas = pautaService.listAllPautas();
            return ResponseEntity.ok(pautas);
        } catch (Exception e) {
            logger.error("Unexpected error while listing Pautas: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor.");
        }
    }

    @PostMapping("/")
    public ResponseEntity<String> adicionarPauta(@RequestBody Pauta pauta) {
        try {
            pautaService.isValidPauta(pauta);
            pautaService.savePauta(pauta);
            return ResponseEntity.status(HttpStatus.CREATED).body("Pauta adicionada com sucesso.\nId da pauta: " + pauta.getId());
        } catch (Exception e) {
            logger.error("Unexpected error while adding Pauta: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor.");
        }
    }
}
