package br.com.med.voll.api.dto;

public record PacienteDto(String nome, String email, String telefone, String cpf, EnderecoDto endereco) {
}
