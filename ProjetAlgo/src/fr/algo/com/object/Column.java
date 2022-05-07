package fr.algo.com.object;

public class Column {

	private String name;
	private String type;
	private String default_value = "";
	private boolean not_null = true;
	private boolean auto_increment = false;
	private boolean primary = false;
	
	public Column(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isNotNull() {
		
		return this.not_null;
		
	}
	
	public void setNotNull(boolean bool) {
		this.not_null = bool;
	}
	
	public boolean hasDefaultValue() {
		
		return this.default_value.length() > 0;
		
	}
	
	public String getDefaultValue() {
		
		return this.default_value;
		
	}
	
	public void setDefaultValue(String value) {
		this.default_value = value;
	}
	
	public boolean isAutoIncrement() {
		
		return this.auto_increment;
		
	}
	
	public void setAutoIncrement(Boolean bool) {
		this.auto_increment = bool;
	}
	
	public boolean isPrimary() {
		return this.primary;
	}
	
	public void setPrimary(Boolean bool) {
		this.primary = bool;
	}
	
}
