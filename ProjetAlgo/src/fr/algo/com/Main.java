package fr.algo.com;

import java.sql.SQLException;

import fr.algo.com.gui.MyGUI;
import fr.algo.com.gui.containers.ContainerAdmin;
import fr.algo.com.handler.InitTable;
import fr.algo.com.handler.MySQL;
import fr.algo.com.object.TableObject;

public class Main {
	
	public static MySQL database;
	
	public static void main(String args[]) {
		
		database = new MySQL("localhost", "3306", "gueriotb", "root", "");
		
		connectToDatabase(database);
		
		MyGUI gui = new MyGUI();
	
		gui.setVisible(true);
		
	}
	
	private static void connectToDatabase(MySQL datebase){
		
		 try
		    {
		      database.openConnection();
		    
		      
		      System.out.println("Connexion � la base de donn�es r�alis�e avec succ�s.");
		      
		      
		      InitTable.initTable();
		      
		      for(TableObject to : InitTable.liste_tables.values()) {
		    	  //System.out.println(to.selectAll());
		    	  //System.out.println(to.getName());
		    	  //System.out.println(to.getTotalLine());
		    	  System.out.println(to.selectAll());
		      } 
		      
		      /*TableBuilder tb = new TableBuilder("test3");
		      tb.addColumn();
		      tb.build(); */
		      
		      
		    }
		    catch (ClassNotFoundException |SQLException e)
		    {
		      System.out.println("Impossible de se connecter � la base de donn�es :");
		      e.printStackTrace();
		      return;
		    } 
		
	}
}