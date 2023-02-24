package br.com.med.voll.api.util.validacoes;

import br.com.med.voll.api.dto.DadosConsultasDto;

public interface ValidadorAgendamentoDeConsulta {

    void validar(DadosConsultasDto dados);
}
