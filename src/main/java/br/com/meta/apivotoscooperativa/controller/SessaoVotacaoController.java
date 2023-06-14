package br.com.meta.apivotoscooperativa.controller;

import br.com.meta.apivotoscooperativa.exception.PautaNotFoundException;
import br.com.meta.apivotoscooperativa.model.Pauta;
import br.com.meta.apivotoscooperativa.model.SessaoVotacao;
import br.com.meta.apivotoscooperativa.service.PautaService;
import br.com.meta.apivotoscooperativa.service.SessaoVotacaoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sessao-votacao")
public class SessaoVotacaoController {
    @Autowired
    SessaoVotacaoService sessaoVotacaoService;

    @Autowired
    PautaService pautaService;

    @GetMapping("")
    Iterable<SessaoVotacao> listarSessoes() {
        return sessaoVotacaoService.listAllSessoes();
    }

    @PostMapping("/")
    @Transactional
    public ResponseEntity<Object> criarSessao(@RequestParam Integer pautaId, @RequestBody SessaoVotacao sessao) {
        try {
            Pauta pauta = pautaService.findById(pautaId);
            if (pauta == null) {
                throw new PautaNotFoundException("Pauta com id " + pautaId + " não foi encontrada.");
            }
            sessao.setPauta(pauta);
            SessaoVotacao sessaoSalva = sessaoVotacaoService.saveSessaoVotacao(sessao);
            pautaService.setSessaoVotacao(pauta, sessaoSalva);
            return ResponseEntity.status(HttpStatus.CREATED).body("Pauta adicionada com sucesso.\nId da pauta: " + sessaoSalva.getId());
        } catch (PautaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            // For any other unexpected exceptions, let's return a 500 status
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }
}
