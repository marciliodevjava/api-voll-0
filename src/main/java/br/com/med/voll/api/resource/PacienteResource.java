package br.com.med.voll.api.resource;

import br.com.med.voll.api.domain.Paciente;
import br.com.med.voll.api.dto.PacienteDto;
import br.com.med.voll.api.dto.PacienteRetornoCriadoDto;
import br.com.med.voll.api.dto.PacienteRetornoDto;
import br.com.med.voll.api.repository.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("paciente")
public class PacienteResource {

    private URI uri;
    private Paciente paciente;
    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @CrossOrigin
    @Transactional
    public ResponseEntity<PacienteRetornoCriadoDto> cadastra(@RequestBody @Valid PacienteDto dados, UriComponentsBuilder uriBuilder) {
        Paciente retorno = pacienteRepository.save(new Paciente(dados));
        this.uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(retorno.getId()).toUri();
        return ResponseEntity.created(uri).body(new PacienteRetornoCriadoDto(retorno));
    }

    @GetMapping("/listar")
    @CrossOrigin
    public ResponseEntity<Page<PacienteRetornoDto>> listPaciente(@PageableDefault(size = 10, page = 0, sort = "name") Pageable paginacao) {
        Page<PacienteRetornoDto> dados = pacienteRepository.findAllByAtivoTrue(paginacao).map(PacienteRetornoDto::new);
        return ResponseEntity.ok(dados);
    }

    @DeleteMapping("/excluir/{id}")
    @Transactional
    @CrossOrigin
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        this.paciente = pacienteRepository.getReferenceById(id);
        paciente.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ativar/{id}")
    @Transactional
    @CrossOrigin
    public ResponseEntity<?> ativarPaciente(@PathVariable Long id) {
        this.paciente = pacienteRepository.getReferenceById(id);
        paciente.ativar();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    @CrossOrigin
    public ResponseEntity<PacienteRetornoDto> trazerDados(@PathVariable Long id) {
        Paciente paciente = pacienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new PacienteRetornoDto(paciente));
    }

    @GetMapping
    @CrossOrigin
    public String status() {
        return "Api /paciente Funcionando!";
    }
}
