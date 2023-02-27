package br.com.med.voll.api.repository;

import br.com.med.voll.api.domain.consulta.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConsultaRespository extends JpaRepository<Consulta, Long> {
    // existsByMedicoInAndData
    @Query("""
            SELECT id FROM consulta
            where medico = :idMedico
            AND
            data = :dateTime
            """)
    Boolean consultaExiste(Long idMedico, LocalDateTime dateTime);

    Boolean existsByPacienteIdAndDataBetween(Long id, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);
}
