package br.com.med.voll.api.dto;

import br.com.med.voll.api.domain.Especialidade;
import br.com.med.voll.api.domain.MotivoAgendamentoOuCancelamentoEnum;
import br.com.med.voll.api.domain.consulta.Consulta;

import java.time.LocalDateTime;

public record ListagemConsultaDto(Long id,
                                  Long idMedico,
                                  Long idPaciente,
                                  MotivoAgendamentoOuCancelamentoEnum motivo,
                                  LocalDateTime data) {

    public ListagemConsultaDto(Consulta consulta){
        this(consulta.getId(),
                consulta.getMedico().getId(),
                consulta.getMedico().getId(),
                consulta.getStatus(),
                consulta.getData());
    }
}
