package br.com.med.voll.api.repository;

import br.com.med.voll.api.domain.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Page<Paciente> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
    select p.ativo 
    from paciente p 
    where p.id = :idPaciente""")
    Boolean findAtivoById(Long idPaciente);
}
