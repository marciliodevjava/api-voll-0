package br.com.med.voll.api.resource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class ConsultaResourceTest {

    @Autowired
    private MockMvc mockMvc;
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
}