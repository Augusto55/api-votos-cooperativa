package br.com.meta.apivotoscooperativa.service;
import br.com.meta.apivotoscooperativa.exception.PautaNotFoundException;
import br.com.meta.apivotoscooperativa.model.Associado;
import br.com.meta.apivotoscooperativa.model.SessaoVotacao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.Duration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SessaoVotacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SessaoVotacaoService sessaoVotacaoService;

    @MockBean
    private AssociadoService associadoService;

    @Test
    void testListarSessoes_Success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/sessao-votacao"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(sessaoVotacaoService, times(1)).listAllSessoes();
    }

    @Test
    void testOpenSessao_PautaNotFound() throws Exception {
        when(sessaoVotacaoService.findById(1)).thenThrow(new PautaNotFoundException("SessaoVotacao com id 1 não foi encontrada."));

        mockMvc.perform(MockMvcRequestBuilders.put("/sessao-votacao/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("SessaoVotacao com id 1 não foi encontrada."));

        verify(sessaoVotacaoService, times(1)).findById(1);
        verify(sessaoVotacaoService, never()).updateSessao(anyInt(), any());
    }

    @Test
    void testVoteInSession_InvalidAssociado() throws Exception {
        SessaoVotacao sessao = new SessaoVotacao();
        sessao.setId(1);
        sessao.setIsOpen();
        sessao.setDuration(Duration.ofMinutes(60));
        sessao.setPautaId(1);

        when(sessaoVotacaoService.findById(1)).thenReturn(sessao);
        when(associadoService.findById(1)).thenReturn(null);

        String requestPayload = "{\"associadoId\": 1, \"voto\": true}";

        mockMvc.perform(MockMvcRequestBuilders.post("/sessao-votacao/1/votar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestPayload))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("O associado não está cadastrado"));

        verify(sessaoVotacaoService, times(1)).findById(1);
        verify(associadoService, times(1)).findById(1);
        verify(associadoService, never()).isValidCPF(any());
        verify(sessaoVotacaoService, never()).isSessaoExpired(any());
        verify(sessaoVotacaoService, never()).isSessaoOpen(any());
        verify(sessaoVotacaoService, never()).addVoto(any(), anyBoolean());
    }

    @Test
    void testVoteInSession_NotEligibleAssociado() throws Exception {
        SessaoVotacao sessao = new SessaoVotacao();
        sessao.setId(1);
        sessao.setIsOpen();
        sessao.setDuration(Duration.ofMinutes(60));
        sessao.setPautaId(1);

        Associado associado = new Associado();
        associado.setId(1);
        associado.setCpf("12345678910");

        when(sessaoVotacaoService.findById(1)).thenReturn(sessao);
        when(associadoService.findById(1)).thenReturn(associado);
        when(associadoService.isValidCPF("12345678910")).thenReturn(false);

        String requestPayload = "{\"associadoId\": 1, \"voto\": true}";

        mockMvc.perform(MockMvcRequestBuilders.post("/sessao-votacao/1/votar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestPayload))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Associado null não está apto para votar."));

        verify(sessaoVotacaoService, times(1)).findById(1);
        verify(associadoService, times(1)).findById(1);
        verify(associadoService, times(1)).isValidCPF("12345678910");
        verify(sessaoVotacaoService, never()).isSessaoExpired(any());
        verify(sessaoVotacaoService, never()).isSessaoOpen(any());
        verify(sessaoVotacaoService, never()).addVoto(any(), anyBoolean());
    }

    @Test
    void testVoteInSession_SessaoExpired() throws Exception {
        SessaoVotacao sessao = new SessaoVotacao();
        sessao.setId(1);
        sessao.setDuration(Duration.ofMinutes(60));
        sessao.setPautaId(1);

        Associado associado = new Associado();
        associado.setId(1);
        associado.setCpf("12345678910");

        when(sessaoVotacaoService.findById(1)).thenReturn(sessao);
        when(associadoService.findById(1)).thenReturn(associado);
        when(associadoService.isValidCPF("12345678910")).thenReturn(true);
        when(sessaoVotacaoService.isSessaoExpired(sessao)).thenReturn(true);

        String requestPayload = "{\"associadoId\": 1, \"voto\": true}";

        mockMvc.perform(MockMvcRequestBuilders.post("/sessao-votacao/1/votar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestPayload))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("SessaoVotacao com id 1 está fechada."));

        verify(sessaoVotacaoService, times(1)).findById(1);
        verify(associadoService, times(1)).findById(1);
        verify(associadoService, times(1)).isValidCPF("12345678910");
        verify(sessaoVotacaoService, times(1)).isSessaoExpired(sessao);
        verify(sessaoVotacaoService, never()).isSessaoOpen(any());
        verify(sessaoVotacaoService, never()).addVoto(any(), anyBoolean());
    }

    @Test
    void testShowResult_Success() throws Exception {
        SessaoVotacao sessao = new SessaoVotacao();
        sessao.setId(1);
        sessao.setPautaId(1);
        sessao.setVotosSim();
        sessao.setVotosSim();
        sessao.setVotosSim();
        sessao.setVotosNao();
        sessao.setVotosNao();
        sessao.setVotosTotal();
        sessao.setVotosTotal();
        sessao.setVotosTotal();
        sessao.setVotosTotal();
        sessao.setVotosTotal();

        when(sessaoVotacaoService.findById(1)).thenReturn(sessao);
        when(sessaoVotacaoService.showResult(sessao)).thenReturn("Result");

        mockMvc.perform(MockMvcRequestBuilders.put("/sessao-votacao/1/resultado"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Result"));

        verify(sessaoVotacaoService, times(1)).findById(1);
        verify(sessaoVotacaoService, times(1)).showResult(sessao);
    }

    @Test
    void testShowResult_PautaNotFound() throws Exception {
        when(sessaoVotacaoService.findById(1)).thenThrow(new PautaNotFoundException("SessaoVotacao com id 1 não foi encontrada."));

        mockMvc.perform(MockMvcRequestBuilders.put("/sessao-votacao/1/resultado"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("SessaoVotacao com id 1 não foi encontrada."));

        verify(sessaoVotacaoService, times(1)).findById(1);
        verify(sessaoVotacaoService, never()).showResult(any());
    }
}
