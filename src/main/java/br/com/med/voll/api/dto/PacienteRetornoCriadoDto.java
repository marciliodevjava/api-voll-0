package br.com.med.voll.api.dto;

import br.com.med.voll.api.domain.Endereco;
import br.com.med.voll.api.domain.Paciente;
import jakarta.validation.constraints.Pattern;

public record PacienteRetornoCriadoDto(Long id, String nome, String email, String telefone,String cpf, Endereco endereco) {

    public PacienteRetornoCriadoDto(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
    }
}
