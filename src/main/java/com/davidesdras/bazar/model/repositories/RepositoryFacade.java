package com.davidesdras.bazar.model.repositories;

import com.davidesdras.bazar.model.entities.Lote;
import com.davidesdras.bazar.model.entities.OrgaoDonatario;
import com.davidesdras.bazar.model.entities.OrgaoFiscalizador;
import com.davidesdras.bazar.model.entities.Produto;

@org.springframework.stereotype.Repository
public class RepositoryFacade {
  public static RepositoryFacade myself = null;

  private Repository<Integer, OrgaoDonatario> rOrgaoDonatario = null;
  private Repository<Integer, OrgaoFiscalizador> rOrgaoFiscalizador = null;
  private Repository<Integer, Lote> rLote = null;
  private Repository<Integer, Produto> rProduto = null;

  public RepositoryFacade() {
    this.rOrgaoDonatario = new OrgaoDonatarioRepository();
    this.rOrgaoFiscalizador = new OrgaoFiscalizadorRepository();
    this.rLote = new LoteRepository();
    this.rProduto = new ProdutoRepository();
  }

  public static RepositoryFacade getCurrentInstance() {
    if (myself == null)
      myself = new RepositoryFacade();
    return myself;
  }
}
