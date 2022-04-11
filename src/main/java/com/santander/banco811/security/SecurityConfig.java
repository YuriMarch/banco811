package com.santander.banco811.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserDetailsServiceImpl userService;

  public SecurityConfig(UserDetailsServiceImpl userService) {
    this.userService = userService;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    //Permissões de acesso

    http.csrf().disable().authorizeRequests()
            .antMatchers(HttpMethod.POST, "/usuario").permitAll()
            .antMatchers(HttpMethod.GET, "/usuario").permitAll()
            .antMatchers(HttpMethod.GET, "/usuario/{id}").permitAll()
            .antMatchers(HttpMethod.DELETE, "/usuario/{id}").permitAll()
            .antMatchers(HttpMethod.PUT, "/usuario/{id}").permitAll()
            .antMatchers(HttpMethod.PUT, "/usuario/{cpf}").permitAll()
            .antMatchers(HttpMethod.GET, "/health-check").permitAll()
            .antMatchers(HttpMethod.GET, "/conta").permitAll()
            .antMatchers(HttpMethod.POST, "/conta").permitAll()
            .antMatchers(HttpMethod.GET, "/conta/view").permitAll()
            .anyRequest().authenticated().and()
            .addFilter(new JWTAuthenticateFilter(authenticationManager()))
            .addFilter(new JWTValidateFilter(authenticationManager()))
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  @Bean
  PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }
}
