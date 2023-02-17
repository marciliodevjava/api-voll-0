package br.com.med.voll.api.resource;

import br.com.med.voll.api.domain.Endereco;
import br.com.med.voll.api.domain.Medico;
import br.com.med.voll.api.dto.DadosDetalhamentoMedico;
import br.com.med.voll.api.dto.ListagemMedicosDto;
import br.com.med.voll.api.dto.MedicoAtualizarDto;
import br.com.med.voll.api.dto.MedicoDto;
import br.com.med.voll.api.repository.MedicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("medico")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping("/cadastro")
    @CrossOrigin
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid MedicoDto dadosMedico, UriComponentsBuilder uriBuilder){
       Medico medico = medicoRepository.save(new Medico(dadosMedico));
       var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
       return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping("/listar")
    @CrossOrigin
    public ResponseEntity<Page<ListagemMedicosDto>> listar(@PageableDefault(size = 10, sort = "nome", page = 0) Pageable paginacao){
        Page<ListagemMedicosDto> retorno = medicoRepository.findAllByAtivoTrue(paginacao).map(ListagemMedicosDto::new);
        return ResponseEntity.ok(retorno);
    }

    @PutMapping("/atualizar")
    @Transactional
    @CrossOrigin
    public ResponseEntity<?> atualizar(@RequestBody @Valid MedicoAtualizarDto medicoAtualizarDto){
        var medico = medicoRepository.getReferenceById(medicoAtualizarDto.id());
        medico.atualizarInformacoes(medicoAtualizarDto);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("/excluir/real/{id}")
    @Transactional
    @CrossOrigin
    public ResponseEntity<?> excluir(@PathVariable Long id){
        medicoRepository.deleteById(id);
        return ResponseEntity.ok("Médico: " + id + " deletado");
    }

    @DeleteMapping("/excluir/{id}")
    @Transactional
    @CrossOrigin
    public ResponseEntity<?> excluirLogico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ativar/{id}")
    @Transactional
    @CrossOrigin
    public ResponseEntity<?> ativar(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        medico.ativar();
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @CrossOrigin
    public String status(){
        return "Api /medicos Funcionando!";
    }
}
