package fr.algo.com;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import fr.algo.com.gui.MyGUI;
import fr.algo.com.gui.WarningGui;
import fr.algo.com.handler.ConfigHandler;
import fr.algo.com.handler.InitTable;
import fr.algo.com.handler.MySQL;
import fr.algo.com.utils.Config;

/**
 * <b>Zero est la classe représentant un membre du Site du Zéro.</b>
 * <p>
 *   Un membre du SDZ est caractérisé par les informations suivantes :
 *   <ul>
 *     <li>Un identifiant unique attribué définitivement.</li>
 *     <li>Un pseudo, suceptible d'être changé.</li>
 *     <li>Un "level". Il peut être "zéro", newser, validateur, modérateur, etc.</li>
 *   </ul>
 * <p> 	De plus, un Zéro a une liste d'amis Zéro. Le membre pourra ajouter ou enlever des amis à cette liste.
 * @see Main
 * 
 * @author dworkin
 * @version 3.0
 */

public class Main {
	
	/**
     * Initialiser un objet MySQL
     * 
     * @see MySQL#MySQL(String, String, String, String, String)
     */
	public static MySQL database;
	
	
	/**
     * Variable pour connaître l'état de la connexion.
     * 
     */
	public static boolean connected;
	
	 /**
     * Fonction principale du programme.
     * @param args[]
     */
	public static void main(String args[]) {
		
		database = new MySQL(getConfig().getHost(), getConfig().getPort(), getConfig().getDatabase(), getConfig().getUser(), getConfig().getPassword());
		
		if(!connectToDatabase(database)) return;
		
		MyGUI gui = new MyGUI();
	
		gui.setVisible(true); 
		
	}
	
	  /**
     * Retourne le fichier de configuration.
     * 
     * @return Une instance de Config, qui correspond à un fichier configuration.
     * 
     * @see Config
     */
	private static Config getConfig() {
		
		ConfigHandler handler = null;
		try {
			handler = ConfigHandler.getInstance();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	    return handler.getConfig();
		
	}
	
	 /**
     * Fonction de connexion à la base de donnée.
     * 
     * @return True si la connexion est réussie, False si elle a échoué.
     * 
     * @see MySQL
     */
	private static boolean connectToDatabase(MySQL datebase){
        
		 try
		    {
		      database.openConnection();
		      System.out.println("Connexion à la base de données réalisée avec succès.");
		      InitTable.initTable();
		      return true;
		      
		    }
		    catch (ClassNotFoundException | SQLException e)
		    {
		      new WarningGui("Connexion à la base de données échouée").setVisible(true);
		      System.out.println("Impossible de se connecter à la base de données :");
		      e.printStackTrace();
		    } 
		return false;
	}
	
}