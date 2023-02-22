package br.com.med.voll.api.domain.consulta;

import br.com.med.voll.api.domain.Medico;
import br.com.med.voll.api.domain.Paciente;
import br.com.med.voll.api.dto.DadosConsultasDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "consultas")
@Entity(name = "consulta")
@EqualsAndHashCode(of = "id")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private LocalDateTime data;

    public Consulta(Long idMedico, Long idPaciente, LocalDateTime data){
        this.medico = new Medico(idMedico);
        this.paciente = new Paciente(idPaciente);
        this.data = data;
    }

}
