package br.com.med.voll.api.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosConsultasDto(
        Long idMedico,
        @NotNull(message = "Capo paciente obrigatório.")
        Long idPaciente,
        @NotNull(message = "Campo data obrigatório")
        @Future
        LocalDateTime data) {
}
