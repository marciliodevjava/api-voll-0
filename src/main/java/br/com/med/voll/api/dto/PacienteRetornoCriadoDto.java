package br.com.med.voll.api.dto;

import br.com.med.voll.api.domain.Endereco;
import br.com.med.voll.api.domain.Paciente;

public record PacienteRetornoCriadoDto(String nome, String email, String telefone,String cpf, Endereco endereco) {

    public PacienteRetornoCriadoDto(Paciente paciente){
        this(paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
    }
}
