package br.com.meta.apivotoscooperativa.dto;

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;

public record SessaoVotacaoVoteDto(@NotNull Integer associadoId,
                                   @NotNull @AssertTrue @AssertFalse Boolean voto) {
}
