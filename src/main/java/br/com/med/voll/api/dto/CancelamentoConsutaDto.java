package br.com.med.voll.api.dto;

import br.com.med.voll.api.domain.MotivoAgendamentoOuCancelamentoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CancelamentoConsutaDto(
        @NotNull(message = "Id é obrigatório")
        Long idConsulta,
        @NotNull(message = "Motivo do cancelamento está incorreto.")
        MotivoAgendamentoOuCancelamentoEnum cancelamento) {
}
