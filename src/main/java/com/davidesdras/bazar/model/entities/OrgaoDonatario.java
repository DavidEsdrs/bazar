package com.davidesdras.bazar.model.entities;

public class OrgaoDonatario {
  private int id;
  private String nome;
  private String endereco;
  private String telefone;
  private String horarioFuncionamento;
  private String descricao;

  public OrgaoDonatario() {
  }

  public OrgaoDonatario(int id, String nome, String endereco, String telefone, String horarioFuncionamento,
      String descricao) {
    this.id = id;
    this.nome = nome;
    this.endereco = endereco;
    this.telefone = telefone;
    this.horarioFuncionamento = horarioFuncionamento;
    this.descricao = descricao;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public String getHorarioFuncionamento() {
    return horarioFuncionamento;
  }

  public void setHorarioFuncionamento(String horarioFuncionamento) {
    this.horarioFuncionamento = horarioFuncionamento;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

}
