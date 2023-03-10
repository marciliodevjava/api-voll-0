package br.com.med.voll.api.dto;

import br.com.med.voll.api.domain.Endereco;
import br.com.med.voll.api.domain.Especialidade;
import br.com.med.voll.api.domain.Medico;

public record DadosDetalhamentoMedico(Long id,
                                      String nome,
                                      String email,
                                      String crm,
                                      String telefone,
                                      Especialidade especialidade,
                                      EnderecoDto endereco) {

    public DadosDetalhamentoMedico(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm()
                , medico.getTelefone(), medico.getEspecialidade(), new EnderecoDto(medico.getEndereco()));
    }
}
