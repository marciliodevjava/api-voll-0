package br.com.med.voll.api.dto;

import br.com.med.voll.api.domain.Especialidade;
import br.com.med.voll.api.domain.MotivoAgendamentoOuCancelamentoEnum;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosConsultasDto(
        @NotNull(message = "Campo idMedico obrigatório.")
        Long idMedico,
        @NotNull(message = "Campo idPaciente obrigatório.")
        Long idPaciente,
        @NotNull(message = "Campo data obrigatório")
        @Future
        LocalDateTime data,
        @NotNull(message = "Campo especialidade obrigatório")
        Especialidade especialidade,
        @NotNull(message = "Motivo motivo obrigatório")
        MotivoAgendamentoOuCancelamentoEnum motivo) {
}
