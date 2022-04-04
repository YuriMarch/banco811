package com.santander.banco811.service;

import com.santander.banco811.dto.ContaRequest;
import com.santander.banco811.dto.ContaResponse;

public interface ContaService {
  ContaResponse create(ContaRequest contaRequest, Integer usuarioId);

}

