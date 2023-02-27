package br.com.med.voll.api.util.validacoes;

import br.com.med.voll.api.dto.DadosConsultasDto;
import br.com.med.voll.api.exception.medico.ValidacaoException;
import br.com.med.voll.api.repository.ConsultaRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private ConsultaRespository consultaRespository;

    public void validar(DadosConsultasDto dadosConsultasDto) {

        LocalDateTime primeiroHorario = dadosConsultasDto.data().withHour(7);
        LocalDateTime ultimoHorario = dadosConsultasDto.data().withHour(18);
        Boolean pacientePossuiOutraConsulta = consultaRespository.existsByPaciente_IdAndDataBetween(dadosConsultasDto.idPaciente(),
                primeiroHorario,
                ultimoHorario);

        if (pacientePossuiOutraConsulta) {
            throw new ValidacaoException("Paciente já possui uma consulta agendada nesse dia.");
        }
    }

}
