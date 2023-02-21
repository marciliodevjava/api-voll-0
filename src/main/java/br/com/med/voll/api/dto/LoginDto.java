package br.com.med.voll.api.dto;

import br.com.med.voll.api.domain.usuario.Usuario;
import jakarta.validation.constraints.NotNull;

public record LoginDto(
        @NotNull(message = "Informe o login.")
        String login,
        @NotNull(message = "Informe a senha.")
        String senha) {

}
