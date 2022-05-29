package fr.algo.com.handler;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.algo.com.Main;
import fr.algo.com.object.TableObject;

/**
 * <b>Classe d'initialisation d'instances de TableObject</b>
 * @see TableObject
 * <p>
 *   Cette classe va réaliser les fonctions liées à l'initialisation des tables
 * 
 * @author Thomas, Benjamin
 * @version 1.0
 */
public class InitTable {
	
	/**
     * Récupérer l'instance de l'objet MySQL se situant dans le main
     * @see MySQL
     * @see Main
     */
	public static MySQL database = Main.database;
	
	/**
     * Dictionnaire des différentes tables
     * Comme clé : String correspondant au nom de la table
     * Comme valeur : une instance TableObject
     * @see TableObject
     */
	public static HashMap <String, TableObject> liste_tables = new HashMap<>();
	
	/**
	  * Retourne une liste des noms des tables
	  * @return une liste de String
	  */
	public static List<String> getListOfTable(){
				
			List<String> tables = new ArrayList<>();
		
            try {
            		DatabaseMetaData md = database.connexion.getMetaData();
            		String[] types = {"TABLE"};
            		ResultSet rs = md.getTables(null, null, "%", types);
            		
            		while (rs.next()) {
            			tables.add(rs.getString("TABLE_NAME"));
            		}
                                   
                    } catch (SQLException e) {
                            e.printStackTrace();
                    }
            
            return tables;
           
	}
	
	/**
	  * Méthode pour intitaliser les instances de TableObject à partir de requêtes sql
	  * @see TableObject
	  */
	public static void initTable() {
		
		liste_tables.clear();
		
		 try {
     		DatabaseMetaData md = database.connexion.getMetaData();
     		String[] types = {"TABLE"};
     		ResultSet rs = md.getTables(null, null, "%", types);
     		
     		while (rs.next()) {
     			 if(!rs.getString("TABLE_NAME").contentEquals("sys_config")) {
     				 TableObject table = new TableObject(rs.getString("TABLE_NAME")) ;
         			 initColumn(table);
     			 }
     		}
                            
             } catch (SQLException e) {
                     e.printStackTrace();
             }
		
	}
	
	/**
	  * Méthode pour intitaliser les listes de colonnes dans les instances TableObject
	  * @param table une instance de TableObject
	  * @see TableObject
	  */
	public static void initColumn(TableObject table){
		
		try {
			DatabaseMetaData metadata = database.connexion.getMetaData();
		    ResultSet resultSet = metadata.getColumns(null, null, table.getName(), null);
		    
		    while (resultSet.next()) {
		    	String name = resultSet.getString("COLUMN_NAME");
		    	String type = resultSet.getString("TYPE_NAME");
		    	
		    	table.addColumn(name, type);

		      }   
		    
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		liste_tables.put(table.getName(), table);
		
	}
	
	
}
