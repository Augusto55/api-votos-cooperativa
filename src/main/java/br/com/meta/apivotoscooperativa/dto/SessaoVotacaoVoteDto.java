package br.com.meta.apivotoscooperativa.dto;

import jakarta.validation.constraints.NotNull;

public record SessaoVotacaoVoteDto(@NotNull Integer associadoId,
                                   @NotNull(message = "voto nao pode ser nulo")  Boolean voto) {
}
