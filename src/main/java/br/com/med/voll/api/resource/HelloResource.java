package br.com.med.voll.api.resource;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "hello")
public class HelloResource {


    @GetMapping
    @CrossOrigin
    public String olaMundo(){
        return "Hello World! SpringBoot!!!";
    }
    @GetMapping("/status")
    @CrossOrigin
    public String status(){
        return "Api Funcionando!";
    }
}
