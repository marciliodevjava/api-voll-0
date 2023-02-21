package br.com.med.voll.api.dto;


import org.apache.logging.log4j.CloseableThreadContext;

import java.time.Instant;
import java.util.Date;

public record TokemDadosJWT (String login, Date data, Instant expiracao, String token){

}
