package br.com.med.voll.api.domain;

import br.com.med.voll.api.dto.PacienteDto;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Entity(name = "paciente")
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    @Embedded
    private Endereco endereco;
    private Boolean ativo;

    public Paciente(PacienteDto dados){
        this.nome = dados.nome();
        this.email = dados.email();
        this.cpf = dados.cpf().replace(".","").replace("-","");
        this.telefone = dados.telefone().replace("(","")
                                        .replace(")","")
                                        .replace("-","")
                                        .replace(" ","");
        this.endereco = new Endereco(dados.endereco());
        this.ativo = true;
    }

    public Paciente(Long id){
        this.id = id;
    }
    public void excluir(){
        this.ativo = false;
    }

    public void ativar() {
        this.ativo = true;
    }
}
