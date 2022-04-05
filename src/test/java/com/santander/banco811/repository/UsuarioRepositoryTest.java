package com.santander.banco811.repository;

import com.santander.banco811.model.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;

//@RunWith(SpringRunner.class) for JUnit4
@DataJpaTest
public class UsuarioRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Test
  public void validar_findAll_vazio_se_repository_em_branco(){
    var usuarios = usuarioRepository.findAll();
    Assertions.assertEquals(Arrays.asList(), usuarios);
  }

  @Test
  public void trazer_usuarios_cadastrados_no_findAll() {
    var usuario = new Usuario();
    usuario.setNome("Joao");
    usuario.setSenha("1234567");
    usuario.setCpf("12345678910");

    var usuario2 = new Usuario();
    usuario2.setNome("Maria");
    usuario2.setSenha("7654321");
    usuario2.setCpf("10987654321");

    entityManager.persist(usuario);
    entityManager.persist(usuario2);

    var usuarios = usuarioRepository.findAll();

    Assertions.assertEquals(Arrays.asList(usuario, usuario2), usuarios);
  }
}
