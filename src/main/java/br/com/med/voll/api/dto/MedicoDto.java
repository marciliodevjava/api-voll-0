package br.com.med.voll.api.dto;

import br.com.med.voll.api.domain.Especialidade;

public record MedicoDto(String nome, String email, String crm, Especialidade especialidade, EnderecoDto endereco) {
}
