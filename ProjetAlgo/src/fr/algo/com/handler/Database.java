package fr.algo.com.handler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <b>Classe abstraite r�alisant des interactions avec une base de donn�es</b>
 * 
 * @author Thomas
 * @version 1.0
 */

public abstract class Database {
	
  public Connection connection;
  
  protected Database()
  {
    this.connection = null;
  }
  
  	 /**
	 * M�thode abstraite pour ouvrir une connexion
	 * @throws SQLException erreur dans la requ�te sql
	 * @throws ClassNotFoundException classe introuvable
	 * @return une instance Connection
	 */
  public abstract Connection openConnection()
    throws SQLException, ClassNotFoundException;
  
  	 /**
 	 * M�thode retournant un boolean 
 	 * @return true si la connexion est active, false sinon
 	 * @throws SQLException erreur dans la requ�te sql
 	 */
  public boolean checkConnection()
    throws SQLException
  {
    return (this.connection != null) && (!this.connection.isClosed());
  }
  
  	 /**
	 * M�thode retournant la connexio, 
	 * @return une instance Connection
	 */
  public Connection getConnection()
  {
    return this.connection;
  }
  
  	/**
	 * M�thode retournant un boolean 
	 * @return true si la connexion a bien �t� ferm�, false sinon
	 * @throws SQLException erreur dans la requ�te sql
	 */
  public boolean closeConnection()
    throws SQLException
  {
    if (this.connection == null) {
      return false;
    }
    this.connection.close();
    return true;
  }
  
  	/**
	 * M�thode pour executer des requ�tes sql
	 * @return un ResultSet
	 * @param query String correspondant � la requ�te
	 * @throws SQLException erreur dans la requ�te sql
	 * @throws ClassNotFoundException classe introuvable
	 */
  public ResultSet querySQL(String query)
    throws SQLException, ClassNotFoundException
  {
    if (!checkConnection()) {
      openConnection();
    }
    Statement statement = this.connection.createStatement();
    
    ResultSet result = statement.executeQuery(query);
    
    return result;
  }
  
  /**
	 * M�thode pour mettre � jour une base de donn�es � partir requ�tes sql
	 * @return un entier
	 * @param query String correspondant � la requ�te
	 * @throws SQLException erreur dans la requ�te sql
	 * @throws ClassNotFoundException classe introuvable
	 */
  public int updateSQL(String query)
    throws SQLException, ClassNotFoundException
  {
    if (!checkConnection()) {
      openConnection();
    }
    Statement statement = this.connection.createStatement();
    
    int result = statement.executeUpdate(query);
    
    return result;
  }
}