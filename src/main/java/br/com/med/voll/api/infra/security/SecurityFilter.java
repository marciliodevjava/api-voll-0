package br.com.med.voll.api.infra.security;

import br.com.med.voll.api.domain.usuario.Usuario;
import br.com.med.voll.api.repository.UsuarioRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private String tokemJWT;
    private String authorizationHader;
    private String subject;
    private UserDetails usuario;
    private UsernamePasswordAuthenticationToken authentication;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        this.tokemJWT = recuperarToken(request);

        if (this.tokemJWT != null) {
            this.subject = tokenService.getSubject(tokemJWT);
            this.usuario = usuarioRepository.findByLogin(this.subject);

            this.authentication = new UsernamePasswordAuthenticationToken(this.usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        this.authorizationHader = request.getHeader("Authorization");
        if (authorizationHader != null) {
            return authorizationHader.replace("Bearer ", "");
        }

        return null;
    }
}
