package br.com.med.voll.api.util.validacoes;

import br.com.med.voll.api.dto.DadosConsultasDto;
import br.com.med.voll.api.exception.medico.ValidacaoException;
import br.com.med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaPacienteAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DadosConsultasDto dados) {
        var pacienteAtivo = pacienteRepository.findAtivoById(dados.idPaciente());

        if (!pacienteAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com paciente excluido");
        }
    }
}
