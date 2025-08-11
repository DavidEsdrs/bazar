package com.davidesdras.bazar.dto;

public class ProdutoDTO {
  private String nome;
  private String descricao;

  public ProdutoDTO(String nome, String descricao) {
    this.nome = nome;
    this.descricao = descricao;
  }

  public ProdutoDTO() {
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
}
