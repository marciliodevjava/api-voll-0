package br.com.med.voll.api.resource;

import br.com.med.voll.api.dto.CancelamentoConsutaDto;
import br.com.med.voll.api.dto.CancelamentoDetalheConsutaDto;
import br.com.med.voll.api.dto.DadosConsultasDto;
import br.com.med.voll.api.dto.DadosDetalhamentoConsultaDto;
import br.com.med.voll.api.repository.ConsultaRespository;
import br.com.med.voll.api.service.ConsultaServive;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
public class ConsultaResource {

    @Autowired
    private ConsultaServive consultaServive;

    @PostMapping("/marcar")
    @Transactional
    public ResponseEntity<?> agendar(@RequestBody @Valid DadosConsultasDto dados) {
        DadosDetalhamentoConsultaDto dadosDetalhamentoConsultaDto = consultaServive.agendar(dados);
        return ResponseEntity.ok(dadosDetalhamentoConsultaDto);
    }

    @DeleteMapping("/cancelar")
    @Transactional
    @CrossOrigin
    public ResponseEntity<CancelamentoDetalheConsutaDto> cancelarConsulta(@RequestBody @Valid CancelamentoConsutaDto cancelamentoConsutaDto) {
        CancelamentoDetalheConsutaDto retorno = consultaServive.cancelaMentoConsulta(cancelamentoConsutaDto);
        return ResponseEntity.ok(retorno);
    }
}
