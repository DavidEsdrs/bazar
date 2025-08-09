package com.davidesdras.bazar.model.entities;

import java.util.Date;
import java.util.List;

public class Lote {
  private int id;
  private Date dataEntrega;
  private String observacao;
  private OrgaoFiscalizador fiscalizador;
  private OrgaoDonatario donatario;
  private List<Produto> doacoes;

  public Lote() {
  }

  public Lote(int id, Date dataEntrega, String observacao, OrgaoFiscalizador fiscalizador, OrgaoDonatario donatario,
      List<Produto> doacoes) {
    this.id = id;
    this.dataEntrega = dataEntrega;
    this.observacao = observacao;
    this.fiscalizador = fiscalizador;
    this.donatario = donatario;
    this.doacoes = doacoes;
  }

  public OrgaoFiscalizador getFiscalizador() {
    return fiscalizador;
  }

  public void setFiscalizador(OrgaoFiscalizador fiscalizador) {
    this.fiscalizador = fiscalizador;
  }

  public OrgaoDonatario getDonatario() {
    return donatario;
  }

  public void setDonatario(OrgaoDonatario donatario) {
    this.donatario = donatario;
  }

  public List<Produto> getDoacoes() {
    return doacoes;
  }

  public void setDoacoes(List<Produto> doacoes) {
    this.doacoes = doacoes;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Date getDataEntrega() {
    return dataEntrega;
  }

  public void setDataEntrega(Date dataEntrega) {
    this.dataEntrega = dataEntrega;
  }

  public String getObservacao() {
    return observacao;
  }

  public void setObservacao(String observacao) {
    this.observacao = observacao;
  }
}
