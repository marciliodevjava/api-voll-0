package br.com.med.voll.api.domain;

import br.com.med.voll.api.dto.MedicoDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "medicos")
@Entity(name = "medico")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;

    public Medico(MedicoDto dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }
}
