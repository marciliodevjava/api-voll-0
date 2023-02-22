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
        if(dados != null && !pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Paciente informado não existe.");
        }
        Optional<Medico> medico = medicoRepository.findById(dados.idMedico());
        Optional<Paciente> paciente = pacienteRepository.findById(dados.idPaciente());
        Consulta consulta = consultaRespository.save(new Consulta(medico.get().getId(), paciente.get().getId(), dados.data()));
        return new DadosDetalhamentoConsultaDto(consulta.getId(), medico.get().getId()
                , paciente.get().getId(), dados.data());
    }
}
