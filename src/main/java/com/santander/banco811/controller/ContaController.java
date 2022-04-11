package com.santander.banco811.controller;

import com.santander.banco811.dto.ContaRequest;
import com.santander.banco811.dto.ContaResponse;
import com.santander.banco811.model.Conta;
import com.santander.banco811.model.TipoConta;
import com.santander.banco811.projection.ContaView;
import com.santander.banco811.service.ContaService;
import com.santander.banco811.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.List;

@RestController
@RequestMapping("/conta")
public class ContaController {

  @Autowired
  ContaService contaService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Page<Conta> getAll(@RequestParam(required = false) String nome,
                            @RequestParam(required = false, defaultValue = "0") int page,
                            @RequestParam(required = false, defaultValue = "10") int size) {
    return contaService.getAll(nome, page, size);
  }

  @GetMapping("/view")
  public List<ContaView> getAllContaViewByTipoConta(@RequestParam TipoConta tipoConta) {
    return contaService.getAllViewByTipoConta(tipoConta);
  }

  @PostMapping
  public Conta create(@RequestBody ContaRequest contaRequest, @RequestParam(required = true) Integer userId) {
    return contaService.create(contaRequest, userId);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") Integer id) {
    contaService.delete(id);
  }

  private static final String USERNAME = "USERNAME";
}
