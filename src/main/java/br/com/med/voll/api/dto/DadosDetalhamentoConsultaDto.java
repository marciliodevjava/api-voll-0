package br.com.med.voll.api.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsultaDto(
        @NotNull(message = "id está incorreto.")
        Long id,
        @NotNull(message = "idMedico está incorreto.")
        Long idMedico,
        @NotNull(message = "idPaciente está incorreto.")
        Long idPaciente,
        @NotNull(message = "data está incorreto.")
        LocalDateTime data) {
}
