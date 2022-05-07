package fr.algo.com.handler;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import fr.algo.com.Main;
import fr.algo.com.object.TableObject;

public class InitTable {

	public static MySQL database = Main.database;
	
	public static HashMap <String, TableObject> liste_tables = new HashMap<>();
	
	public static List<String> getListOfTable(){
				
			List<String> tables = new ArrayList<>();
		
            try {
            		DatabaseMetaData md = database.connection.getMetaData();
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
	
	public static void initTable() {
		
		 try {
     		DatabaseMetaData md = database.connection.getMetaData();
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
	
	public static void initColumn(TableObject table){
		
		try {
			DatabaseMetaData metadata = database.connection.getMetaData();
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
