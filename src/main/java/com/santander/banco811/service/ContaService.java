package com.santander.banco811.service;

import com.santander.banco811.dto.ContaRequest;
import com.santander.banco811.dto.ContaResponse;
import com.santander.banco811.model.Conta;
import com.santander.banco811.model.TipoConta;
import com.santander.banco811.model.Usuario;
import com.santander.banco811.projection.ContaView;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ContaService {
  List<ContaView> getAllViewByTipoConta(TipoConta tipoConta);

  Conta create(ContaRequest contaRequest, Integer userId);

//  Page<Conta> getAll(String nome, int page, int size);

  void delete(Integer id);
}

