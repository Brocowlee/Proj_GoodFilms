package fr.algo.com.object;

/**
 * <b>Classe d'une colonne</b>
 * <p>
 *   Cette classe va permettre de r�aliser des instances de colonnes
 * 
 * @author Thomas
 * @version 1.0
 */
public class Column {
	
	/**
     * Nom de la colonne
     */
	private String name;
	
	/**
     * Type de la colonne
     */
	private String type;
	
	/**
     * Valeur par d�faut de la colonne
     */
	private String default_value = "";
	
	/**
     * boolean pour conna�tre si la colonne peut �tre nulle
     */
	private boolean not_null = true;
	
	/**
     * boolean pour conna�tre s'il y a une incr�mentation automatique
     */
	private boolean auto_increment = false;
	
	/**
     * boolean pour savoir si la colonne est une cl� primaire
     */
	private boolean primary = false;
	
	/**
	* Constructeur de la classe
	* @param name nom de la classe
	*/
	public Column(String name) {
		this.name = name;
	}
	
	 /**
     * Retourne le nom de la colonne
     * @return un String, qui correspond au nom
     */
	public String getName() {
		return name;
	}

	/**
	 * M�thode qui set le nom
     * @param name une cha�ne de caract�res correspondant au nouveau nom
	 */
	public void setName(String name) {
		this.name = name;
	}

	 /**
     * Retourne le type de la colonne
     * @return un String, qui correspond au type
     */
	public String getType() {
		return type;
	}

	/**
	 * M�thode qui set le type
     * @param type une cha�ne de caract�res correspondant au nouveau type
	 */
	public void setType(String type) {
		this.type = type;
	}

	 /**
     * Retourne un boolean
     * @return true si la colonne peut �tre nulle, false sinon
     */
	public boolean isNotNull() {
		return this.not_null;
	}
	
	/**
	 * M�thode qui set le boolean non nulle
     * @param bool une valeur true ou false 
	 */
	public void setNotNull(boolean bool) {
		this.not_null = bool;
	}
	
	/**
     * Retourne un boolean
     * @return true si la colonne a une valeur par d�faut, false sinon
     */
	public boolean hasDefaultValue() {
		
		return !this.default_value.isBlank();
		
	}
	
	/**
     * Retourne la valeur par d�faut
     * @return un String, qui correspond � la valeur par d�faut
     */
	public String getDefaultValue() {
		
		return this.default_value;
		
	}
	
	/**
	 * M�thode qui set la valeur par d�faut
     * @param value une cha�ne de caract�res correspondant � la nouvelle valeur par d�faut
	 */
	public void setDefaultValue(String value) {
		this.default_value = value;
	}
	
	/**
     * Retourne un boolean
     * @return true si la colonne a une incr�mentation automatique, false sinon
     */
	public boolean isAutoIncrement() {
		
		return this.auto_increment;
		
	}
	
	/**
	 * M�thode qui set le boolean auto increment
     * @param bool une valeur true ou false 
	 */
	public void setAutoIncrement(Boolean bool) {
		this.auto_increment = bool;
	}
	
	/**
     * Retourne un boolean
     * @return true si la colonne est une cl� primaire, false sinon
     */
	public boolean isPrimary() {
		return this.primary;
	}
	
	/**
	 * M�thode qui set le boolean priary
     * @param bool une valeur true ou false 
	 */
	public void setPrimary(Boolean bool) {
		this.primary = bool;
	}
	
}
