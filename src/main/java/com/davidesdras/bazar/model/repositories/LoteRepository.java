package com.davidesdras.bazar.model.repositories;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.davidesdras.bazar.model.entities.Lote;
import com.davidesdras.bazar.model.entities.OrgaoDonatario;
import com.davidesdras.bazar.model.entities.OrgaoFiscalizador;
import com.davidesdras.bazar.model.entities.Produto;

public final class LoteRepository implements Repository<Integer, Lote> {

  @Override
  public void create(Lote c) throws ClassNotFoundException, SQLException {
    String sql = "insert into lote(data_entrega,observacao,orgao_fiscalizador_id,orgao_donatario_id) values (?,?,?,?)";
    PreparedStatement pstmt = ConnectionManager.getCurrentConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

    Date d = new Date(c.getDataEntrega().getTime());
    pstmt.setDate(1, d);
    pstmt.setString(2, c.getObservacao());
    pstmt.setInt(3, c.getFiscalizador().getId());
    pstmt.setInt(4, c.getDonatario().getId());

    pstmt.executeUpdate();

    ResultSet generatedKeys = pstmt.getGeneratedKeys();
    if (generatedKeys.next()) {
      int loteId = generatedKeys.getInt(1);
      c.setId(loteId);
    }

    if (c.getDoacoes().size() > 0) {
      RepositoryFacade facade = RepositoryFacade.getCurrentInstance();
      for (Produto p : c.getDoacoes()) {
        p.setLote(c);
        facade.create(p);
      }
    }
  }

  @Override
  public void update(Lote c) throws ClassNotFoundException, SQLException {
    String sql = "update lote set data_entrega = ?, observacao = ?, orgao_fiscalizador_id = ?, orgao_donatario_id = ? where id = ?";
    PreparedStatement pstmt = ConnectionManager.getCurrentConnection().prepareStatement(sql);
    java.sql.Date d = new java.sql.Date(c.getDataEntrega().getTime());
    pstmt.setDate(1, d);
    pstmt.setString(2, c.getObservacao());
    pstmt.setInt(3, c.getFiscalizador().getId());
    pstmt.setInt(4, c.getDonatario().getId());
    pstmt.setInt(5, c.getId());
    pstmt.executeUpdate();
  }

  @Override
  public Lote read(Integer k) throws ClassNotFoundException, SQLException {
    String sql = "select " +
    "l.id as lote_id, l.data_entrega as lote_data_entrega, l.observacao as lote_observacao, " +
    "d.id as donatario_id, d.nome as donatario_nome, d.endereco as donatario_endereco, d.telefone as donatario_telefone, d.horariofuncionamento as donatario_horariofuncionamento, d.descricao as donatario_descricao, " +
    "f.id as fiscalizador_id, f.nome as fiscalizador_nome, f.descricao as fiscalizador_descricao " +
    "from lote l " +
    "inner join orgaodonatario d on l.orgao_donatario_id = d.id " +
    "inner join orgaofiscalizador f on l.orgao_fiscalizador_id = f.id " +
    "where l.id = " + k;
    ResultSet result = ConnectionManager.getCurrentConnection().prepareStatement(sql).executeQuery();
    Lote l = null;

    if (result.next()) {
      l = new Lote();
      l.setId(result.getInt("lote_id"));
      l.setDataEntrega(result.getDate("lote_data_entrega"));
      l.setObservacao(result.getString("lote_observacao"));

      OrgaoDonatario donatario = new OrgaoDonatario();
      donatario.setId(result.getInt("donatario_id"));
      donatario.setNome(result.getString("donatario_nome"));
      donatario.setEndereco(result.getString("donatario_endereco"));
      donatario.setTelefone(result.getString("donatario_telefone"));
      donatario.setHorarioFuncionamento(result.getString("donatario_horariofuncionamento"));
      donatario.setDescricao(result.getString("donatario_descricao"));
      l.setDonatario(donatario);

      OrgaoFiscalizador fiscalizador = new com.davidesdras.bazar.model.entities.OrgaoFiscalizador();
      fiscalizador.setId(result.getInt("fiscalizador_id"));
      fiscalizador.setNome(result.getString("fiscalizador_nome"));
      fiscalizador.setDescricao(result.getString("fiscalizador_descricao"));
      l.setFiscalizador(fiscalizador);

      List<Produto> produtos = RepositoryFacade.getCurrentInstance().filterProdutoByLote(l);
      l.setDoacoes(produtos);
    }
    return l;
  }

