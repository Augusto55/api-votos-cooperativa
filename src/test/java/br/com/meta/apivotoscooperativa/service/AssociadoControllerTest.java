package br.com.meta.apivotoscooperativa.service;


import br.com.meta.apivotoscooperativa.controller.AssociadoController;

import br.com.meta.apivotoscooperativa.dto.AssociadoDto;

import br.com.meta.apivotoscooperativa.model.Associado;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.context.SpringBootTest;
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


@SpringBootTest
@AutoConfigureMockMvc
class AssociadoControllerTest {



    @Autowired

    private MockMvc mockMvc;


    @MockBean

    private AssociadoService associadoService;


    @Test

    void testPostAssociadoSuccess() throws Exception {

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

    void testPostAssociadoNomeInvalid() throws Exception {

        String nome = "J";

        String cpf = "123.456.789-10";


        String requestPayload = "{\"nome\": \"" + nome + "\", \"cpf\": \"" + cpf + "\"}";


        mockMvc.perform(MockMvcRequestBuilders.post("/associados")

                        .contentType(MediaType.APPLICATION_JSON)

                        .content(requestPayload))

                .andExpect(MockMvcResultMatchers.status().isBadRequest())

                .andExpect(MockMvcResultMatchers.content().string("O nome do associado deve ter entre 3 e 30 caracteres"));


        verify(associadoService, never()).saveAssociado(Mockito.any(AssociadoDto.class));

    }

//    @Test
//
//    void testPostAssociadoCpfInvalido() throws Exception {
//
//        String nome = "Joao";
//        String cpf = "adsad@@313";
//
//        AssociadoDto associadoDto = new AssociadoDto(1, nome, cpf);
//
//
//        //String requestPayload = "{\"nome\": \"" + associadoDto.nome() + "\", \"cpf\": \"" + associadoDto.cpf() + "\"}";
//
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/associados")
//
//                        .contentType(MediaType.APPLICATION_JSON)
//
//                        .content(String.valueOf(associadoDto)))
//
//                .andExpect(MockMvcResultMatchers.status().isBadRequest())
//
//                .andExpect(MockMvcResultMatchers.content().string("O nome do associado deve ter entre 3 e 30 caracteres"));
//
//
//        verify(associadoService, never()).saveAssociado(Mockito.any(AssociadoDto.class));
//
//    }




}
