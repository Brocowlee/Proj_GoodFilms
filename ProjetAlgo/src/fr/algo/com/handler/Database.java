package fr.algo.com.handler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <b>Classe abstraite réalisant des interactions avec une base de données</b>
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
	 * Méthode abstraite pour ouvrir une connexion
	 * @throws SQLException erreur dans la requête sql
	 * @throws ClassNotFoundException classe introuvable
	 * @return une instance Connection
	 */
  public abstract Connection openConnection()
    throws SQLException, ClassNotFoundException;
  
  	 /**
 	 * Méthode retournant un boolean 
 	 * @return true si la connexion est active, false sinon
 	 * @throws SQLException erreur dans la requête sql
 	 */
  public boolean checkConnection()
    throws SQLException
  {
    return (this.connection != null) && (!this.connection.isClosed());
  }
  
  	 /**
	 * Méthode retournant la connexio, 
	 * @return une instance Connection
	 */
  public Connection getConnection()
  {
    return this.connection;
  }
  
  	/**
	 * Méthode retournant un boolean 
	 * @return true si la connexion a bien été fermé, false sinon
	 * @throws SQLException erreur dans la requête sql
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
	 * Méthode pour executer des requêtes sql
	 * @return un ResultSet
	 * @param query String correspondant à la requête
	 * @throws SQLException erreur dans la requête sql
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
	 * Méthode pour mettre à jour une base de données à partir requêtes sql
	 * @return un entier
	 * @param query String correspondant à la requête
	 * @throws SQLException erreur dans la requête sql
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