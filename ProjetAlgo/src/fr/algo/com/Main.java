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
 * <b>Zero est la classe repr�sentant un membre du Site du Z�ro.</b>
 * <p>
 *   Un membre du SDZ est caract�ris� par les informations suivantes :
 *   <ul>
 *     <li>Un identifiant unique attribu� d�finitivement.</li>
 *     <li>Un pseudo, suceptible d'�tre chang�.</li>
 *     <li>Un "level". Il peut �tre "z�ro", newser, validateur, mod�rateur, etc.</li>
 *   </ul>
 * <p> 	De plus, un Z�ro a une liste d'amis Z�ro. Le membre pourra ajouter ou enlever des amis � cette liste.
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
     * Variable pour conna�tre l'�tat de la connexion.
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
     * @return Une instance de Config, qui correspond � un fichier configuration.
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
     * Fonction de connexion � la base de donn�e.
     * 
     * @return True si la connexion est r�ussie, False si elle a �chou�.
     * 
     * @see MySQL
     */
	private static boolean connectToDatabase(MySQL datebase){
        
		 try
		    {
		      database.openConnection();
		      System.out.println("Connexion � la base de donn�es r�alis�e avec succ�s.");
		      InitTable.initTable();
		      return true;
		      
		    }
		    catch (ClassNotFoundException | SQLException e)
		    {
		      new WarningGui("Connexion � la base de donn�es �chou�e").setVisible(true);
		      System.out.println("Impossible de se connecter � la base de donn�es :");
		      e.printStackTrace();
		    } 
		return false;
	}
	
}