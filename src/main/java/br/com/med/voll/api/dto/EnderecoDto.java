package br.com.med.voll.api.dto;

public record EnderecoDto(String logradouro, String bairro, String cep, String cidade, String uf, String numero, String complemento) {
}
