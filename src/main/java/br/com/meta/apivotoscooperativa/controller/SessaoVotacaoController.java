package br.com.meta.apivotoscooperativa.controller;

import br.com.meta.apivotoscooperativa.exception.PautaNotFoundException;
import br.com.meta.apivotoscooperativa.model.Pauta;
import br.com.meta.apivotoscooperativa.model.SessaoVotacao;
import br.com.meta.apivotoscooperativa.service.PautaService;
import br.com.meta.apivotoscooperativa.service.SessaoVotacaoService;
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
    public ResponseEntity<Object> criarSessao(@RequestParam Integer pautaId, @RequestBody SessaoVotacao sessao) {
        Pauta pauta;
        try {
            pauta = pautaService.findById(pautaId);
        } catch (PautaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pauta com id " + pautaId + " não foi encontrada.");
        }

        return sessaoVotacaoService.createSessao(pauta, sessao);
    }



    @PutMapping("/{sessaoId}")
    public ResponseEntity<String> openSessao(@PathVariable Integer sessaoId) {
        SessaoVotacao sessao;
        try {
            sessao = sessaoVotacaoService.findById(sessaoId);
            if (sessao == null) {
                throw new PautaNotFoundException("SessaoVotacao with id " + sessaoId + " was not found.");
            }
        } catch (PautaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return sessaoVotacaoService.updateSessao(sessaoId, sessao);
    }

}
