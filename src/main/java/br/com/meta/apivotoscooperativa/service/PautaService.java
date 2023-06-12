package br.com.meta.apivotoscooperativa.service;

import br.com.meta.apivotoscooperativa.model.Pauta;
import br.com.meta.apivotoscooperativa.repository.PautaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PautaService {
    @Autowired
    private PautaRepository pautaRepository;

    public Iterable<Pauta> listAllPautas() {
        return pautaRepository.findAll();
    }

    public void savePauta(Pauta pauta) {
        pautaRepository.save(pauta);
    }

}
