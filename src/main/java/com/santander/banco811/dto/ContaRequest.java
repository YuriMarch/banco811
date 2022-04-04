package com.santander.banco811.dto;

import com.santander.banco811.model.TipoConta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ContaRequest {
  private Integer numero;
  private Integer agencia;
  private TipoConta tipoConta;
}
