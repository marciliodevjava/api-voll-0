package br.com.med.voll.api.resource;

import br.com.med.voll.api.domain.Endereco;
import br.com.med.voll.api.domain.Medico;
import br.com.med.voll.api.dto.MedicoDto;
import br.com.med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medico")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @CrossOrigin
    public void cadastrar(@RequestBody MedicoDto dadosMedico){
        medicoRepository.save(new Medico(dadosMedico));
    }

    @GetMapping
    @CrossOrigin
    public String status(){
        return "Api /medicos Funcionando!";
    }
}
