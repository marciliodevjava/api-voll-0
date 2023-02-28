package br.com.med.voll.api.repository;

import br.com.med.voll.api.domain.Endereco;
import br.com.med.voll.api.domain.Especialidade;
import br.com.med.voll.api.domain.Medico;
import br.com.med.voll.api.domain.Paciente;
import br.com.med.voll.api.domain.consulta.Consulta;
import br.com.med.voll.api.dto.EnderecoDto;
import br.com.med.voll.api.dto.MedicoDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private ConsultaRespository consultaRespository;

    @Test
    void findAllByAtivoTrue() {
    }

    @Test
    @DisplayName("Deve Devolver Null quando o médico não está disponivel na data")
    void escolherMedicoAlatorioCenarioUm() {
        LocalDateTime proximaSegundaAsDez = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        Endereco enderecoCadastroMedico = new Endereco("Rua 1", "Santa Lucia", "71815460"
                                                       , "Casa Branca", "2", "Jardins do Fotro", "JF");
        Medico medicoCadastro = new Medico("Patrick afonso padilha", "patrick-afonso@teste.com.br", "61983625689"
                                           , "362956", Especialidade.GINECOLOGIA, enderecoCadastroMedico);
        Endereco enderecoCadastroPaciente = new Endereco("Rua 2", "Candagoandia", "72725460"
                , "Casa Amarela", "12", "Brasilia", "DF");
        Paciente pacienteCadastro = new Paciente("Luiza Alessandra", "luiza-ale@teste.com.br", "05046259877"
                                                 , "61983562659", enderecoCadastroPaciente);
        Consulta consultaCadastro = new Consulta(null, medicoCadastro.getId(), pacienteCadastro.getId(), proximaSegundaAsDez);

        Medico medicoLivre = medicoRepository.escolherMedicoAlatorio(Especialidade.CARDIOLOGIA, proximaSegundaAsDez);

        assertThat(medicoLivre).isNull();
    }

    @Test
    @DisplayName("Deve Devolver True quando o médico não está disponivel na data")
    void escolherMedicoAlatorioCenarioDois() {
    }
    @Test
    void verificaMedicoAtivo() {
    }

}