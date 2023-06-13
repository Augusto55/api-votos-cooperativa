package br.com.meta.apivotoscooperativa.controller;

import br.com.meta.apivotoscooperativa.model.Pauta;
import br.com.meta.apivotoscooperativa.model.SessaoVotacao;
import br.com.meta.apivotoscooperativa.repository.SessaoVotacaoRepository;
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
    Iterable<SessaoVotacao> listarSessoes(){
        return sessaoVotacaoService.listAllSessoes();
    }

    @PostMapping("/{pauta_Id}")
    ResponseEntity<String> adicionarSessaoPauta(@RequestBody SessaoVotacao sessaoVotacao,
                                                @RequestParam Integer id){
        sessaoVotacaoService.saveSessaoVotacao(sessaoVotacao);
        Pauta pauta = pautaService.acharPorId(id);
        sessaoVotacao.setPauta(pauta);
        return ResponseEntity.status(HttpStatus.CREATED).body("Criado");
    }
}
