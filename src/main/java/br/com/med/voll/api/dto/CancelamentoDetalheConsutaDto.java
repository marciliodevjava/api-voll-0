package br.com.med.voll.api.dto;

import br.com.med.voll.api.domain.MotivoAgendamentoOuCancelamentoEnum;
import br.com.med.voll.api.domain.consulta.Consulta;

public record CancelamentoDetalheConsutaDto(Long id, MotivoAgendamentoOuCancelamentoEnum motivo) {

    public CancelamentoDetalheConsutaDto(Consulta consulta) {
        this(consulta.getId(), consulta.getStatus());
    }
}
