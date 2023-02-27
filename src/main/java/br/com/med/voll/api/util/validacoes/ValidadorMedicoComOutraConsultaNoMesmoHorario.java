package br.com.med.voll.api.util.validacoes;

import br.com.med.voll.api.domain.Medico;
import br.com.med.voll.api.dto.DadosConsultasDto;
import br.com.med.voll.api.exception.medico.ValidacaoException;
import br.com.med.voll.api.repository.ConsultaRespository;
import br.com.med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private ConsultaRespository consultaRespository;
    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DadosConsultasDto dados) {
        Boolean medicoPossuiOutraConsultaNoMesmoHorario = consultaRespository.existsByMedico_IdAndData(dados.idMedico(), dados.data());
        if (medicoPossuiOutraConsultaNoMesmoHorario) {
            throw new ValidacaoException("Médico já possui outra consulta agendada nesse horario");
        }
    }
}

