package com.santander.banco811.service;

import com.santander.banco811.dto.ContaRequest;
import com.santander.banco811.dto.ContaResponse;
import com.santander.banco811.model.TipoConta;
import com.santander.banco811.projection.ContaView;

import java.util.List;

public interface ContaService {
  List<ContaView> getAllViewByTipoConta(TipoConta tipoConta);

//  ContaResponse create(ContaRequest contaRequest, Integer usuarioId);

}

