package br.com.med.voll.api.resource;

import br.com.med.voll.api.domain.Endereco;
import br.com.med.voll.api.domain.Medico;
import br.com.med.voll.api.dto.ListagemMedicosDto;
import br.com.med.voll.api.dto.MedicoDto;
import br.com.med.voll.api.repository.MedicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medico")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping("/cadastro")
    @CrossOrigin
    @Transactional
    public ResponseEntity<MedicoDto> cadastrar(@RequestBody @Valid MedicoDto dadosMedico){
       Medico medico = medicoRepository.save(new Medico(dadosMedico));
       return ResponseEntity.ok(dadosMedico);
    }

    @GetMapping("/listar")
    @CrossOrigin
    public ResponseEntity<List<ListagemMedicosDto>> listar(){
        List<ListagemMedicosDto> retorno = medicoRepository.findAll().stream().map(ListagemMedicosDto::new).toList();
        return ResponseEntity.ok(retorno);
    }

    @GetMapping
    @CrossOrigin
    public String status(){
        return "Api /medicos Funcionando!";
    }
}
