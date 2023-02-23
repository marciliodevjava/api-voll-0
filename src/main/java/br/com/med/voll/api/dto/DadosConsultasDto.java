package br.com.med.voll.api.dto;

import br.com.med.voll.api.domain.Especialidade;
import br.com.med.voll.api.domain.MotivoAgendamentoOuCancelamentoEnum;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosConsultasDto(
        Long idMedico,
        @NotNull(message = "Capo paciente obrigatório.")
        Long idPaciente,
        @NotNull(message = "Campo data obrigatório")
        @Future
        LocalDateTime data,
        Especialidade especialidade,
        @NotNull(message = "Motivo data obrigatório")
        MotivoAgendamentoOuCancelamentoEnum motivo) {
}
