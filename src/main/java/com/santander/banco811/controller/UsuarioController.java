package com.santander.banco811.controller;

import com.santander.banco811.dto.UsuarioRequest;
import com.santander.banco811.dto.UsuarioResponse;
import com.santander.banco811.model.Usuario;
import com.santander.banco811.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

  @Autowired
  UsuarioService usuarioService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Usuario> getAll(@RequestParam(value = "nome", required = false) String nome) {
    return usuarioService.getAll(nome);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UsuarioResponse create(@RequestBody UsuarioRequest usuarioRequest) {
    return usuarioService.create(usuarioRequest);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Usuario getById(@PathVariable("id") Integer id) {
    return usuarioService.getById(id);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Usuario update(@PathVariable("id") Integer id, @RequestBody UsuarioRequest usuarioRequest) {
    return usuarioService.update(usuarioRequest, id);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") Integer id) {
    usuarioService.delete(id);
  }
}