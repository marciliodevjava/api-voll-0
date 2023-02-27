package br.com.med.voll.api.resource;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "hello")
@SecurityRequirement(name = "bearer-key")
public class HelloResource {


    @GetMapping
    @CrossOrigin
    public String olaMundo() {
        return "Hello World! SpringBoot!!!";
    }

    @GetMapping("/status")
    @CrossOrigin
    public String status() {
        return "Api Funcionando!";
    }
}
