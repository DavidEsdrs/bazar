package com.davidesdras.bazar.model.repositories;

import java.sql.SQLException;
import java.util.List;

public interface Repository<K, C> {
  public void create(C c) throws ClassNotFoundException, SQLException;
  public void update(C c) throws ClassNotFoundException, SQLException;
  public C read(K k) throws ClassNotFoundException, SQLException;
  public void delete(C c) throws ClassNotFoundException, SQLException;
  public List<C> readAll() throws ClassNotFoundException, SQLException;
}
