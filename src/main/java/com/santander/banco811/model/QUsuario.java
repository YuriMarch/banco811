package com.santander.banco811.model;

import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

public class QUsuario extends EntityPathBase<Usuario> {

  public static final QUsuario qusuario = new QUsuario("usuario");

  public final NumberPath<Long> id = createNumber("id", Long.class);

  public QUsuario(String variable) {
    super(Usuario.class, forVariable(variable));
  }
  public final StringPath nome = createString("nome");

  public final StringPath cpf = createString("cpf");
}
