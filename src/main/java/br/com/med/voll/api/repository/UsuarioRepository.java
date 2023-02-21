package br.com.med.voll.api.repository;

import br.com.med.voll.api.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
