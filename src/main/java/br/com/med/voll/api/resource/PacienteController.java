package br.com.med.voll.api.resource;

import br.com.med.voll.api.domain.Paciente;
import br.com.med.voll.api.dto.PacienteDto;
import br.com.med.voll.api.repository.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void cadastra(@RequestBody @Valid PacienteDto dados){
        pacienteRepository.save(new Paciente(dados));
    }

    @GetMapping
    @CrossOrigin
    public String status(){
        return "Api /paciente Funcionando!";
    }
}
