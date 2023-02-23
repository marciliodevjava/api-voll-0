package br.com.med.voll.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoDto(
        @NotBlank(message = "Logradouro está incorreto.")
        String logradouro,
        @NotBlank(message = "Bairro está incorreto.")
        String bairro,
        @NotBlank(message = "Cep está incorreto.")
                @Pattern(regexp = "\\d{5}\\-?\\d{3}")
        String cep,
        @NotBlank(message = "Cidade está incorreto.")
        String cidade,
        @NotBlank(message = "Uf está incorreto.")
        String uf,
        String numero,
        String complemento) {
}
