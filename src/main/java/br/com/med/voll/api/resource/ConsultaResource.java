package br.com.med.voll.api.resource;

import br.com.med.voll.api.dto.DadosConsultasDto;
import br.com.med.voll.api.dto.DadosDetalhamentoConsultaDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
public class ConsultaResource {

    @PostMapping("/marcar")
    @Transactional
    public ResponseEntity<?> agendar(@RequestBody @Valid DadosConsultasDto dados){

        return ResponseEntity.ok(new DadosDetalhamentoConsultaDto(null, null, null, null));
    }
}
