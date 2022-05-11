package fr.algo.com;

import java.io.FileNotFoundException;
import java.sql.SQLException;

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