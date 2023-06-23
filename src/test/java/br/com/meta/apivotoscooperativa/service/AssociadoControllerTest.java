package br.com.meta.apivotoscooperativa.service;


import br.com.meta.apivotoscooperativa.controller.AssociadoController;

import br.com.meta.apivotoscooperativa.dto.AssociadoDto;

import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.verify;

import static org.mockito.Mockito.never;

import static org.mockito.Mockito.times;


@WebMvcTest(AssociadoController.class)

class AssociadoControllerTest {


// private final AssociadoService associadoService = Mockito.mock(AssociadoService.class);

// private final AssociadoController associadoController = new AssociadoController(associadoService);


    @Autowired

    private MockMvc mockMvc;


    @MockBean

    private AssociadoService associadoService;


    @Test

    void testPostAssociado_Success() throws Exception {

        String nome = "João";

        String cpf = "12345678910";


        String requestPayload = "{\"nome\": \"" + nome + "\", \"cpf\": \"" + cpf + "\"}";


        mockMvc.perform(MockMvcRequestBuilders.post("/associados")

                        .contentType(MediaType.APPLICATION_JSON)

                        .content(requestPayload))

                .andExpect(MockMvcResultMatchers.status().isCreated())

                .andExpect(MockMvcResultMatchers.content().string("Associado cadastrado com sucesso."));


        verify(associadoService, times(1)).saveAssociado(Mockito.any(AssociadoDto.class));

    }


    @Test

    void testPostAssociado_Exception() throws Exception {

        String nome = "J";

        String cpf = "143413414141321313123212adsad@@313";


        String requestPayload = "{\"nome\": \"" + nome + "\", \"cpf\": \"" + cpf + "\"}";


        mockMvc.perform(MockMvcRequestBuilders.post("/associados")

                        .contentType(MediaType.APPLICATION_JSON)

                        .content(requestPayload))

                .andExpect(MockMvcResultMatchers.status().isBadRequest())

                .andExpect(MockMvcResultMatchers.content().string("O cpf do associado deve ter 11 caracteres"));


        verify(associadoService, never()).saveAssociado(Mockito.any(AssociadoDto.class));

    }



}
