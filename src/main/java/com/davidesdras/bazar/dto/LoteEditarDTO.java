package com.davidesdras.bazar.dto;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class LoteEditarDTO {
  private Integer id;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date dataEntrega;
  private String observacao;
  private Integer fiscalizadorId;
  private Integer donatarioId;
  private List<ProdutoDTO> doacoes;

  public LoteEditarDTO(Integer id, Date dataEntrega, String observacao, Integer fiscalizadorId, Integer donatarioId,
      List<ProdutoDTO> doacoes) {
    this.id = id;
    this.dataEntrega = dataEntrega;
    this.observacao = observacao;
    this.fiscalizadorId = fiscalizadorId;
    this.donatarioId = donatarioId;
    this.doacoes = doacoes;
  }

  public LoteEditarDTO() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
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

  public Integer getFiscalizadorId() {
    return fiscalizadorId;
  }

  public void setFiscalizadorId(Integer fiscalizadorId) {
    this.fiscalizadorId = fiscalizadorId;
  }

  public Integer getDonatarioId() {
    return donatarioId;
  }

  public void setDonatarioId(Integer donatarioId) {
    this.donatarioId = donatarioId;
  }

  public List<ProdutoDTO> getDoacoes() {
    return doacoes;
  }

  public void setDoacoes(List<ProdutoDTO> doacoes) {
    this.doacoes = doacoes;
  }

}
