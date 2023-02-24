package br.com.med.voll.api.util.validacoes;

import br.com.med.voll.api.dto.DadosConsultasDto;
import br.com.med.voll.api.exception.medico.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta{


    public void validar(DadosConsultasDto dados) {
        var dadaConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dadaConsulta).toMinutes();

        if(diferencaEmMinutos < 30){
            throw new ValidacaoException("Consulta deve ser agendada com antecedência minima de 30 minutos");
        }
    }
}
