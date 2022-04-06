package com.santander.banco811.controller;

import com.querydsl.core.types.Predicate;
import com.santander.banco811.dto.UsuarioRequest;
import com.santander.banco811.dto.UsuarioResponse;
import com.santander.banco811.model.Usuario;
import com.santander.banco811.repository.UsuarioRepository;
import com.santander.banco811.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

  @Autowired
  UsuarioService usuarioService;

  @Autowired
  UsuarioRepository usuarioRepository;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Page<Usuario> getAll(@RequestParam(value = "nome", required = false) String nome,
                              @RequestParam(required = false, defaultValue = "0") int page,
                              @RequestParam(required = false, defaultValue = "10") int size) {
    return usuarioService.getAll(nome, page, size);
  }

  @GetMapping("cpf")
  @ResponseStatus(HttpStatus.OK)
  public Page<UsuarioResponse> getAllByCpf(@RequestParam(value = "cpf", required = false) String cpf,
                                           @RequestParam(required = false, defaultValue = "0") int page,
                                           @RequestParam(required = false, defaultValue = "10") int size) {
    return usuarioService.getAllByCpf(cpf, page, size);
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

  @GetMapping("/search")
  @ResponseStatus(HttpStatus.OK)
  public List<Usuario> search(@RequestParam String search){
    return usuarioService.search(search);
  }

  @GetMapping("/search/dsl")
  @ResponseStatus(HttpStatus.OK)
  public Iterable<Usuario> searchDsl(@QuerydslPredicate(root = Usuario.class) Predicate predicate){
    return usuarioRepository.findAll();
  }
}