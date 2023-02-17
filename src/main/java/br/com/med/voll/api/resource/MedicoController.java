package br.com.med.voll.api.resource;

import br.com.med.voll.api.domain.Endereco;
import br.com.med.voll.api.domain.Medico;
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
    public ResponseEntity<MedicoDto> cadastrar(@RequestBody @Valid MedicoDto dadosMedico){
       Medico medico = medicoRepository.save(new Medico(dadosMedico));
       return ResponseEntity.ok(dadosMedico);
    }

    @GetMapping("/listar")
    @CrossOrigin
    public ResponseEntity<Page<ListagemMedicosDto>> listar(@PageableDefault(size = 10, sort = "nome", page = 0) Pageable paginacao){
        Page<ListagemMedicosDto> retorno = medicoRepository.findAll(paginacao).map(ListagemMedicosDto::new);
        return ResponseEntity.ok(retorno);
    }

    @PutMapping("/atualizar")
    @Transactional
    @CrossOrigin
    public ResponseEntity<MedicoAtualizarDto> atualizar(@RequestBody @Valid MedicoAtualizarDto medicoAtualizarDto){
        Medico medico = medicoRepository.getReferenceById(medicoAtualizarDto.id());
        medico.atualizarInformacoes(medicoAtualizarDto);
        return ResponseEntity.ok(medicoAtualizarDto);
    }
    @GetMapping
    @CrossOrigin
    public String status(){
        return "Api /medicos Funcionando!";
    }
}
