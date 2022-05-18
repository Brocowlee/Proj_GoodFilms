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
 * <b>Classe principale du projet</b>
 * <p>
 *   Cette classe va réaliser la connexion à la base de données puis ouvrir le gui principal
 * @see Main
 * 
 * @author Thomas, Benjamin
 * @version 1.0
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
		
		if(!connectToDatabase()) return;
		
		MyGUI gui = new MyGUI();
	
		gui.setVisible(true); 
		
	}
	
	  /**
     * Retourne le fichier de configuration.
     * 
     * @return Une instance de Config, qui correspond à un fichier configuration.
     * 
     * @throws FileNotFoundException si le fichier est introuvable
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
     * @throws ClassNotFoundException si la classe est introuvable
     * @throws SQLException si la requête sql n'a pas fonctionné
     * @see MySQL
     */
	private static boolean connectToDatabase(){
        
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