  @Override
  public void delete(Lote c) throws ClassNotFoundException, SQLException {
    String sqlProdutos = "delete from produto where lote_id = ?";
    PreparedStatement pstmProdutos = ConnectionManager.getCurrentConnection().prepareStatement(sqlProdutos);
    pstmProdutos.setInt(1, c.getId());
    pstmProdutos.execute();

    String sqlLote = "delete from lote where id = ?";
    PreparedStatement pstmLote = ConnectionManager.getCurrentConnection().prepareStatement(sqlLote);
    pstmLote.setInt(1, c.getId());
    pstmLote.execute();
  }

  @Override
  public List<Lote> readAll() throws ClassNotFoundException, SQLException {
    String sql = "select " +
    "l.id as lote_id, l.data_entrega as lote_data_entrega, l.observacao as lote_observacao, " +
    "d.id as donatario_id, d.nome as donatario_nome, d.endereco as donatario_endereco, d.telefone as donatario_telefone, d.horariofuncionamento as donatario_horariofuncionamento, d.descricao as donatario_descricao, " +
    "f.id as fiscalizador_id, f.nome as fiscalizador_nome, f.descricao as fiscalizador_descricao " +
    "from lote l " +
    "inner join orgaodonatario d on l.orgao_donatario_id = d.id " +
    "inner join orgaofiscalizador f on l.orgao_fiscalizador_id = f.id ";
    ResultSet result = ConnectionManager.getCurrentConnection().prepareStatement(sql).executeQuery();
    List<Lote> lotes = new ArrayList<>();
    while (result.next()) {
      Lote l = new Lote();
      l.setId(result.getInt("lote_id"));
      l.setDataEntrega(result.getDate("lote_data_entrega"));
      l.setObservacao(result.getString("lote_observacao"));

      OrgaoDonatario donatario = new OrgaoDonatario();
      donatario.setId(result.getInt("donatario_id"));
      donatario.setNome(result.getString("donatario_nome"));
      donatario.setEndereco(result.getString("donatario_endereco"));
      donatario.setTelefone(result.getString("donatario_telefone"));
      donatario.setHorarioFuncionamento(result.getString("donatario_horariofuncionamento"));
      donatario.setDescricao(result.getString("donatario_descricao"));
      l.setDonatario(donatario);

      OrgaoFiscalizador fiscalizador = new com.davidesdras.bazar.model.entities.OrgaoFiscalizador();
      fiscalizador.setId(result.getInt("fiscalizador_id"));
      fiscalizador.setNome(result.getString("fiscalizador_nome"));
      fiscalizador.setDescricao(result.getString("fiscalizador_descricao"));
      l.setFiscalizador(fiscalizador);

      lotes.add(l);
    }

    return lotes;
  }


  public List<Lote> filterLoteByDonatarioId(int donatarioId) throws ClassNotFoundException, SQLException {
    String sql = "select " +
      "l.id as lote_id, l.data_entrega as lote_data_entrega, l.observacao as lote_observacao, " +
      "d.id as donatario_id, d.nome as donatario_nome, d.endereco as donatario_endereco, d.telefone as donatario_telefone, d.horariofuncionamento as donatario_horariofuncionamento, d.descricao as donatario_descricao, " +
      "f.id as fiscalizador_id, f.nome as fiscalizador_nome, f.descricao as fiscalizador_descricao " +
      "from lote l " +
      "inner join orgaodonatario d on l.orgao_donatario_id = d.id " +
      "inner join orgaofiscalizador f on l.orgao_fiscalizador_id = f.id " +
      "where d.id = ?";
    PreparedStatement pstmt = ConnectionManager.getCurrentConnection().prepareStatement(sql);
    pstmt.setInt(1, donatarioId);
    ResultSet result = pstmt.executeQuery();
    List<Lote> lotes = new ArrayList<>();
    while (result.next()) {
      Lote l = new Lote();
      l.setId(result.getInt("lote_id"));
      l.setDataEntrega(result.getDate("lote_data_entrega"));
      l.setObservacao(result.getString("lote_observacao"));

      OrgaoDonatario d = new OrgaoDonatario();
      d.setId(result.getInt("donatario_id"));
      d.setNome(result.getString("donatario_nome"));
      d.setEndereco(result.getString("donatario_endereco"));
      d.setTelefone(result.getString("donatario_telefone"));
      d.setHorarioFuncionamento(result.getString("donatario_horariofuncionamento"));
      d.setDescricao(result.getString("donatario_descricao"));
      l.setDonatario(d);

      OrgaoFiscalizador f = new OrgaoFiscalizador();
      f.setId(result.getInt("fiscalizador_id"));
      f.setNome(result.getString("fiscalizador_nome"));
      f.setDescricao(result.getString("fiscalizador_descricao"));
      l.setFiscalizador(f);

      List<Produto> produtos = RepositoryFacade.getCurrentInstance().filterProdutoByLote(l);
      l.setDoacoes(produtos);

      lotes.add(l);
    }
    return lotes;
  }

