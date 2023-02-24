package br.com.med.voll.api.dto;

import jakarta.validation.constraints.NotNull;

public record MedicoAtualizarDto(
        @NotNull(message = "Id é obrigatório.")
        Long id,
        String nome,
        String telefone,
        EnderecoDto enderecoDto) {
}
