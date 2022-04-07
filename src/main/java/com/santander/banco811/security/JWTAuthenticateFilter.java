package com.santander.banco811.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.banco811.model.Usuario;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

public class JWTAuthenticateFilter extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager authenticationManager;

  public JWTAuthenticateFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

    try {
      var usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
      return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
              usuario.getLogin(),
              usuario.getSenha(),
              new ArrayList<>()
      ));
    } catch (IOException e) {
      throw new RuntimeException("Failed to authenticate user", e);
    }
  }

  @Override
  @Bean
  protected void successfulAuthentication(
          HttpServletRequest request,
          HttpServletResponse response,
          FilterChain chain,
          Authentication authResult) throws IOException {
    var usuarioData = ((Usuario) authResult.getPrincipal());

    var token = JWT.create()
            .withSubject(usuarioData.getLogin())
            .withExpiresAt(Date.from(Instant.now().plus(EXPIRATION_TOKEN, ChronoUnit.MILLIS)))
            .sign(Algorithm.HMAC512(PASSWORD_TOKEN));

    response.getWriter().write(token);
    response.getWriter().flush();
  }

  public static final int EXPIRATION_TOKEN = 600_000; // 10 minutos
  public static final String PASSWORD_TOKEN = "98029d17-7350-4fc6-a729-e5fc1e0f4bd0";
}

