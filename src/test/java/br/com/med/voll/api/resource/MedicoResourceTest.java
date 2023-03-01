package br.com.med.voll.api.resource;

import br.com.med.voll.api.dto.DadosDetalhamentoMedico;
import br.com.med.voll.api.dto.MedicoDto;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class MedicoResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<MedicoDto> medicoDtoJson;

    @Autowired
    private JacksonTester<DadosDetalhamentoMedico> detalhamentoMeicoDtoJson;

    @MockBean
    private MedicoResource medicoResource;

    @Test
    @DisplayName("Testando endpoint cadastar medico - Devolve código HTTP - 400")
    @WithMockUser
    void cadastrar() throws Exception{
        MockHttpServletResponse response = mockMvc.perform(post("/medico/cadastro")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}