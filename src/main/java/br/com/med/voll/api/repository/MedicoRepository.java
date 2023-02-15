package br.com.med.voll.api.repository;

import br.com.med.voll.api.domain.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
