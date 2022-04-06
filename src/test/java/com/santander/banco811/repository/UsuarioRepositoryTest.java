package com.santander.banco811.repository;

import com.santander.banco811.model.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;

//@RunWith(SpringRunner.class) for JUnit4
@DataJpaTest
@Profile("test")
public class UsuarioRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Test
  public void validar_findAll_vazio_se_repository_em_branco() {
    var usuarios = usuarioRepository.findAll();
    Assertions.assertEquals(Arrays.asList(), usuarios);
  }

  @Test
  public void trazer_usuarios_cadastrados_no_findAll() {
    Usuario usuario = new Usuario();
    usuario.setNome("Joao");
    usuario.setSenha("1234567");
    usuario.setCpf("12345678910");

    Usuario usuario2 = new Usuario();
    usuario2.setNome("Maria");
    usuario2.setSenha("7654321");
    usuario2.setCpf("10987654321");

    entityManager.persist(usuario);
    entityManager.persist(usuario2);

    var usuarios = usuarioRepository.findAll();

    Assertions.assertEquals(Arrays.asList(usuario, usuario2), usuarios);
  }

  @Test
  public void trazer_usuarios_pelo_nome_com_sucesso(){
    Usuario usuario = new Usuario();
    usuario.setNome("Joao");
    usuario.setSenha("1234567");
    usuario.setCpf("12345678910");

    Usuario usuario2 = new Usuario();
    usuario2.setNome("Maria");
    usuario2.setSenha("7654321");
    usuario2.setCpf("10987654321");

    usuario = entityManager.persist(usuario);
    entityManager.persist(usuario2);

    PageRequest pageRequest = PageRequest.of(0, 10);

    var usuarios = usuarioRepository.findByNome("Joao", pageRequest);
    Assertions.assertEquals(1, usuarios.getTotalElements());
    Assertions.assertEquals(usuario, usuarios.stream().findFirst().get());
  }

  @Test
  public void salvar_um_novo_usuario_com_sucesso(){
    Usuario usuario = new Usuario();
    usuario.setNome("Joao");
    usuario.setSenha("1234567");
    usuario.setCpf("12345678910");

    usuario = usuarioRepository.save(usuario);
    Assertions.assertNotNull(usuario.getId());
    Assertions.assertNotNull(usuario.getDataAtualizacao());
    Assertions.assertNotNull(usuario.getDataCriacao());
  }

  @Test
  public void trazer_usuario_pelo_nome(){
    Usuario usuario = new Usuario();
    usuario.setNome("Joao");
    usuario.setSenha("1234567");
    usuario.setCpf("12345678910");

    Usuario usuario2 = new Usuario();
    usuario2.setNome("Maria");
    usuario2.setSenha("7654321");
    usuario2.setCpf("10987654321");

    PageRequest pageRequest = PageRequest.of(0, 10, Sort.Direction.ASC, "nome");

    var usuarios = usuarioRepository.findByNome("Joao", pageRequest);

    Assertions.assertEquals(1, usuarios.getTotalElements());
    Assertions.assertEquals(usuario, usuarios.stream().findFirst().get());
  }

  @Test
  public void encontrar_usuario_pelo_id(){
    Usuario usuario = new Usuario();
    usuario.setNome("Joao");
    usuario.setSenha("1234567");
    usuario.setCpf("12345678910");

    usuario = entityManager.persist(usuario);

    var usuarioEncontrado = usuarioRepository.findById(usuario.getId()).get();

    Assertions.assertEquals(usuarioEncontrado.getId(), usuario.getId());
    Assertions.assertEquals(usuarioEncontrado.getCpf(), usuario.getCpf());
  }
}
