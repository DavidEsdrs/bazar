package com.davidesdras.bazar.model.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.davidesdras.bazar.model.entities.Lote;
import com.davidesdras.bazar.model.entities.Produto;

public final class ProdutoRepository implements Repository<Integer, Produto> {

  @Override
  public void create(Produto c) throws ClassNotFoundException, SQLException {
    String sql = "insert into produto(nome,descricao,lote_id) values (?,?,?)";
    PreparedStatement pstmt = ConnectionManager.getCurrentConnection().prepareStatement(sql);

    pstmt.setString(1, c.getNome());
    pstmt.setString(2, c.getDescricao());
    pstmt.setInt(3, c.getLote().getId());

    pstmt.execute();
  }

  @Override
  public void update(Produto c) throws ClassNotFoundException, SQLException {
    String sql = "update produto set nome = ?, descricao = ? where codigo = ?";
    PreparedStatement pstmt = ConnectionManager.getCurrentConnection().prepareStatement(sql);

    pstmt.setString(1, c.getNome());
    pstmt.setString(2, c.getDescricao());
    pstmt.setInt(3, c.getCodigo());

    pstmt.executeUpdate();
  }

  @Override
  public Produto read(Integer k) throws ClassNotFoundException, SQLException {
    String sql = "select * from produto where codigo = " + k;

    ResultSet result = ConnectionManager.getCurrentConnection().prepareStatement(sql).executeQuery();

    Produto p = null;

    if (result.next()) {
      p = new Produto();

      p.setCodigo(k);
      p.setNome(result.getString("nome"));
      p.setDescricao(result.getString("descricao"));

      int loteId = result.getInt("lote_id");
      if (!result.wasNull()) {
        Lote lote = new LoteRepository().read(loteId);
        p.setLote(lote);
      }
    }

    return p;
  }

  @Override
  public void delete(Produto c) throws ClassNotFoundException, SQLException {
    String sql = "delete from produto where codigo = ?";

    PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

    pstm.setInt(1, c.getCodigo());

    pstm.execute();
  }

  @Override
  public List<Produto> readAll() throws ClassNotFoundException, SQLException {
    String sql = "select * from produto";

    ResultSet result = ConnectionManager.getCurrentConnection()
        .prepareStatement(sql).executeQuery();

    List<Produto> tipos = new ArrayList<Produto>();

    while (result.next()) {
      Produto p = new Produto();
      p.setCodigo(result.getInt("codigo"));
      p.setNome(result.getString("nome"));
      p.setDescricao(new String(result.getBytes("descricao")));

      int loteId = result.getInt("lote_id");
      if (!result.wasNull()) {
        Lote lote = new LoteRepository().read(loteId);
        p.setLote(lote);
      }

      tipos.add(p);

    }

    return tipos;
  }

  public List<Produto> filterProdutoByLote(Lote lote) throws ClassNotFoundException, SQLException {
    String sql = "select * from produto where lote_id = ?";
    PreparedStatement pstmt = ConnectionManager.getCurrentConnection().prepareStatement(sql);
    pstmt.setInt(1, lote.getId());
    ResultSet result = pstmt.executeQuery();

    List<Produto> produtos = new ArrayList<>();
    while (result.next()) {
      Produto p = new Produto();
      p.setCodigo(result.getInt("codigo"));
      p.setNome(result.getString("nome"));
      p.setDescricao(result.getString("descricao"));
      p.setLote(lote);
      produtos.add(p);
    }
    return produtos;
  }

}
