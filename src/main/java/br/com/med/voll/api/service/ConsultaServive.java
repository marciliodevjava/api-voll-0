package br.com.med.voll.api.service;

import br.com.med.voll.api.domain.Medico;
import br.com.med.voll.api.domain.Paciente;
import br.com.med.voll.api.domain.consulta.Consulta;
import br.com.med.voll.api.dto.DadosConsultasDto;
import br.com.med.voll.api.dto.DadosDetalhamentoConsultaDto;
import br.com.med.voll.api.dto.DadosDetalhamentoMedico;
import br.com.med.voll.api.exception.medico.ValidacaoException;
import br.com.med.voll.api.repository.ConsultaRespository;
import br.com.med.voll.api.repository.MedicoRepository;
import br.com.med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsultaServive {

    @Autowired
    private ConsultaRespository consultaRespository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public DadosDetalhamentoConsultaDto agendar(DadosConsultasDto dados){
        if(!medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("Médico informado não existe");
        }
        if(dados.idMedico() != null && !pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Paciente informado não existe.");
        }
        Medico medico = this.escolhaMedico(dados);
        Paciente paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        Consulta consulta = consultaRespository.save(new Consulta(medico.getId(), paciente.getId(), dados.data()));
        return new DadosDetalhamentoConsultaDto(consulta.getId(), medico.getId()
                , paciente.getId(), dados.data());
    }

    private Medico escolhaMedico(DadosConsultasDto dados){
        if(dados.idMedico() != null) return medicoRepository.getReferenceById(dados.idMedico());

        if(dados.especialidade() == null) throw new ValidacaoException("Especialidade é obrigatória, quando o medico não for escolhido.");

        return medicoRepository.escolherMedicoAlatorio(dados.especialidade(), dados.data());
    }
}
