package br.com.med.voll.api.util.validacoes;

import br.com.med.voll.api.dto.DadosConsultasDto;
import br.com.med.voll.api.exception.medico.ValidacaoException;
import br.com.med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DadosConsultasDto dados) {
        if (dados.idMedico() == null) {
            return;
        }

        var medicoEstaAtivo = medicoRepository.verificaMedicoAtivo(dados.idMedico());
        if (!medicoEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendanda com médico excluido.");
        }
    }
}
