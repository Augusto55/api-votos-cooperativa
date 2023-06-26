package br.com.meta.apivotoscooperativa.service;

import br.com.meta.apivotoscooperativa.controller.PautaController;
import br.com.meta.apivotoscooperativa.dto.PautaRegisterDto;
import br.com.meta.apivotoscooperativa.model.Pauta;
import br.com.meta.apivotoscooperativa.service.PautaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class PautaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PautaService pautaService;


    @Test
    void testListarPautasSuccess() throws Exception {
        Pauta pauta1 = new Pauta();
        pauta1.setId(1);
        pauta1.setTitulo("Pauta 1");

        Pauta pauta2 = new Pauta();
        pauta2.setId(2);
        pauta2.setTitulo("Pauta 2");

        List<Pauta> pautas = Arrays.asList(pauta1, pauta2);

        Mockito.when(pautaService.listAllPautas()).thenReturn(pautas);

        mockMvc.perform(MockMvcRequestBuilders.get("/pautas"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].titulo").value("Pauta 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].titulo").value("Pauta 2"));

        Mockito.verify(pautaService, Mockito.times(1)).listAllPautas();
    }

    @Test
    void testAdicionarPautaSuccess() throws Exception {

        Pauta pauta = new Pauta();
        pauta.setTitulo("Nova Pauta");
        pauta.setDescricao("Descrição da nova pauta");
        pauta.setId(1);

        when(pautaService.savePauta(Mockito.any(Pauta.class))).thenAnswer(invocation -> {
            Pauta pautaSalva = invocation.getArgument(0);
            pautaSalva.setId(1);
            return pautaSalva;
        });


        mockMvc.perform(MockMvcRequestBuilders.post("/pautas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"titulo\": \"Nova Pauta\", \"descricao\": \"Descrição da nova pauta\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("Pauta adicionada com sucesso.\nId da pauta: 1"));

        Mockito.verify(pautaService, Mockito.times(1)).savePauta(Mockito.any(Pauta.class));
    }

    @Test
    void testAdicionarPautaInvalida() throws Exception {

        String titulo = "Ab";
        String descricao = "Descrição da nova pauta";

        String requestPayload = "{\"titulo\": \"" + titulo + "\", \"descricao\": \"" + descricao + "\"}";


        mockMvc.perform(MockMvcRequestBuilders.post("/pautas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestPayload))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Titulo da pauta deve ter de 3 a 20 caracteres."));


    }

    @Test
    void testAdicionarDescricaoInvalida() throws Exception {

        String titulo = "Nova pauta";
        String descricao = "De";

        String requestPayload = "{\"titulo\": \"" + titulo + "\", \"descricao\": \"" + descricao + "\"}";


        mockMvc.perform(MockMvcRequestBuilders.post("/pautas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestPayload))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Descricao da pauta deve ter de 3 a 20 caracteres."));

    }

}
