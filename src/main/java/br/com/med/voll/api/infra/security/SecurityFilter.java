package br.com.med.voll.api.infra.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private String tokemJWT;
    private String authorizationHader;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        this.tokemJWT = recuperarToken(request);
        System.out.println(tokemJWT);
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        this.authorizationHader = request.getHeader("Authorization");
        if(authorizationHader == null){
            throw new RuntimeException("Token não enviado, no cabeçalho Authorization!");
        }
        return authorizationHader.replace("Bearer ", "");
    }
}
