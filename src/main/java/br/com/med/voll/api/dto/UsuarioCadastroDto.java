package br.com.med.voll.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UsuarioCadastroDto(
        @NotBlank(message = "Informe o login do usuario")
                @Email
        String login,
        @NotBlank(message = "Informe a senha do usuario, de no minimo 8 digitos a 64 digitos")
        @Pattern(regexp = "\\d{8,24}")
        String senha)
    {
}
