package fr.algo.com.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MySQL
  extends Database
{
  private String user;
  private String database;
  private String password;
  private String port;
  private String hostname;
  
  public MySQL(String hostname, String port, String database, String username, String password)
  {
    this.hostname = hostname;
    this.port = port;
    this.database = database;
    this.user = username;
    this.password = password;
  }
  
  public Connection openConnection()
    throws SQLException, ClassNotFoundException
  {
    if (checkConnection()) {
      return this.connection;
    }
    Class.forName("com.mysql.cj.jdbc.Driver");
    this.connection = DriverManager.getConnection("jdbc:mysql://" + this.hostname + ":" + this.port + "/" + this.database, this.user, this.password);
    return this.connection;
  }
}


