package br.com.meta.apivotoscooperativa.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PautaRegisterDto(@NotNull(message = "Titulo da pauta  não pode ser nulo") @Size(min = 3, max = 20, message = "Titulo da pauta deve ter de 3 a 20 caracteres.") String titulo,
                               @NotNull(message = "Descricao da pauta não pode ser nula") @Size(min = 8, max = 60, message = "Descricao da pauta deve ter de 3 a 20 caracteres.") String descricao) {
}
