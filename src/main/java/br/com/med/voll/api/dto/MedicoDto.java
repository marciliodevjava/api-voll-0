package br.com.med.voll.api.dto;

import br.com.med.voll.api.domain.Especialidade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record MedicoDto(
        @NotBlank(message = "Nome está incorreto.")
        String nome,
        @NotBlank
        @Email(message = "E-mail está incorreto.")
        String email,
        @NotBlank
        String telefone,
        @NotBlank(message = "E-mail está incorreto.")
                @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull(message = "Especialidade está incorreto.")
        Especialidade especialidade,
        @NotNull @Valid
        EnderecoDto endereco) {
}
