package fr.algo.com.object;

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
	
	/*public int getMaxSizeOfLine() {
		
	} */
	
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
	
	
}

