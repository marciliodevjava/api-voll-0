package br.com.med.voll.api.resource;

import br.com.med.voll.api.domain.Especialidade;
import br.com.med.voll.api.domain.Medico;
import br.com.med.voll.api.dto.DadosDetalhamentoMedico;
import br.com.med.voll.api.dto.EnderecoDto;
import br.com.med.voll.api.dto.MedicoDto;
import br.com.med.voll.api.repository.MedicoRepository;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


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
    private MedicoRepository medicoRepository;

    @Test
    @DisplayName("Testando endpoint cadastar medico - Devolve código HTTP - 400")
    @WithMockUser
    public void cadastrar400() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(post("/medico/cadastro")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Testando endpoint cadastrar medico - Devolve codigo HTTP 403")
    public void cadastrar403() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(post("/medico/cadastro")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.FORBIDDEN.value());
    }

    @Test
    @DisplayName("Testando endpoint cadastar medico = Devolve codigo HTTP-201")
    @WithMockUser()
    public void cadastar201() throws Exception {

        EnderecoDto endereco = new EnderecoDto("Rua 2", "Santa Luzia", "72815460", "Portao marron", "12", "Luziania", "GO");

        MedicoDto medico = new MedicoDto("Saulo Marques Rodrigues", "saulo-rodrigues@teste.com.br", "61983625948"
                , "23659", Especialidade.CARDIOLOGIA, endereco);
        DadosDetalhamentoMedico detalhamentoMedico = new DadosDetalhamentoMedico(null, "Saulo Marques Rodrigues", "saulo-rodrigues@teste.com.br"
                , "23659", "61983625948", Especialidade.CARDIOLOGIA, endereco);
        when(medicoRepository.save(any())).thenReturn(new Medico(medico));

        MockHttpServletResponse response = mockMvc.perform(post("/medico/cadastro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(medicoDtoJson.write(medico
                        ).getJson())
                )
                .andReturn().getResponse();

        String jsonEsperado = detalhamentoMeicoDtoJson.write(detalhamentoMedico).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}