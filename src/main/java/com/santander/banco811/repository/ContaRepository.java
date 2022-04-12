package com.santander.banco811.repository;

import com.santander.banco811.model.Conta;
import com.santander.banco811.model.TipoConta;
import com.santander.banco811.projection.ContaView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {
  List<Conta> findBySaldoLessThan(BigDecimal valor);
  List<Conta> findBySaldoGreaterThan(BigDecimal valor);
  List<Conta> findBySaldoLessThanEqual(BigDecimal valor);
  List<Conta> findBySaldoGreaterThanEqual(BigDecimal valor);

  List<Conta> findBySaldoBetween(BigDecimal valorInicial, BigDecimal valorFinal);
  List<Conta> findBySaldoIn(List<BigDecimal> valores);

  List<Conta> findByUsuario_cpf(String cpf);

  Boolean existsByTipoConta(TipoConta tipoConta);

  @Query("select c from Conta c " +
          "where (c.tipoConta = :tipoConta and c.usuario.cpf = :cpf) " +
          "or (c.tipoConta = :tipoConta or c.saldo = :saldo)")
  List<Conta> findByTipoContaAndCpfOrTipoContaAndSaldo(
          @Param("tipoConta") TipoConta tipoConta,
          @Param("cpf") String cpf,
          @Param("saldo") BigDecimal saldo
  );

  List<ContaView> findAllByTipoConta(TipoConta tipoConta);

  Page<Conta> findByNumero(Integer numero, Pageable pageable);

  Page<Conta> findByUsuario_nome(String nome, Pageable pageable);
}
