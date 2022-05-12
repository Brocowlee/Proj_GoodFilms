package fr.algo.com;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import fr.algo.com.gui.MyGUI;
import fr.algo.com.gui.WarningGui;
import fr.algo.com.handler.ConfigHandler;
import fr.algo.com.handler.InitTable;
import fr.algo.com.handler.MySQL;
import fr.algo.com.utils.Config;

public class Main {
	
	public static MySQL database;
	public static boolean connected;
	
	public static void main(String args[]) {
		
		database = new MySQL(getConfig().getHost(), getConfig().getPort(), getConfig().getDatabase(), getConfig().getUser(), getConfig().getPassword());
		
		if(!connectToDatabase(database)) return;
		
		/*String hashed = BCrypt.hashpw("test", "$2a$10$8lYJu9EHPliYUhAsSwSJnYgQ");
		
		String candidate = "$2a$10$8lYJu9EHPliYUhAsSwSJnO/7mvBNBa9pMu.jt3tzHya39ELE7juA.";
		
		System.out.println("Java " + hashed);
		System.out.println("PHP " + candidate);
		
		
		if(candidate.equalsIgnoreCase(hashed))
			System.out.println("It matches");
		else
			System.out.println("It does not match");  */
		
		
		MyGUI gui = new MyGUI();
	
		gui.setVisible(true); 
		
	}
	
	private static Config getConfig() {
		
		ConfigHandler handler = null;
		try {
			handler = ConfigHandler.getInstance();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	    return handler.getConfig();
		
	}
	
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