package br.com.med.voll.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PacienteDto(
        @NotBlank(message = "Nome está incorreto.")
        String nome,
        @NotBlank
        @Email(message = "E-mail está incorreto.")
        String email,
        @NotBlank(message = "Telefone está incorreto.")
        String telefone,
        @NotBlank(message = "Cpf está incorreto.")
                @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}")
        String cpf,
        @NotNull @Valid
        EnderecoDto endereco) {
}
