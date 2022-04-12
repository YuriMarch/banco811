package com.santander.banco811.service.impl;

import com.santander.banco811.dto.ContaRequest;
import com.santander.banco811.dto.ContaResponse;
import com.santander.banco811.model.Conta;
import com.santander.banco811.model.TipoConta;
import com.santander.banco811.model.Usuario;
import com.santander.banco811.projection.ContaView;
import com.santander.banco811.repository.ContaRepository;
import com.santander.banco811.repository.UsuarioRepository;
import com.santander.banco811.service.ContaService;
import com.santander.banco811.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaServiceImpl implements ContaService {

  @Autowired
  ContaRepository contaRepository;

  @Autowired
  UsuarioRepository usuarioRepository;

  @Autowired
  UsuarioService usuarioService;

  @Override
  public List<ContaView> getAllViewByTipoConta(TipoConta tipoConta) {
    return contaRepository.findAllByTipoConta(tipoConta);
  }

  @Override
  public Conta create(ContaRequest contaRequest, Integer userId) {
    var usuario = usuarioRepository.findById(userId);

    Conta conta = new Conta();
    conta.setTipoConta(contaRequest.getTipoConta());
    conta.setAgencia(contaRequest.getAgencia());
    conta.setNumero(contaRequest.getNumero());
    conta.setUsuario(usuario.get());

    return contaRepository.save(conta);
  }

//  @Override
//  public Page<Conta> getAll(String nome, int page, int size) {
//
//    PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "nome");
//
//    if (nome != null) {
//      return contaRepository.findByUsuario_nome(nome, pageRequest);
//    } else {
//      return contaRepository.findAll(pageRequest);
//    }
//  }

  @Override
  public void delete(Integer id) {
    var conta = contaRepository.findById(id).orElseThrow();
    contaRepository.delete(conta);
  }

  @Override
  public List<Conta> getAll() {
    return contaRepository.findAll();
  }

//  @Override
//  public ContaResponse create(ContaRequest contaRequest, Integer usuarioId) {
//
//    Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow();
//    Conta novaConta = new Conta();
//    novaConta.setUsuario(usuario);
//    Conta conta = contaRepository.save(novaConta);
//    return new ContaResponse(conta);

//    var usuario = usuarioService.getById(usuarioId);
//    Conta conta = new Conta();
//    conta.setTipoConta(contaRequest.getTipoConta());
//    conta.setAgencia(contaRequest.getAgencia());
//    conta.setNumero(contaRequest.getNumero());
//
//    conta = contaRepository.save(conta);
//    return conta;
//  }
}
