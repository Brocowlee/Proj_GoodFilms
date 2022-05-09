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
		
		// Delete sql request
		
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
							
							if(this.informations.get(name).equalsIgnoreCase("INT")) {
								list_attribut.add(String.valueOf(rs.getInt(name)));
							}
							if(this.informations.get(name).equalsIgnoreCase("VARCHAR")) {
								list_attribut.add(rs.getString(name));
							}
							if(this.informations.get(name).equalsIgnoreCase("BIT")) {
								list_attribut.add(String.valueOf(rs.getByte(name)));
							}
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

