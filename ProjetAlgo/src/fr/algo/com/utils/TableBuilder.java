package fr.algo.com.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.algo.com.Main;
import fr.algo.com.object.Column;

/**
 * <b>Classe permettant de créer de nouvelles tables</b>
 * 
 * @author Thomas, Benjamin
 * @version 1.0
 */

public class TableBuilder {

	/**
     * Nom de l'instance
     */
	private String name;
	
	/**
     * Liste des colonnes
     * 
     * @see Column
     */
	private ArrayList<Column> list_columns = new ArrayList<>();
	
	/**
	* Constructeur de la classe
	* @param name nom de la classe
	*/
	public TableBuilder(String name) {
		
		this.name = name;
	}
	
	/**
	 * Méthode qui ajoute une instance de colonne à la liste totale de colonnes
     * @param column correspond à une instance de colonne
	 */
	public void addColumn(Column column) {
		
		list_columns.add(column);
		
	}
	
	/**
	 * Méthode qui envoie une requête sql au serveur
	 */
	public void build() {
		
		  try {
			  
			Main.database.updateSQL(writeRequest(list_columns));
			
		  } catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	  /**
     * Retourne la requête sql de création de table
     * @param list_columns une liste de colonnes
     * @return un String, qui correspond à une requête
     * @see Column
     */
	public String writeRequest(List<Column> list_columns) {
		
		ArrayList<String> primary_keys = new ArrayList<>();
		String primary = "";
		String request = "CREATE TABLE IF NOT EXISTS `" + this.name + "` ( "; 
		
		for(Column column : list_columns) {
			
				request += "`" + column.getName() + "` " + column.getType();
				
				if(column.hasDefaultValue()) {
					request += " DEFAULT '" + column.getDefaultValue() + "'";
				}
				if(column.isNotNull()) {
					request += " NOT NULL";
				}
				if(column.isAutoIncrement()) {
					request += " AUTO_INCREMENT";
				}
				if(column.isPrimary()) {
					primary_keys.add(column.getName());
				}
				if(!(list_columns.get(list_columns.size() - 1) == column)) {
					request += ", ";
				} else {
					request += " ";
				}
				
		}
		
		for(String prim : primary_keys) {
			
			if(!(primary_keys.get(primary_keys.size() - 1) == prim)) {
				primary += "`" + prim + "`, ";
			} else {
				primary += "`" + prim + "`";
			}
			
		}
		
		String end_request = "";
		if(primary.isBlank()) {
			end_request = ") ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;"  ;
		} else {
			end_request = ", PRIMARY KEY (" + primary + ") ) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;"  ;
		}
		
		String final_request = request += end_request;
		
		return final_request;
	}
	
}
