package br.com.meta.apivotoscooperativa.controller;

import br.com.meta.apivotoscooperativa.model.Pauta;
import br.com.meta.apivotoscooperativa.service.PautaService;
import org.hibernate.PropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pautas")
public class PautaController {
    @Autowired
    private PautaService pautaService;

    @GetMapping("")
    public Iterable<Pauta> listarPautas(){
        return pautaService.listAllPautas();
    }

    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionarPauta(@RequestBody Pauta pauta){
        try {
            pautaService.savePauta(pauta);
            return ResponseEntity.status(HttpStatus.CREATED).body("Pauta adicionada com sucesso.\nId da pauta: " + pauta.getId());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campos não válidos.");
        }
    }
}
