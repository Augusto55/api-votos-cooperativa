package br.com.meta.apivotoscooperativa.service;

import br.com.meta.apivotoscooperativa.controller.AssociadoController;
import br.com.meta.apivotoscooperativa.dto.AssociadoDto;
import br.com.meta.apivotoscooperativa.model.Associado;
import jakarta.validation.Valid;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AssociadoControllerTest {

    private final AssociadoService associadoService = Mockito.mock(AssociadoService.class);
    private final AssociadoController associadoController = new AssociadoController(associadoService);

    @Test
    void testPostAssociado_Success() {
        Associado associado = new Associado();
        associado.setNome("João");
        associado.setCpf("12345678910");

        AssociadoDto associadoDto = new AssociadoDto(associado.getId(), associado.getNome(), associado.getCpf());
        ResponseEntity<?> response = associadoController.postAssociado(associadoDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Associado cadastrado com sucesso.", response.getBody());
        verify(associadoService, times(1)).saveAssociado(associadoDto);
    }

    @Test
    void testPostAssociado_Exception() {
        String nome = "João";
        String cpf = "143413414141321313123212adsad@@313";



        AssociadoDto associadoDto = new AssociadoDto(null, nome, cpf);
        ResponseEntity<?> response = associadoController.postAssociado(associadoDto);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Erro ao cadastrar Associado.", response.getBody());
        verify(associadoService, never()).saveAssociado(associadoDto);
    }


}
