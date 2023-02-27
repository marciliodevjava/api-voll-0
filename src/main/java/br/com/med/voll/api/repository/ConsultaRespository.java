package br.com.med.voll.api.repository;

import br.com.med.voll.api.domain.Medico;
import br.com.med.voll.api.domain.consulta.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ConsultaRespository extends JpaRepository<Consulta, Long> {
    // existsByMedicoInAndData
    Boolean existsByMedico_IdAndData(Long idMedico, LocalDateTime dateTime);

    Boolean existsByPacienteIdAndDataBetween(Long id, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);
    Boolean existsByPaciente_IdAndDataBetween(Long id, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

}
