package br.com.med.voll.api.dto;

import br.com.med.voll.api.domain.Endereco;
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
        public EnderecoDto(Endereco endereco) {
                this(endereco.getLogradouro(), endereco.getBairro(), endereco.getCep(), endereco.getCidade(), endereco.getUf(), endereco.getNumero(), endereco.getComplemento());
        }
}
