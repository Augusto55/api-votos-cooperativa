package br.com.meta.apivotoscooperativa.service;

import br.com.meta.apivotoscooperativa.commons.enums;
import br.com.meta.apivotoscooperativa.model.Pauta;
import br.com.meta.apivotoscooperativa.repository.PautaRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class PautaServiceTest {

    @Mock
    private PautaRepository pautaRepository;

    @InjectMocks
    private PautaService pautaService;


    @Test
    public void testListAllPautas() {
        Pauta pauta1 = new Pauta(1, "Titulo1", "Descricao1", enums.PautaStatus.VOTO_ABERTO);
        Pauta pauta2 = new Pauta(2, "Titulo2", "Descricao2", enums.PautaStatus.VOTO_ABERTO);
        List<Pauta> pautas = Arrays.asList(pauta1, pauta2);

        Mockito.when(pautaRepository.findAll()).thenReturn(pautas);

        var returnedPautas = pautaService.listAllPautas();

        Assertions.assertEquals(pautas, returnedPautas);
        Assertions.assertNotNull(returnedPautas);

    }
}
