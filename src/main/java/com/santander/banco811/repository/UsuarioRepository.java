package com.santander.banco811.repository;

import com.santander.banco811.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
  List<Usuario> findByNome(String nome);
  List<Usuario> findByNomeAndCpf(String nome, String cpf);
  List<Usuario> findByCpf(String cpf);

  List<Usuario> findByNomeIsNot(String nome);
  List<Usuario> findByNomeIs(String nome);

  List<Usuario> findByNomeIsNull();
  List<Usuario> findByNomeIsNotNull();

  List<Usuario> findByCpfStartingWith(String cpf);
  List<Usuario> findByCpfEndingWith(String cpf);
  List<Usuario> findByCpfContaining(String cpf);

  List<Usuario> findByNomeLike(String nome);


  List<Usuario> findByDataCriacaoAfter(LocalDateTime dataCriacao);
  List<Usuario> findByDataCriacaoBefore(LocalDateTime dataCriacao);
  List<Usuario> findByDataCriacaoAfterAndNome(LocalDateTime dataCriacao, String nome);
  LocalDateTime now = LocalDateTime.now();
  List<Usuario> findByDataCriacaoBetween(LocalDateTime dataCriacao, LocalDateTime now);
  List<Usuario> findByDataCriacao(LocalDateTime dataCriacao);

  List<Usuario> findByNomeOrderByNome(String nome);



}