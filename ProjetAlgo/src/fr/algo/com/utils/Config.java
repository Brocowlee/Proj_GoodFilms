package fr.algo.com.utils;

/**
 * <b>Classe correspondant au fichier de configuration </b>
 * <p>
 *   Cette classe permet de r�cup�rer les informations du fichier de configuration
 * 
 * @author Thomas
 * @version 1.0
 */
public class Config {
    
	/**
     * Nom de l'host
     */
    private String host;
    
    /**
     * Valeur du port
     */
    private String port;
    
    /**
     * Nom de la base de donn�es
     */
    private String database;
    
    /**
     * Nom de l'utilisateur
     */
    private String user;
    
    /**
     * Valeur du mot de passe
     */
    private String password;

    /**
	* Constructeur de la classe
	*/
    public Config() {}

    /**
     * Retourne l'host
     * @return un String, qui correspond � l'host
     */
    public String getHost() {
		return host;
	}
    
    /**
	 * M�thode qui set le nouvel host
     * @param host un nouvel host
	 */
	public void setHost(String host) {
		this.host = host;
	}
	
	 /**
     * Retourne le port
     * @return un String, qui correspond au port
     */
	public String getPort() {
		return port;
	}

	/**
	 * M�thode qui set le port
     * @param port un nouveau port
	 */
	public void setPort(String port) {
		this.port = port;
	}

	 /**
     * Retourne le nom de la base de donn�es
     * @return un String, qui correspond au nom de la base de donn�es
     */
	public String getDatabase() {
		return database;
	}

	/**
	 * M�thode qui set le nom de la base de donn�es
     * @param database un nouveau nom de base de donn�es
	 */
	public void setDatabase(String database) {
		this.database = database;
	}

	 /**
     * Retourne l'utilisateur
     * @return un String, qui correspond � l'utilisateur
     */
	public String getUser() {
		return user;
	}
	
	/**
	 * M�thode qui set l'utilisateur
     * @param user un nouvel utilisateur
	 */
	public void setUser(String user) {
		this.user = user;
	}
	
	 /**
     * Retourne le mot de passe
     * @return un String, qui correspond au mot de passe
     */
	public String getPassword() {
		return password;
	}
	
	/**
	 * M�thode qui set le mot de passe
     * @param password un nouveau mot de passe
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	 /**
     * Retourne un string correspondant � la description de la classe
     * @return un String, avec les diff�rentes valeurs
     */
	@Override
    public String toString() {
        return "Config [host=" + this.host + ", port=" + this.port + ", database=" + this.host
                + ", user=" + this.user + ", password=" + this.password + "]";
    }
}
