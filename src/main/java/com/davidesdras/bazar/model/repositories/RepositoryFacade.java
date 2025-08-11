package com.davidesdras.bazar.model.repositories;

import java.sql.SQLException;
import java.util.List;

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

  // Orgão donatário
  public void create(OrgaoDonatario tipo) throws ClassNotFoundException, SQLException {
    this.rOrgaoDonatario.create(tipo);
  }

  public void update(OrgaoDonatario tipo) throws ClassNotFoundException, SQLException {
    this.rOrgaoDonatario.update(tipo);
  }

  public OrgaoDonatario readOrgaoDonatario(Integer codigo) throws ClassNotFoundException, SQLException {
    return this.rOrgaoDonatario.read(codigo);
  }

  public void delete(OrgaoDonatario tipo) throws ClassNotFoundException, SQLException {
    this.rOrgaoDonatario.delete(tipo);
  }

  public List<OrgaoDonatario> readAllDonatarios() throws ClassNotFoundException, SQLException {
    return this.rOrgaoDonatario.readAll();
  }

  // Orgão fiscalizador
  public void create(OrgaoFiscalizador tipo) throws ClassNotFoundException, SQLException {
    this.rOrgaoFiscalizador.create(tipo);
  }

  public void update(OrgaoFiscalizador tipo) throws ClassNotFoundException, SQLException {
    this.rOrgaoFiscalizador.update(tipo);
  }

  public OrgaoFiscalizador readOrgaoFiscalizador(Integer codigo) throws ClassNotFoundException, SQLException {
    return this.rOrgaoFiscalizador.read(codigo);
  }

  public void delete(OrgaoFiscalizador tipo) throws ClassNotFoundException, SQLException {
    this.rOrgaoFiscalizador.delete(tipo);
  }

  public List<OrgaoFiscalizador> readAllFiscalizador() throws ClassNotFoundException, SQLException {
    return this.rOrgaoFiscalizador.readAll();
  }

  // Lote
  public void create(Lote tipo) throws ClassNotFoundException, SQLException {
    this.rLote.create(tipo);
  }

  public void update(Lote tipo) throws ClassNotFoundException, SQLException {
    this.rLote.update(tipo);
  }

  public Lote readLote(Integer codigo) throws ClassNotFoundException, SQLException {
    return this.rLote.read(codigo);
  }

  public void delete(Lote tipo) throws ClassNotFoundException, SQLException {
    this.rLote.delete(tipo);
  }

  public List<Lote> readAllLote() throws ClassNotFoundException, SQLException {
    return this.rLote.readAll();
  }

  // Produto
  public void create(Produto tipo) throws ClassNotFoundException, SQLException {
    this.rProduto.create(tipo);
  }

  public void update(Produto tipo) throws ClassNotFoundException, SQLException {
    this.rProduto.update(tipo);
  }

  public Produto readProduto(Integer codigo) throws ClassNotFoundException, SQLException {
    return this.rProduto.read(codigo);
  }

  public void delete(Produto tipo) throws ClassNotFoundException, SQLException {
    this.rProduto.delete(tipo);
  }

  public List<Produto> readAllProduto() throws ClassNotFoundException, SQLException {
    return this.rProduto.readAll();
  }

  public List<Produto> filterProdutoByLote(Lote lote) throws ClassNotFoundException, SQLException {
    return ((ProdutoRepository)rProduto).filterProdutoByLote(lote);
  }

  public List<Lote> filterLoteByDonatarioId(int donatarioId) throws ClassNotFoundException, SQLException {
    return ((LoteRepository)rLote).filterLoteByDonatarioId(donatarioId);
  }

  public List<Lote> filterLoteByFiscalizadorId(int fiscalizadorId) throws ClassNotFoundException, SQLException {
    return ((LoteRepository)rLote).filterLoteByFiscalizadorId(fiscalizadorId);
  }
  public List<Lote> filterLoteByDonatarioIdAndFiscalizadorId(int donatarioId, int fiscalizadorId) throws ClassNotFoundException, SQLException {
    return ((LoteRepository)rLote).filterLoteByDonatarioIdAndFiscalizadorId(donatarioId, fiscalizadorId);
  }
}
