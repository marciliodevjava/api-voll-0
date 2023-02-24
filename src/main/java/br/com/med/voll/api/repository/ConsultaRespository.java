package br.com.med.voll.api.repository;

import br.com.med.voll.api.domain.consulta.Consulta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConsultaRespository extends JpaRepository<Consulta, Long> {
   // existsByMedicoInAndData
    Boolean existsByMedicoAndData(Long idMedico, LocalDateTime dateTime);

    Boolean existsByPacienteIdAndDataBetween(Long id, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);
}
