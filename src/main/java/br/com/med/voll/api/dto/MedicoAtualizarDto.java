package br.com.med.voll.api.dto;

import br.com.med.voll.api.domain.Medico;
import jakarta.validation.constraints.NotNull;

public record MedicoAtualizarDto(
        @NotNull(message = "Id é obrigatório.")
        Long id,
        String nome,
        String telefone,
        EnderecoDto enderecoDto) {
}
