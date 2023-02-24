package br.com.med.voll.api.dto;

import jakarta.validation.constraints.NotNull;

public record LoginDto(
        @NotNull(message = "Informe o login.")
        String login,
        @NotNull(message = "Informe a senha.")
        String senha) {

}
