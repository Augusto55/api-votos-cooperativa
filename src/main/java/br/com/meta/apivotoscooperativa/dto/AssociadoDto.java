package br.com.meta.apivotoscooperativa.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AssociadoDto(Integer id,
                           @NotNull(message = "O nome do associado não pode ser nulo")
                           @Size(min = 3, max = 30, message = "O nome do associado deve ter entre 3 e 30 caracteres") String nome, @NotNull(message = "O cpf do associado não pode ser nulo") String cpf) {

}
