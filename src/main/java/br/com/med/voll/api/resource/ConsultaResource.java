package br.com.med.voll.api.resource;

import br.com.med.voll.api.dto.*;
import br.com.med.voll.api.service.ConsultaServive;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping("/listar/consultas")
    @CrossOrigin
    public ResponseEntity<Page<ListagemConsultaDto>> listarConsutas(@PageableDefault(size = 10, sort = "data", page = 0) Pageable paginacao){
        Page<ListagemConsultaDto> retorno = consultaServive.listaPaginada(paginacao);
        return ResponseEntity.ok(retorno);
    }
}
