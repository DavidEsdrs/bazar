package com.davidesdras.bazar.model.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.davidesdras.bazar.model.entities.OrgaoFiscalizador;

public final class OrgaoFiscalizadorRepository implements Repository<Integer, OrgaoFiscalizador> {
  @Override
  public void create(OrgaoFiscalizador c) throws ClassNotFoundException, SQLException {
    String sql = "insert into orgaofiscalizador(id, nome, descricao) values (?, ?, ?)";
    PreparedStatement pstmt = ConnectionManager.getCurrentConnection().prepareStatement(sql);

    pstmt.setInt(1, c.getId());
    pstmt.setString(2, c.getNome());
    pstmt.setString(3, c.getDescricao());

    pstmt.execute();
  }

  @Override
  public void update(OrgaoFiscalizador c) throws ClassNotFoundException, SQLException {
    String sql = "update orgaofiscalizador set nome=?, descricao=? where id=?";
    PreparedStatement pstmt = ConnectionManager.getCurrentConnection().prepareStatement(sql);

    pstmt.setString(1, c.getNome());
    pstmt.setString(2, c.getDescricao());
    pstmt.setInt(3, c.getId());

    pstmt.executeUpdate();
  }

  @Override
  public OrgaoFiscalizador read(Integer k) throws ClassNotFoundException, SQLException {
    String sql = "select * from orgaofiscalizador where id = " + k;

    ResultSet result = ConnectionManager.getCurrentConnection().prepareStatement(sql).executeQuery();

    OrgaoFiscalizador o = null;

    if (result.next()) {
      o = new OrgaoFiscalizador();
      o.setId(result.getInt("id"));
      o.setNome(result.getString("nome"));
      o.setDescricao(result.getString("descricao"));
    }

    return o;
  }

  @Override
  public void delete(OrgaoFiscalizador c) throws ClassNotFoundException, SQLException {
    String sql = "delete from orgaofiscalizador where id = ?";

    PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

    pstm.setInt(1, c.getId());

    pstm.execute();
  }

  @Override
  public List<OrgaoFiscalizador> readAll() throws ClassNotFoundException, SQLException {
    String sql = "select * from orgaofiscalizador";

    ResultSet result = ConnectionManager.getCurrentConnection()
        .prepareStatement(sql).executeQuery();

    List<OrgaoFiscalizador> orgaos = new ArrayList<OrgaoFiscalizador>();

    while (result.next()) {
      OrgaoFiscalizador o = new OrgaoFiscalizador();
      o.setId(result.getInt("id"));
      o.setNome(result.getString("nome"));
      o.setDescricao(result.getString("descricao"));

      orgaos.add(o);
    }

    return orgaos;
  }
}
