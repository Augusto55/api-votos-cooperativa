package br.com.meta.apivotoscooperativa.service;

import br.com.meta.apivotoscooperativa.model.SessaoVotacao;
import br.com.meta.apivotoscooperativa.repository.SessaoVotacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SessaoVotacaoService {

    @Autowired
    SessaoVotacaoRepository sessaoVotacaoRepository;

    public Iterable<SessaoVotacao> listAllSessoes(){
        return sessaoVotacaoRepository.findAll();
    }

    public void saveSessaoVotacao(SessaoVotacao sessaoVotacao){
        sessaoVotacaoRepository.save(sessaoVotacao);
    }
}
