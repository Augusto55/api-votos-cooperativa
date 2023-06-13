package br.com.meta.apivotoscooperativa.service;

import br.com.meta.apivotoscooperativa.exception.InvalidPautaException;
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

    public Pauta acharPorId(Integer id){
        return pautaRepository.getReferenceById(id);
    }
    public void savePauta(Pauta pauta) {
            pautaRepository.save(pauta);
    }

    public void isValidPauta(Pauta pauta) throws InvalidPautaException {
        if (pauta == null) {
            throw new InvalidPautaException("A pauta não pode ser nula.");
        }
        String titulo = pauta.getTitulo();
        String descricao = pauta.getDescricao();

        if (titulo == null || descricao == null) {
            throw new InvalidPautaException("O título ou descrição não podem ser nulos.");
        }

        int tituloLength = titulo.trim().length();
        int descricaoLength = descricao.trim().length();

        if (tituloLength < 2 || tituloLength > 30) {
            throw new InvalidPautaException("O comprimento do título deve ser entre 2 e 30.");
        }

        if (descricaoLength < 2 || descricaoLength > 254) {
            throw new InvalidPautaException("O comprimento da descrição deve ser entre 2 e 254.");
        }
    }


}
