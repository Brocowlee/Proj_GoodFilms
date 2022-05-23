package fr.algo.com.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * <b>Classe MySQL de connexion sql</b>
 * <p>
 *   Cette classe va permettre de réaliser les connexions à la base de données
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
   * Nom de la base de données
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
 	* @param hostname String représentant l'host
 	* @param port String représentant le port
 	* @param database String représentant le nom de la base de données
 	* @param username String représentant le nom de l'utilisateur
 	* @param password String représentant le mot de passe
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
  * Retourne une connexion à une base de données
  * @throws SQLException si une requête sql est érronée
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


