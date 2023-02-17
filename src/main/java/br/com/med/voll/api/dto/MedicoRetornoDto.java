package br.com.med.voll.api.dto;

import br.com.med.voll.api.domain.Endereco;
import br.com.med.voll.api.domain.Especialidade;
import br.com.med.voll.api.domain.Medico;

public record MedicoRetornoDto(String nome, String email, String telefone, String crm, Especialidade especialidade, Endereco endereco) {

    public MedicoRetornoDto(Medico medico){
        this(medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
    }
}
