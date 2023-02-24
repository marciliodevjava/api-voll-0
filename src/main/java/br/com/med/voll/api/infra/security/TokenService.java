package br.com.med.voll.api.infra.security;

import br.com.med.voll.api.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario) {
        try {
            Algorithm algoritimo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API Voll.med")
                    .withSubject(usuario.getLogin())
                    .withClaim("Login: ", usuario.getLogin())
                    .withExpiresAt(DataExpiracao())
                    .sign(algoritimo);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar TOKEN: " + exception.getMessage());
        }
    }

    public String getSubject(String tokemJWT) {
        try {
            Algorithm algoritimo = Algorithm.HMAC256(secret);
            return JWT.require(algoritimo)
                    .withIssuer("API Voll.med")
                    .build()
                    .verify(tokemJWT)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Tokem inválido ou expirado!");
        }
    }

    private Instant DataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public Instant DataExpiracaoTime() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
