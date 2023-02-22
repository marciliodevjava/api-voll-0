package br.com.med.voll.api.dto;

import br.com.med.voll.api.domain.Medico;
import br.com.med.voll.api.domain.Paciente;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsultaDto(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {
}
