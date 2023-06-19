package br.com.meta.apivotoscooperativa.controller;

import br.com.meta.apivotoscooperativa.exception.PautaNotFoundException;
import br.com.meta.apivotoscooperativa.model.Associado;
import br.com.meta.apivotoscooperativa.model.AssociadoSessaoVotacao;
import br.com.meta.apivotoscooperativa.model.Pauta;
import br.com.meta.apivotoscooperativa.model.SessaoVotacao;
import br.com.meta.apivotoscooperativa.repository.AssociadoSessaoVotacaoRepository;
import br.com.meta.apivotoscooperativa.service.AssociadoService;
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

    @Autowired
    AssociadoService associadoService;

    @Autowired
    AssociadoSessaoVotacaoRepository associadoSessaoVotacaoRepository;

    @GetMapping("")
    public Iterable<SessaoVotacao> listarSessoes() {
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
        try {
            SessaoVotacao  sessao = sessaoVotacaoService.findById(sessaoId);
            return sessaoVotacaoService.updateSessao(sessaoId, sessao);

        } catch (PautaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @PostMapping("/{sessaoId}/votar")
    public ResponseEntity<String> votarNaSessao(
            @PathVariable Integer sessaoId,
            @RequestParam Integer associadoId,
            @RequestParam Boolean voto) {

        try {
            SessaoVotacao sessao = sessaoVotacaoService.findById(sessaoId);
            Associado associado = associadoService.findById(associadoId);

            if (associado == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O associado não está cadastrado");
            }

            if (sessaoVotacaoService.isSessaoExpired(sessao) || !sessaoVotacaoService.isSessaoOpen(sessao)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("SessaoVotacao with id " + sessaoId + " is closed.");
            }

            boolean hasVoted = associadoSessaoVotacaoRepository.existsByAssociadoAndSessaoVotacao(associado, sessao);
            if (hasVoted) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Associado with id " + associadoId +
                        " has already voted in SessaoVotacao with id " + sessaoId);
            }

            sessaoVotacaoService.addVoto(sessao, voto);
            AssociadoSessaoVotacao associadoSessaoVotacao = new AssociadoSessaoVotacao(associado, sessao);
            associadoSessaoVotacaoRepository.save(associadoSessaoVotacao);

            return ResponseEntity.ok("Vote registered successfully.");
        } catch (PautaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while voting in SessaoVotacao with id " + sessaoId);
        }
    }

    @PutMapping("/{sessaoId}/resultado")
    public ResponseEntity<String> mostrarResultado(@PathVariable Integer sessaoId) {
        try {
            SessaoVotacao sessao = sessaoVotacaoService.findById(sessaoId);
            String resultado = sessaoVotacaoService.showResultado(sessao);
            return ResponseEntity.ok(resultado);
        } catch (PautaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while retrieving result for SessaoVotacao with id " + sessaoId);
        }
    }

}
