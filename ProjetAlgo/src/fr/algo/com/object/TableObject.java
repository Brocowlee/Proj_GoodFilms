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

public class TableObject {

	private String name;
	private LinkedHashMap<String, String> informations = new LinkedHashMap<>();
	
	public TableObject(String table_name) {
		this.name = table_name;
	}
	
	public void addColumn(String column_name, String column_type) {
		this.informations.put(column_name, column_type);
	}
	
	public String getName() {
		return this.name;
	}
	
	public void showColumn() {
		
		for(String column_name : informations.keySet()) {
			
			System.out.println("Colonne : " + column_name + " | Type : " + informations.get(column_name));
			
		}
		
	}
	
	public void deleteTable() {
		
		InitTable.liste_tables.remove(this.name);
		
		try {
			Main.database.updateSQL("DROP TABLE if exists " + this.name + ";");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public Set<String> getColumnName(){
		
		return this.informations.keySet();
		
	}
	
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
	
	public boolean isRelationTable() {
		
		int count = 0;
		
		try {
			DatabaseMetaData md = Main.database.connection.getMetaData();
			
			ResultSet rs = md.getPrimaryKeys("nicolath", null, this.name);
			
			while (rs.next()){
		        count++;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count > 1;
		
	}
	
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
	
	public ArrayList<List<String>> selectAll() {
		
		ArrayList<List<String>> total_list = new ArrayList<>();
		
		String QUERY = "SELECT * FROM " + this.name;
		
		try {
			ResultSet rs = Main.database.querySQL(QUERY);
			
				while(rs.next()){
					
					ArrayList<String> sublist = new ArrayList<>();
					
					for(String name : this.informations.keySet()) {
						
						if(this.informations.get(name).equalsIgnoreCase("INT")) {
							 sublist.add(String.valueOf(rs.getInt(name)));
						}
						if(this.informations.get(name).equalsIgnoreCase("VARCHAR")) {
							 sublist.add(rs.getString(name));
						}
						if(this.informations.get(name).equalsIgnoreCase("BIT")) {
							 sublist.add(String.valueOf(rs.getByte(name)));
						}
						
					}
					total_list.add(sublist);
				
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return total_list;
		
	}
	
	public ArrayList<String> selectLigne(String index) {
		
		ArrayList<String> list_attribut = new ArrayList<>();
		
		String QUERY = "SELECT * FROM " + this.name + " WHERE " + this.informations.keySet().toArray()[0] + " = +" + index + ";";
		
		try {
			ResultSet rs = Main.database.querySQL(QUERY);
				int cpt = 0;
				while(rs.next()){
					
					
						
					for(String name : this.informations.keySet()) {
							
						if(cpt != 0) {
							
							switch (this.informations.get(name)) {
								case "INT": {
									list_attribut.add(String.valueOf(rs.getInt(name)));
								}
								case "VARCHAR": {
									list_attribut.add(rs.getString(name));
								}
								case "BIT": {
									list_attribut.add(String.valueOf(rs.getByte(name)));
								}
								case "CHAR": {
									list_attribut.add(String.valueOf(rs.getString(name)));
								}
								case "BINARY": {
									list_attribut.add(String.valueOf(rs.getByte(name)));
								}
								case "VARBINARY": {
									list_attribut.add(String.valueOf(rs.getByte(name)));
								}
								case "TINYTEXT": {
									list_attribut.add(String.valueOf(rs.getString(name)));
								}
								case "MEDIUMTEXT": {
									list_attribut.add(String.valueOf(rs.getString(name)));
								}
								case "LONGTEXT": {
									list_attribut.add(String.valueOf(rs.getString(name)));
								}
								case "TINYINT": {
									list_attribut.add(String.valueOf(rs.getShort(name)));
								}
								case "BOOL": {
									list_attribut.add(String.valueOf(rs.getBoolean(name)));
								}
								case "BOOLEAN": {
									list_attribut.add(String.valueOf(rs.getBoolean(name)));
								}
								case "SMALLINT": {
									list_attribut.add(String.valueOf(rs.getShort(name)));
								}
								case "MEDIUMINT": {
									list_attribut.add(String.valueOf(rs.getInt(name)));
								}
								case "BIGINT": {
									list_attribut.add(String.valueOf(rs.getLong(name)));
								}
								case "FLOAT": {
									list_attribut.add(String.valueOf(rs.getDouble(name)));
								}
								case "DOUBLE": {
									list_attribut.add(String.valueOf(rs.getDouble(name)));
								}
								case "DECIMAL": {
									list_attribut.add(String.valueOf(rs.getBigDecimal(name)));
								}
								case "DATE": {
									list_attribut.add(String.valueOf(rs.getDate(name)));
								}
								case "DATETIME": {
									list_attribut.add(String.valueOf(rs.getTimestamp(name)));
								}
								case "TIMESTAMP": {
									list_attribut.add(String.valueOf(rs.getTimestamp(name)));
								}
								case "TIME": {
									list_attribut.add(String.valueOf(rs.getTime(name)));
								}
								case "TINYBLOB": {
									list_attribut.add(String.valueOf(rs.getBlob(name)));
								}
								case "BLOB": {
									list_attribut.add(String.valueOf(rs.getBlob(name)));
								}
								case "MEDIUMBLOB": {
									list_attribut.add(String.valueOf(rs.getBlob(name)));
								}
								case "LONGBLOB": {
									list_attribut.add(String.valueOf(rs.getBlob(name)));
								}
							}
							
							
							/*if(this.informations.get(name).equalsIgnoreCase("INT")) {
								list_attribut.add(String.valueOf(rs.getInt(name)));
							}
							if(this.informations.get(name).equalsIgnoreCase("VARCHAR")) {
								list_attribut.add(rs.getString(name));
							}
							if(this.informations.get(name).equalsIgnoreCase("BIT")) {
								list_attribut.add(String.valueOf(rs.getByte(name)));
							}
							if(this.informations.get(name).equalsIgnoreCase("CHAR")) {
								list_attribut.add(String.valueOf(rs.getString(name)));
							}
							if(this.informations.get(name).equalsIgnoreCase("BINARY")) {
								list_attribut.add(String.valueOf(rs.getByte(name)));
							}
							if(this.informations.get(name).equalsIgnoreCase("VARBINARY")) {
								list_attribut.add(String.valueOf(rs.getByte(name)));
							}
							if(this.informations.get(name).equalsIgnoreCase("TINYTEXT")) {
								list_attribut.add(String.valueOf(rs.getString(name)));
							}
							if(this.informations.get(name).equalsIgnoreCase("MEDIUMTEXT")) {
								list_attribut.add(String.valueOf(rs.getString(name)));
							}
							if(this.informations.get(name).equalsIgnoreCase("LONGTEXT")) {
								list_attribut.add(String.valueOf(rs.getString(name)));
							}
							if(this.informations.get(name).equalsIgnoreCase("TINYINT")) {
								list_attribut.add(String.valueOf(rs.getShort(name)));
							}
							if(this.informations.get(name).equalsIgnoreCase("BOOL")) {
								list_attribut.add(String.valueOf(rs.getInt(name)));
							}
							if(this.informations.get(name).equalsIgnoreCase("BOOLEAN")) {
								list_attribut.add(String.valueOf(rs.getInt(name)));
							}
							if(this.informations.get(name).equalsIgnoreCase("SMALLINT")) {
								list_attribut.add(String.valueOf(rs.getShort(name)));
							}
							if(this.informations.get(name).equalsIgnoreCase("MEDIUMINT")) {
								list_attribut.add(String.valueOf(rs.getInt(name)));
							}
							if(this.informations.get(name).equalsIgnoreCase("BIGINT")) {
								list_attribut.add(String.valueOf(rs.getLong(name)));
							}
							if(this.informations.get(name).equalsIgnoreCase("FLOAT")) {
								list_attribut.add(String.valueOf(rs.getFloat(name)));
							}
							if(this.informations.get(name).equalsIgnoreCase("DOUBLE")) {
								list_attribut.add(String.valueOf(rs.getDouble(name)));
							}
							if(this.informations.get(name).equalsIgnoreCase("DECIMAL")) {
								list_attribut.add(String.valueOf(rs.getBigDecimal(name)));
							}
							if(this.informations.get(name).equalsIgnoreCase("DATE")) {
								list_attribut.add(String.valueOf(rs.getDate(name)));
							}
							if(this.informations.get(name).equalsIgnoreCase("DATETIME")) {
								list_attribut.add(String.valueOf(rs.getTimestamp(name)));
							}
							if(this.informations.get(name).equalsIgnoreCase("TIMESTAMP")) {
								list_attribut.add(String.valueOf(rs.getTimestamp(name)));
							}
							if(this.informations.get(name).equalsIgnoreCase("TIME")) {
								list_attribut.add(String.valueOf(rs.getTime(name)));
							}
							if(this.informations.get(name).equalsIgnoreCase("TINYBLOB")) {
								list_attribut.add(String.valueOf(rs.getBlob(name)));
							}
							if(this.informations.get(name).equalsIgnoreCase("BLOB")) {
								list_attribut.add(String.valueOf(rs.getBlob(name)));
							}
							if(this.informations.get(name).equalsIgnoreCase("MEDIUMBLOB")) {
								list_attribut.add(String.valueOf(rs.getBlob(name)));
							}
							if(this.informations.get(name).equalsIgnoreCase("LONGBLOB")) {
								list_attribut.add(String.valueOf(rs.getBlob(name)));
							} */
						}
						cpt++;
						
					}	
				}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return list_attribut;	
	}
	
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
	
	public int getTotalColumn() {
		
		return informations.keySet().size();
		
	}
	
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

	public void deleteLine(int index) {
		
		try {
			Main.database.updateSQL("DELETE FROM " + this.name + " WHERE " + this.informations.keySet().toArray()[0] + " = " + index + ";");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
}

