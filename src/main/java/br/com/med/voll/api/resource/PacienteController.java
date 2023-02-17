package br.com.med.voll.api.resource;

import br.com.med.voll.api.domain.Paciente;
import br.com.med.voll.api.dto.PacienteDto;
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

@RestController
@RequestMapping("paciente")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @CrossOrigin
    @Transactional
    public ResponseEntity<PacienteDto> cadastra(@RequestBody @Valid PacienteDto dados){
        Paciente retorno = pacienteRepository.save(new Paciente(dados));
        return ResponseEntity.ok(dados);
    }

    @GetMapping("/listar")
    @CrossOrigin
    public ResponseEntity<Page<PacienteRetornoDto>> listPaciente(@PageableDefault(size = 10, page = 0, sort = "name") Pageable paginacao){
        Page<PacienteRetornoDto> dados = pacienteRepository.findAll(paginacao).map(PacienteRetornoDto::new);
        return ResponseEntity.ok(dados);
    }

    @GetMapping
    @CrossOrigin
    public String status(){
        return "Api /paciente Funcionando!";
    }
}
