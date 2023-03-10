package br.com.med.voll.api.resource;

import br.com.med.voll.api.domain.Medico;
import br.com.med.voll.api.dto.*;
import br.com.med.voll.api.repository.MedicoRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping("medico")
@SecurityRequirement(name = "bearer-key")
public class MedicoResource {

    private URI uri;
    private Medico medico;

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping("/cadastro")
    @CrossOrigin
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedico> cadastrar(@RequestBody @Valid MedicoDto dadosMedico, UriComponentsBuilder uriBuilder) {
        Medico medico = medicoRepository.save(new Medico(dadosMedico));
        this.uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping("/listar")
    @CrossOrigin
    public ResponseEntity<Page<ListagemMedicosDto>> listar(@PageableDefault(size = 10, sort = "nome", page = 0) Pageable paginacao) {

        Page<ListagemMedicosDto> retorno = medicoRepository.findAllByAtivoTrue(paginacao).map(ListagemMedicosDto::new);
        return ResponseEntity.ok(retorno);
    }

    @PutMapping("/atualizar")
    @Transactional
    @CrossOrigin
    public ResponseEntity<DadosDetalhamentoMedico> atualizar(@RequestBody @Valid MedicoAtualizarDto medicoAtualizarDto) {
        this.medico = medicoRepository.getReferenceById(medicoAtualizarDto.id());
        this.medico.atualizarInformacoes(medicoAtualizarDto);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("/excluir/real/{id}")
    @Transactional
    @CrossOrigin
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        medicoRepository.deleteById(id);
        return ResponseEntity.ok("M??dico: " + id + " deletado");
    }

    @DeleteMapping("/excluir/{id}")
    @Transactional
    @CrossOrigin
    public ResponseEntity<?> excluirLogico(@PathVariable Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ativar/{id}")
    @Transactional
    @CrossOrigin
    public ResponseEntity<?> ativar(@PathVariable Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        medico.ativar();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    @CrossOrigin
    public ResponseEntity<MedicoRetornoDto> trazerMedicoDados(@PathVariable Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new MedicoRetornoDto(medico));
    }

    @GetMapping
    @CrossOrigin
    public String status() {
        return "Api /medicos Funcionando!";
    }
}
