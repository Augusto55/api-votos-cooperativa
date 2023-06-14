package br.com.meta.apivotoscooperativa.controller;

import br.com.meta.apivotoscooperativa.model.Associado;
import br.com.meta.apivotoscooperativa.service.AssociadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/associados")
public class AssociadoController {

    @Autowired
    AssociadoService associadoService;

    @GetMapping("")
    public Iterable<Associado> listarAssociados(){
        return associadoService.listAllAssociados();
    }

    @PostMapping("")
    public ResponseEntity<?> salvarAssociado(@Valid @RequestBody Associado associado){
        try {
            associadoService.saveAssociado(associado);
            return ResponseEntity.status(HttpStatus.CREATED).body("Associado cadastrado com sucesso.");}
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nâo foi possível cadastrar o usuário.");
        }
    }
}
