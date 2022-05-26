package fr.algo.com.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * <b>Classe MySQL de connexion sql</b>
 * <p>
 *   Cette classe va permettre de r�aliser les connexions � la base de donn�es
 *   Cette classe utilise le driver jdbc
 * 
 * @author Thomas, Benjamin
 * @version 1.0
 */
public class MySQL extends Database {
	
	/**
     * Nom de l'utilisateur
     */
  private String user;
  
  /**
   * Nom de la base de donn�es
   */
  private String database;
  
  /**
   * Valeur du mot de passe
   */
  private String password;
  
  /**
   * Valeur du port
   */
  private String port;
  
  /**
   * Nom de l'host
   */
  private String hostname;
  
  /**
 	* Constructeur de la classe
 	* @param hostname String repr�sentant l'host
 	* @param port String repr�sentant le port
 	* @param database String repr�sentant le nom de la base de donn�es
 	* @param username String repr�sentant le nom de l'utilisateur
 	* @param password String repr�sentant le mot de passe
 	*/
  public MySQL(String hostname, String port, String database, String username, String password)
  {
    this.hostname = hostname;
    this.port = port;
    this.database = database;
    this.user = username;
    this.password = password;
  }
  

  /**
  * Retourne une connexion � une base de donn�es
  * @throws SQLException si une requ�te sql est �rron�e
  * @throws ClassNotFoundException si une classe est introuvable
  * @return une instance de Connection, qui renvoie un objet Connection
  */
  public Connection openConnection()
    throws SQLException, ClassNotFoundException
  {
    if (checkConnection()) {
      return this.connexion;
    }
    Class.forName("com.mysql.cj.jdbc.Driver");
    this.connexion = DriverManager.getConnection("jdbc:mysql://" + this.hostname + ":" + this.port + "/" + this.database, this.user, this.password);
    return this.connexion;
  }
}


