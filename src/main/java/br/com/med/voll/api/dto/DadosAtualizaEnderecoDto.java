package br.com.med.voll.api.dto;

import br.com.med.voll.api.domain.Paciente;

public record DadosAtualizaEnderecoDto(
        Long id,
        String nome,
        String email,
        String cpf) {

    public DadosAtualizaEnderecoDto(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
