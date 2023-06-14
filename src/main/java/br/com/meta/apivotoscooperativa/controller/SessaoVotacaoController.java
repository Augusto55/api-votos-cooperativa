package br.com.meta.apivotoscooperativa.controller;

import br.com.meta.apivotoscooperativa.model.Pauta;
import br.com.meta.apivotoscooperativa.model.SessaoVotacao;
import br.com.meta.apivotoscooperativa.service.PautaService;
import br.com.meta.apivotoscooperativa.service.SessaoVotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<SessaoVotacao> criarSessao(@RequestParam Integer pautaId, @RequestBody SessaoVotacao sessao) {
        Pauta pauta = pautaService.findById(pautaId);
        if (pauta != null) {
            sessao.setPauta(pauta);
            SessaoVotacao sessaoSalva = sessaoVotacaoService.saveSessaoVotacao(sessao);
            pautaService.setSessaoVotacao(pauta, sessaoSalva);
            return ResponseEntity.ok(sessaoSalva);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
