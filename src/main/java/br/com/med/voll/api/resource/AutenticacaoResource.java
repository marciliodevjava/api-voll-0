package br.com.med.voll.api.resource;

import br.com.med.voll.api.domain.usuario.Usuario;
import br.com.med.voll.api.dto.LoginDto;
import br.com.med.voll.api.dto.TokemDadosJWT;
import br.com.med.voll.api.dto.UsuarioCadastroDto;
import br.com.med.voll.api.infra.security.TokenService;
import br.com.med.voll.api.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("login")
public class AutenticacaoResource {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;


    @PostMapping
    @Transactional
    @CrossOrigin
    public ResponseEntity<?> login(@RequestBody @Valid LoginDto loginDto){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDto.login(), loginDto.senha());
        Authentication authentication = authenticationManager.authenticate(token);

        String tokemGerado = tokenService.gerarToken((Usuario)authentication.getPrincipal());
        return ResponseEntity.ok(new TokemDadosJWT(loginDto.login(), new Date(),tokenService.DataExpiracaoTime(), tokemGerado));
    }

    @PostMapping("/cadastrar")
    @Transactional
    @CrossOrigin
    public ResponseEntity<?> cadastroUsuario(@RequestBody @Valid UsuarioCadastroDto usuarioCadastroDto){
        Usuario cadastro = usuarioRepository.save(new Usuario(usuarioCadastroDto));
        return ResponseEntity.ok().build();
    }
}
