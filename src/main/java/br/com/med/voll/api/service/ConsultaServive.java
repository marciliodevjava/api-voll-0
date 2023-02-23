package br.com.med.voll.api.service;

import br.com.med.voll.api.domain.Medico;
import br.com.med.voll.api.domain.Paciente;
import br.com.med.voll.api.domain.consulta.Consulta;
import br.com.med.voll.api.dto.*;
import br.com.med.voll.api.exception.medico.ValidacaoException;
import br.com.med.voll.api.repository.ConsultaRespository;
import br.com.med.voll.api.repository.MedicoRepository;
import br.com.med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ConsultaServive {

    @Autowired
    private ConsultaRespository consultaRespository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public DadosDetalhamentoConsultaDto agendar(DadosConsultasDto dados) {
        if (!medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("Médico informado não existe");
        }
        if (dados.idMedico() != null && !pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("Paciente informado não existe.");
        }
        Medico medico = this.escolhaMedico(dados);
        Paciente paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        Consulta consulta = consultaRespository.save(new Consulta(medico.getId(), paciente.getId(), dados.data()));
        return new DadosDetalhamentoConsultaDto(consulta.getId(), medico.getId()
                , paciente.getId(), dados.data());
    }

    private Medico escolhaMedico(DadosConsultasDto dados) {
        if (dados.idMedico() != null) return medicoRepository.getReferenceById(dados.idMedico());

        if (dados.especialidade() == null)
            throw new ValidacaoException("Especialidade é obrigatória, quando o medico não for escolhido.");

        return medicoRepository.escolherMedicoAlatorio(dados.especialidade(), dados.data());
    }

    public CancelamentoDetalheConsutaDto cancelaMentoConsulta(CancelamentoConsutaDto cancelamentoConsutaDto) {
        if (!consultaRespository.existsById(cancelamentoConsutaDto.idConsulta())) {
            throw new ValidacaoException("Não é possivel realizar o cancelamento, pois a consulta não existe.");
        }
        Consulta consulta = consultaRespository.getReferenceById(cancelamentoConsutaDto.idConsulta());
        boolean consultaCancelada = consulta.cancelar(cancelamentoConsutaDto.cancelamento());
        if (consultaCancelada) {
            System.out.println("Consulta autorizada o cancelamento");
        } else {
            throw new ValidacaoException("Cancelamento não Autorizado.");
        }
        consulta.excluir(cancelamentoConsutaDto.cancelamento());

        return new CancelamentoDetalheConsutaDto(consulta);
    }

    public Page<ListagemConsultaDto> listaPaginada(Pageable paginacao) {
        Page<ListagemConsultaDto> paginas = consultaRespository.findAll(paginacao).map(ListagemConsultaDto::new);
        return paginas;
    }
}
