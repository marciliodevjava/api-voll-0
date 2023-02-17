package br.com.med.voll.api.dto;

import jakarta.validation.Valid;

public record PacienteAtualizarDto(
        Long id,
        String nome,
        String telefone,
        @Valid DadosAtualizaEnderecoDto endereco) {
}
