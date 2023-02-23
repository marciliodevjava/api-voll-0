package br.com.med.voll.api.domain.consulta;

import br.com.med.voll.api.domain.Medico;
import br.com.med.voll.api.domain.MotivoAgendamentoOuCancelamentoEnum;
import br.com.med.voll.api.domain.Paciente;
import br.com.med.voll.api.dto.CancelamentoDetalheConsutaDto;
import br.com.med.voll.api.exception.medico.ValidacaoException;
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
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private MotivoAgendamentoOuCancelamentoEnum status = MotivoAgendamentoOuCancelamentoEnum.CONSULTA_MARCADA;

    public Consulta(Long idMedico, Long idPaciente, LocalDateTime data){
        this.medico = new Medico(idMedico);
        this.paciente = new Paciente(idPaciente);
        this.data = data;
    }

    public boolean cancelar(MotivoAgendamentoOuCancelamentoEnum cancelamento) {
        if(cancelamento.equals(MotivoAgendamentoOuCancelamentoEnum.VIAGEM)){
            System.out.println("Motivo cancelamento autorizado");
        } else if(cancelamento.equals(MotivoAgendamentoOuCancelamentoEnum.MOTIVO_PESSOAIS)){
            System.out.println("Motivo cancelamento autorizado");
        } else if(cancelamento.equals(MotivoAgendamentoOuCancelamentoEnum.DESISTENCIA)){
            System.out.println("Motivo cancelamento autorizado");
        } else if(cancelamento.equals(MotivoAgendamentoOuCancelamentoEnum.CANCELAMENTO)){
            System.out.println("Motivo cancelamento autorizado");
        } else {
            throw new ValidacaoException("Motivo recusado");
        }
        this.status = cancelamento;
        return true;
    }

    public void excluir(MotivoAgendamentoOuCancelamentoEnum cancelamento) {
        this.status = cancelamento;
    }
}
