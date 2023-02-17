package br.com.med.voll.api.dto;

import br.com.med.voll.api.domain.Paciente;

public record PacienteRetornoDto(Long id, String nome, String email, String cpf, String telefone) {

    public PacienteRetornoDto(Paciente dados){
        this(dados.getId(), dados.getNome(), dados.getEmail(), dados.getCpf(), dados.getTelefone());
    }
}
