package fr.algo.com.object;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import fr.algo.com.Main;
import fr.algo.com.handler.InitTable;

/**
 * <b>Classe d'une table</b>
 * <p>
 *   Cette classe permet de réaliser des instances de tables
 * 
 * @author Thomas, Benjamin
 * @version 1.0
 */
public class TableObject {
	
	/**
     * Nom de la table
     */
	private String name;
	
	/**
     * Dictionnaire avec comme clé un nom de colonne et comme valeur son type
     * Dictionnaire avec un ordre d'itération connu
     */
	private LinkedHashMap<String, String> informations = new LinkedHashMap<>();
	
	/**
	* Constructeur de la classe
	* @param table_name nom de la table
	*/
	public TableObject(String table_name) {
		this.name = table_name;
	}
	
	/**
	 * Méthode qui ajoute une colonne à son dictionnaire de colonnes
     * @param column_name un nom de colonne
     * @param column_type un type de colonne
	 */
	public void addColumn(String column_name, String column_type) {
		this.informations.put(column_name, column_type);
	}
	
	 /**
     * Retourne le nom de la table
     * @return un String, qui correspond au nom
     */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Méthode qui supprime la table
	 */
	public void deleteTable() {
		
		InitTable.liste_tables.remove(this.name);
		
		try {
			Main.database.updateSQL("DROP TABLE if exists " + this.name + ";");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	 /**
     * Retourne la collection des clés du dictionnaire
     * @return une collection de String
     */
	public Set<String> getColumnName(){
		
		return this.informations.keySet();
		
	}
	
	/**
	 * Méthode qui insert des valeurs dans la table
     * @param indexes liste d'entier 
     * @param value liste de String
     * @return true si la requête a fonctionné, false sinon
	 */
	public boolean insertInto(List<Integer> indexes, ArrayList<String> value) {
		
		String request = "INSERT INTO " + this.name + " VALUES(";
		
		for(int i = 0; i < indexes.size(); i++) {
			
			int index = indexes.get(i);
			
			if(!(indexes.get(indexes.size() - 1) == index)) {
				
				request += "'" + value.get(i) + "', ";
				
			} else {
				
				request += "'" + value.get(i) + "');";
				
			}
		}
		
		try {
			
			Main.database.updateSQL(request);
			return true;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			return false;
		}
		return false;
		
	} 
	
	/**
	 * Méthode qui vérifie si une clé primaire existe déjà
     * @param string un String
     * @return true si la table contient déjà une clé primaire, false sinon
	 */
	public boolean alreadyHasPrimaryKey(String string) {
		
		int count = 0;
		
		try {
			ResultSet rs = Main.database.querySQL("SELECT COUNT(*) AS rowcount FROM " + this.name + " WHERE " + this.informations.keySet().toArray()[0] + " = " + string + ";");
			
			rs.next();
			count = rs.getInt("rowcount");
			rs.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {	
		}
		
		return count > 0;
		
		
	}
	
	/**
	 * Méthode qui vérifie si la table est une table de relation
     * @return true si la table contient déjà une clé primaire, false sinon
	 */
	public boolean isRelationTable() {
		
		int count = 0;
		
		try {
			DatabaseMetaData md = Main.database.connexion.getMetaData();
			
			ResultSet rs = md.getPrimaryKeys("nicolath", null, this.name);
			
			while (rs.next()){
		        count++;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count > 1;
		
	}
	
	/**
	 * Méthode qui met à jour des valeurs
     * @param id un entier
     * @param indexes une liste d'entiers
     * @param value une liste de String
	 */
	public void updateInto(int id, List<Integer> indexes, ArrayList<String> value) {
		
			String request = "UPDATE " + this.name + " SET";
			
			for(int i = 0; i < indexes.size(); i++) {
				
				int index = indexes.get(i);
				String column_name = (String) this.informations.keySet().toArray()[index];
				
				if(!(indexes.get(indexes.size() - 1) == index)) {
					
					request += " " + column_name + " = '" + value.get(i) + "',";
					
				} else {
					
					request += " " + column_name + " = '" + value.get(i) + "' WHERE " + this.informations.keySet().toArray()[0] + " = " + id + ";";
					
				}
				
				
			}
			
			
			try {
				Main.database.updateSQL(request);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			
					
		
	}
	
	/**
	 * Méthode qui renvoie une selection totale des valeurs de la table
     * @return une liste comprenant elle même des listes
     * 		chaque liste correpondant à une ligne de la table
	 */
	public ArrayList<List<String>> selectAll() {
		
		ArrayList<List<String>> total_list = new ArrayList<>();
		
		String QUERY = "SELECT * FROM " + this.name;
		
		try {
			ResultSet rs = Main.database.querySQL(QUERY);
			
				while(rs.next()){
					
					ArrayList<String> sublist = new ArrayList<>();
					
					for(String name : this.informations.keySet()) {
						
						sublist.add(getValueOf(rs, name));
						
					}
					total_list.add(sublist);
				
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return total_list;
		
	}
	
	/**
	 * Méthode qui renvoie une ligne précise en fonction de l'id
     * @return une liste comprenant elle même des listes
     * 		chaque liste correpondant à une ligne de la table
     * @param index String correspondant à l'id d'une ligne
	 */
	public ArrayList<String> selectLigne(String index) {
		
		ArrayList<String> list_attribut = new ArrayList<>();
		
		String QUERY = "SELECT * FROM " + this.name + " WHERE " + this.informations.keySet().toArray()[0] + " = +" + index + ";";
		
		try {
			ResultSet rs = Main.database.querySQL(QUERY);
				int cpt = 0;
				while(rs.next()){
					
					for(String name : this.informations.keySet()) {
							
						if(cpt != 0) {
							
							list_attribut.add(getValueOf(rs, name));
							
						}
						cpt++;
						
					}	
				}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return list_attribut;	
	}
	
	/**
	 * Méthode qui un string à partir d'un type de variable d'une base de données
     * @return un type sql casté en String
     * @throws SQLException erreur dans la requête sql
     * @param rs Un ResultSet
     * @param un String correspondant au non d'une colonne
	 */
	private String getValueOf(ResultSet rs, String name) throws SQLException {
	
		switch (this.informations.get(name)) {
		
			case "INT": {
				return (String.valueOf(rs.getInt(name)));
			}
			case "VARCHAR": {
				return (rs.getString(name));
			}
			case "BIT": {
				return (String.valueOf(rs.getByte(name)));
			}
			case "CHAR": {
				return (String.valueOf(rs.getString(name)));
			}
			case "BINARY": {
				return (String.valueOf(rs.getByte(name)));
			}
			case "VARBINARY": {
				return (String.valueOf(rs.getByte(name)));
			}
			case "TINYTEXT": {
				return (String.valueOf(rs.getString(name)));
			}
			case "MEDIUMTEXT": {
				return (String.valueOf(rs.getString(name)));
			}
			case "LONGTEXT": {
				return (String.valueOf(rs.getString(name)));
			}
			case "TINYINT": {
				return (String.valueOf(rs.getShort(name)));
			}
			case "BOOL": {
				return (String.valueOf(rs.getBoolean(name)));
			}
			case "BOOLEAN": {
				return (String.valueOf(rs.getBoolean(name)));
			}
			case "SMALLINT": {
				return (String.valueOf(rs.getShort(name)));
			}
			case "MEDIUMINT": {
				return (String.valueOf(rs.getInt(name)));
			}
			case "BIGINT": {
				return (String.valueOf(rs.getLong(name)));
			}
			case "FLOAT": {
				return (String.valueOf(rs.getDouble(name)));
			}
			case "DOUBLE": {
				return (String.valueOf(rs.getDouble(name)));
			}
			case "DECIMAL": {
				return (String.valueOf(rs.getBigDecimal(name)));
			}
			case "DATE": {
				return (String.valueOf(rs.getDate(name)));
			}
			case "DATETIME": {
				return (String.valueOf(rs.getTimestamp(name)));
			}
			case "TIMESTAMP": {
				return (String.valueOf(rs.getTimestamp(name)));
			}
			case "TIME": {
				return (String.valueOf(rs.getTime(name)));
			}
			case "TINYBLOB": {
				return (String.valueOf(rs.getBlob(name)));
			}
			case "BLOB": {
				return (String.valueOf(rs.getBlob(name)));
			}
			case "MEDIUMBLOB": {
				return (String.valueOf(rs.getBlob(name)));
			}
			case "LONGBLOB": {
				return (String.valueOf(rs.getBlob(name)));
			}
			default:
				return null;
		}
		
	}
	
	/**
	 * Méthode qui renvoie une liste des plus grandes chaînes de caractères par colonne
     * @return une liste des plus grandes chaînes de caractères par colonne
	 */
	public ArrayList<String> getMaxSizeFromColumn() {
		
		ArrayList<String> maxvalue = new ArrayList<>();
		
		for(int i = 0; i < informations.keySet().size(); i++) {
			maxvalue.add("");
		}
		
		for(List<String> sublist : this.selectAll()) {
			
			for(int i = 0; i< sublist.size(); i++) {
				
				String attribut = sublist.get(i);
				
				if(attribut.length() > maxvalue.get(i).length()) {
					maxvalue.set(i, attribut);
				}
				
			}
			
		}
		return maxvalue;
		
	}
	
	/**
	 * Méthode qui renvoie un entier
     * @return un entier correspondant au nombre de colonnes
	 */
	public int getTotalColumn() {
		
		return informations.keySet().size();
		
	}
	
	/**
	 * Méthode qui renvoie un entier
     * @return un entier correspondant au nombre total de lignes
	 */
	public int getTotalLine() {
		
		int count = 0;
		
		try {
			ResultSet rs = Main.database.querySQL("SELECT COUNT(*) AS rowcount FROM " + this.name + ";");
			
			rs.next();
			count = rs.getInt("rowcount");
			rs.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return count;
		
	}
	
	/**
	 * Méthode qui supprime une ligne
	 * @param index entier correspondant à un id
	 */
	public void deleteLine(int index) {
		
		try {
			Main.database.updateSQL("DELETE FROM " + this.name + " WHERE " + this.informations.keySet().toArray()[0] + " = " + index + ";");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
}

