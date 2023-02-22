package br.com.med.voll.api.repository;

import br.com.med.voll.api.domain.consulta.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRespository extends JpaRepository<Consulta, Long> {
}