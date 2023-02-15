package br.com.med.voll.api.dto;

import br.com.med.voll.api.domain.Especialidade;
import br.com.med.voll.api.domain.Medico;

public record ListagemMedicosDto(String nome, String email, String crm, Especialidade especialidade) {

    public ListagemMedicosDto(Medico medico){
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
