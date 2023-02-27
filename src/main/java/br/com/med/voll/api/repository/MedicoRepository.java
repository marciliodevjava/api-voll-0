package br.com.med.voll.api.repository;

import br.com.med.voll.api.domain.Especialidade;
import br.com.med.voll.api.domain.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    @Query("SELECT m FROM medico m WHERE m.ativo = true and m.especialidade = ?1 and m.id not in(SELECT c.medico.id from consulta c WHERE c.data = ?2) ORDER BY rand() limit 1")
    Medico escolherMedicoAlatorio(Especialidade especialidade, LocalDateTime data);

    @Query("""
            SELECT m.ativo
            FROM medico m
            WHERE m.id = :idMedico
            """)
    Boolean verificaMedicoAtivo(Long idMedico);
}
