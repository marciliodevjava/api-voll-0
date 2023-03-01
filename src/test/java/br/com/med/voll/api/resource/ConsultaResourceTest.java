package br.com.med.voll.api.resource;

import br.com.med.voll.api.domain.Especialidade;
import br.com.med.voll.api.domain.MotivoAgendamentoOuCancelamentoEnum;
import br.com.med.voll.api.dto.DadosConsultasDto;
import br.com.med.voll.api.dto.DadosDetalhamentoConsultaDto;
import br.com.med.voll.api.service.ConsultaServive;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<DadosConsultasDto> dadosConsultasDtoJson;

    @Autowired
    private JacksonTester<DadosDetalhamentoConsultaDto> dadosDetalhamentoConsultaDtoJson;

    @MockBean
    private ConsultaServive consultaServive;


    @Test
    @DisplayName("Testando endpoint Agendar - Devolve código HTTP 400 - Quando as informações estão invalidas")
    @WithMockUser()
    void agendarCenarioUm400() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(post("/consultas/marcar"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Testando endpoint Agendar - Devolve código HTTP 403 - Quando as informações estão invalidas")
    void agendarCenarioUm403() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(post("/consultas/marcar"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.FORBIDDEN.value());
    }

    @Test
    @DisplayName("Testando endpoint Agendar - Devolve código HTTP 200 - Quando as informações estão validas")
    @WithMockUser()
    void agendarCenario200() throws Exception {

        var data = LocalDateTime.now().plusHours(1);
        var especialidade = Especialidade.CARDIOLOGIA;

        var dadosDetalhamento = new DadosDetalhamentoConsultaDto(null, 1L, 1L, data);
        when(consultaServive.agendar(any())).thenReturn(dadosDetalhamento);

        var motivo = MotivoAgendamentoOuCancelamentoEnum.CONSULTA_MARCADA;
        MockHttpServletResponse response = mockMvc.perform(post("/consultas/marcar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosConsultasDtoJson.write(
                                new DadosConsultasDto(1L, 1L, data, especialidade, motivo)
                        ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = dadosDetalhamentoConsultaDtoJson.write(
                dadosDetalhamento
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}