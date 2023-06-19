package br.com.meta.apivotoscooperativa.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PautaRegisterDto(@NotNull @Size(min = 3, max = 20) String titulo,
                               @NotNull @Size(min = 8, max = 60) String descricao) {
}
