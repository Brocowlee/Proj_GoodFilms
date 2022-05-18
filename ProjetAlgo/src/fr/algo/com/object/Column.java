package fr.algo.com.object;

/**
 * <b>Classe d'une colonne</b>
 * <p>
 *   Cette classe va permettre de réaliser des instances de colonnes
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
     * Valeur par défaut de la colonne
     */
	private String default_value = "";
	
	/**
     * boolean pour connaître si la colonne peut être nulle
     */
	private boolean not_null = true;
	
	/**
     * boolean pour connaître s'il y a une incrémentation automatique
     */
	private boolean auto_increment = false;
	
	/**
     * boolean pour savoir si la colonne est une clé primaire
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
	 * Méthode qui set le nom
     * @param name une chaîne de caractères correspondant au nouveau nom
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
	 * Méthode qui set le type
     * @param type une chaîne de caractères correspondant au nouveau type
	 */
	public void setType(String type) {
		this.type = type;
	}

	 /**
     * Retourne un boolean
     * @return true si la colonne peut être nulle, false sinon
     */
	public boolean isNotNull() {
		return this.not_null;
	}
	
	/**
	 * Méthode qui set le boolean non nulle
     * @param bool une valeur true ou false 
	 */
	public void setNotNull(boolean bool) {
		this.not_null = bool;
	}
	
	/**
     * Retourne un boolean
     * @return true si la colonne a une valeur par défaut, false sinon
     */
	public boolean hasDefaultValue() {
		
		return !this.default_value.isBlank();
		
	}
	
	/**
     * Retourne la valeur par défaut
     * @return un String, qui correspond à la valeur par défaut
     */
	public String getDefaultValue() {
		
		return this.default_value;
		
	}
	
	/**
	 * Méthode qui set la valeur par défaut
     * @param value une chaîne de caractères correspondant à la nouvelle valeur par défaut
	 */
	public void setDefaultValue(String value) {
		this.default_value = value;
	}
	
	/**
     * Retourne un boolean
     * @return true si la colonne a une incrémentation automatique, false sinon
     */
	public boolean isAutoIncrement() {
		
		return this.auto_increment;
		
	}
	
	/**
	 * Méthode qui set le boolean auto increment
     * @param bool une valeur true ou false 
	 */
	public void setAutoIncrement(Boolean bool) {
		this.auto_increment = bool;
	}
	
	/**
     * Retourne un boolean
     * @return true si la colonne est une clé primaire, false sinon
     */
	public boolean isPrimary() {
		return this.primary;
	}
	
	/**
	 * Méthode qui set le boolean priary
     * @param bool une valeur true ou false 
	 */
	public void setPrimary(Boolean bool) {
		this.primary = bool;
	}
	
}