  public List<Lote> filterLoteByFiscalizadorId(int fiscalizadorId) throws ClassNotFoundException, SQLException {
    String sql = "select " +
      "l.id as lote_id, l.data_entrega as lote_data_entrega, l.observacao as lote_observacao, " +
      "d.id as donatario_id, d.nome as donatario_nome, d.endereco as donatario_endereco, d.telefone as donatario_telefone, d.horariofuncionamento as donatario_horariofuncionamento, d.descricao as donatario_descricao, " +
      "f.id as fiscalizador_id, f.nome as fiscalizador_nome, f.descricao as fiscalizador_descricao " +
      "from lote l " +
      "inner join orgaodonatario d on l.orgao_donatario_id = d.id " +
      "inner join orgaofiscalizador f on l.orgao_fiscalizador_id = f.id " +
      "where f.id = ?";
    PreparedStatement pstmt = ConnectionManager.getCurrentConnection().prepareStatement(sql);
    pstmt.setInt(1, fiscalizadorId);
    ResultSet result = pstmt.executeQuery();
    List<Lote> lotes = new ArrayList<>();
    while (result.next()) {
      Lote l = new Lote();
      l.setId(result.getInt("lote_id"));
      l.setDataEntrega(result.getDate("lote_data_entrega"));
      l.setObservacao(result.getString("lote_observacao"));

      OrgaoDonatario d = new OrgaoDonatario();
      d.setId(result.getInt("donatario_id"));
      d.setNome(result.getString("donatario_nome"));
      d.setEndereco(result.getString("donatario_endereco"));
      d.setTelefone(result.getString("donatario_telefone"));
      d.setHorarioFuncionamento(result.getString("donatario_horariofuncionamento"));
      d.setDescricao(result.getString("donatario_descricao"));
      l.setDonatario(d);

      OrgaoFiscalizador f = new OrgaoFiscalizador();
      f.setId(result.getInt("fiscalizador_id"));
      f.setNome(result.getString("fiscalizador_nome"));
      f.setDescricao(result.getString("fiscalizador_descricao"));
      l.setFiscalizador(f);

      List<Produto> produtos = RepositoryFacade.getCurrentInstance().filterProdutoByLote(l);
      l.setDoacoes(produtos);

      lotes.add(l);
    }
    return lotes;
  }
  public List<Lote> filterLoteByDonatarioIdAndFiscalizadorId(int donatarioId, int fiscalizadorId) throws ClassNotFoundException, SQLException {
    String sql = "select " +
      "l.id as lote_id, l.data_entrega as lote_data_entrega, l.observacao as lote_observacao, " +
      "d.id as donatario_id, d.nome as donatario_nome, d.endereco as donatario_endereco, d.telefone as donatario_telefone, d.horariofuncionamento as donatario_horariofuncionamento, d.descricao as donatario_descricao, " +
      "f.id as fiscalizador_id, f.nome as fiscalizador_nome, f.descricao as fiscalizador_descricao " +
      "from lote l " +
      "inner join orgaodonatario d on l.orgao_donatario_id = d.id " +
      "inner join orgaofiscalizador f on l.orgao_fiscalizador_id = f.id " +
      "where d.id = ? and f.id = ?";
    PreparedStatement pstmt = ConnectionManager.getCurrentConnection().prepareStatement(sql);
    pstmt.setInt(1, donatarioId);
    pstmt.setInt(2, fiscalizadorId);
    ResultSet result = pstmt.executeQuery();
    List<Lote> lotes = new ArrayList<>();
    while (result.next()) {
      Lote l = new Lote();
      l.setId(result.getInt("lote_id"));
      l.setDataEntrega(result.getDate("lote_data_entrega"));
      l.setObservacao(result.getString("lote_observacao"));

      OrgaoDonatario d = new OrgaoDonatario();
      d.setId(result.getInt("donatario_id"));
      d.setNome(result.getString("donatario_nome"));
      d.setEndereco(result.getString("donatario_endereco"));
      d.setTelefone(result.getString("donatario_telefone"));
      d.setHorarioFuncionamento(result.getString("donatario_horariofuncionamento"));
      d.setDescricao(result.getString("donatario_descricao"));
      l.setDonatario(d);

      OrgaoFiscalizador f = new OrgaoFiscalizador();
      f.setId(result.getInt("fiscalizador_id"));
      f.setNome(result.getString("fiscalizador_nome"));
      f.setDescricao(result.getString("fiscalizador_descricao"));
      l.setFiscalizador(f);

      List<Produto> produtos = RepositoryFacade.getCurrentInstance().filterProdutoByLote(l);
      l.setDoacoes(produtos);

      lotes.add(l);
    }
    return lotes;
  }

  
}
