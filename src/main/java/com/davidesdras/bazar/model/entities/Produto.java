package com.davidesdras.bazar.model.entities;

public class Produto {
  private int codigo;
  private String nome;
  private String descricao;
  private Lote lote;

  public Produto() {
  }

  public Produto(int codigo, String nome, String descricao, Lote lote) {
    this.codigo = codigo;
    this.nome = nome;
    this.descricao = descricao;
    this.lote = lote;
  }

  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public Lote getLote() {
    return this.lote;
  }

  public void setLote(Lote lote) {
    this.lote = lote;
  }

}
