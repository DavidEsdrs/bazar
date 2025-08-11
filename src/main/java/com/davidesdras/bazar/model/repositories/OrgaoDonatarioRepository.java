package com.davidesdras.bazar.model.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.davidesdras.bazar.model.entities.OrgaoDonatario;

public final class OrgaoDonatarioRepository implements Repository<Integer, OrgaoDonatario> {

  @Override
  public void create(OrgaoDonatario c) throws ClassNotFoundException, SQLException {
    String sql = "insert into orgaodonatario(nome,endereco,telefone,horariofuncionamento,descricao) values (?,?,?,?,?)";
    PreparedStatement pstmt = ConnectionManager.getCurrentConnection().prepareStatement(sql);

    pstmt.setString(1, c.getNome());
    pstmt.setString(2, c.getEndereco());
    pstmt.setString(3, c.getTelefone());
    pstmt.setString(4, c.getHorarioFuncionamento());
    pstmt.setString(5, c.getDescricao());

    pstmt.execute();
  }

  @Override
  public void update(OrgaoDonatario c) throws ClassNotFoundException, SQLException {
    String sql = "update orgaodonatario set nome = ?, endereco = ?, telefone = ?, horariofuncionamento = ?, descricao = ? where id = ?";
    PreparedStatement pstmt = ConnectionManager.getCurrentConnection().prepareStatement(sql);

    pstmt.setString(1, c.getNome());
    pstmt.setString(2, c.getEndereco());
    pstmt.setString(3, c.getTelefone());
    pstmt.setString(4, c.getHorarioFuncionamento());
    pstmt.setString(5, c.getDescricao());
    pstmt.setInt(6, c.getId());

    pstmt.executeUpdate();
  }

  @Override
  public OrgaoDonatario read(Integer k) throws ClassNotFoundException, SQLException {
    String sql = "select * from orgaodonatario where id = " + k;

    ResultSet result = ConnectionManager.getCurrentConnection().prepareStatement(sql).executeQuery();

    OrgaoDonatario o = null;

    if (result.next()) {

      o = new OrgaoDonatario();

      o.setId(result.getInt("id"));
      o.setNome(result.getString("nome"));
      o.setEndereco(result.getString("endereco"));
      o.setTelefone(result.getString("telefone"));
      o.setHorarioFuncionamento(result.getString("horariofuncionamento"));
      o.setDescricao(result.getString("descricao"));

    }

    return o;
  }

  @Override
  public void delete(OrgaoDonatario c) throws ClassNotFoundException, SQLException {
    String sql = "delete from orgaodonatario where id = ?";

    PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

    pstm.setInt(1, c.getId());

    pstm.execute();
  }

  @Override
  public List<OrgaoDonatario> readAll() throws ClassNotFoundException, SQLException {
    String sql = "select * from orgaodonatario";

    ResultSet result = ConnectionManager.getCurrentConnection()
        .prepareStatement(sql).executeQuery();

    List<OrgaoDonatario> orgaos = new ArrayList<OrgaoDonatario>();

    while (result.next()) {

      OrgaoDonatario o = new OrgaoDonatario();
      o.setId(result.getInt("id"));
      o.setNome(result.getString("nome"));
      o.setEndereco(result.getString("endereco"));
      o.setTelefone(result.getString("telefone"));
      o.setHorarioFuncionamento(result.getString("horariofuncionamento"));
      o.setDescricao(result.getString("descricao"));

      orgaos.add(o);

    }

    return orgaos;
  }

}
