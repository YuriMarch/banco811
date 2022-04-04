package com.santander.banco811.dto;

import com.santander.banco811.model.Conta;
import com.santander.banco811.model.TipoConta;
import com.santander.banco811.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ContaResponse {
  private Integer id;
  private Integer numero;
  private Integer agencia;
  private Usuario usuario;
  private TipoConta tipoConta;
  private BigDecimal saldo;
  private LocalDateTime dataCriacao;
  private LocalDateTime dataAtualizacao;

  public ContaResponse(Conta conta) {
    this.id = conta.getId();
    this.numero = conta.getNumero();
    this.agencia = conta.getAgencia();
    this.usuario = conta.getUsuario();
    this.tipoConta = conta.getTipoConta();
    this.saldo = conta.getSaldo();
    this.dataCriacao = conta.getDataCriacao();
    this.dataAtualizacao = conta.getDataAtualizacao();
  }

}
