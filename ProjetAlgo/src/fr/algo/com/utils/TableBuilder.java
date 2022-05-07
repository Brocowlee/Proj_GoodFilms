package fr.algo.com.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.algo.com.Main;
import fr.algo.com.object.Column;

public class TableBuilder {

	private String name;
	
	private ArrayList<Column> list_columns = new ArrayList<>();
	
	public TableBuilder(String name) {
		
		this.name = name;
	}
	
	public void addColumn() {
		Column c1 = new Column("id");
		c1.setType("int(11)");
		c1.setAutoIncrement(true);
		c1.setNotNull(true);
		c1.setDefaultValue("");
		c1.setPrimary(true);
		
		list_columns.add(c1);
		
		/*Column c2 = new Column("name", "varchar(30)", true, false, "", false);
		Column c3 = new Column("id_grade", "int(11)", true, false, "0", false);
		
		list_columns.add(c2);
		list_columns.add(c3); */
	}
	
	
	public void build() {
		
		  try {
			  
			Main.database.updateSQL(writeRequest(list_columns));
			
		  } catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public String writeRequest(List<Column> list_columns) {
		
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
					primary = column.getName();
				}
				request += ", ";
				
		}
		
		
		String end_request = "PRIMARY KEY (`" + primary +  "`) ) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;"  ;
		
		String final_request = request += end_request;
		
		return final_request;
	}
	
}
