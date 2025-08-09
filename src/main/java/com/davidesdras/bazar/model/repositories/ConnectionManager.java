package com.davidesdras.bazar.model.repositories;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

  private static final String URL = "jdbc:mysql://localhost:3306/bazar";
  private static final String USER = "bazaruser";
  private static final String PASSWORD = "bazarpass";

  private static Connection currentConnection = null;

  public static Connection getCurrentConnection() throws SQLException, ClassNotFoundException {
    Class.forName("com.mysql.cj.jdbc.Driver");

    if (currentConnection == null) {
      currentConnection = DriverManager.getConnection(URL, USER, PASSWORD);
    }
    return currentConnection;
  }

  public static Connection getConnection() throws SQLException {

    return DriverManager.getConnection(URL, USER, PASSWORD);

  }

}
