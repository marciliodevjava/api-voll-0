package br.com.med.voll.api.domain;

import br.com.med.voll.api.dto.EnderecoDto;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Endereco(EnderecoDto endereco) {
        this.logradouro = endereco.logradouro();
        this.bairro = endereco.bairro();
        this.cep = endereco.cep();
        this.complemento = endereco.complemento();
        this.numero = endereco.numero();
        this.cidade = endereco.cidade();
        this.uf = endereco.uf();
    }

    public void atualizarInformacoes(Endereco endereco) {
        if (endereco.logradouro != null){
            this.logradouro = endereco.getLogradouro();
        }
        if(endereco.bairro != null){
           this.bairro = endereco.getBairro();
        }
        if (endereco.cep != null){
            this.cep = endereco.getCep();
        }
        if (endereco.complemento != null){
            this.complemento = endereco.getComplemento();
        }
        if(endereco.numero != null){
            this.numero = endereco.getNumero();
        }
        if(endereco.cidade != null){
            this.cidade = endereco.getCidade();
        }
        if (endereco.uf != null){
            this.uf = endereco.getUf();
        }
    }
}
