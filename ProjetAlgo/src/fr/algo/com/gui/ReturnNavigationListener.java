package fr.algo.com.gui;

import fr.algo.com.gui.containers.MasterContainer;

/**
 * <b>Interface Listener de JPanel </b>
 * <p>
 *   Cette Interface va écouter les JPanel pour afficher le bon JPanel dans le GUI
 * 
 * @see MasterContainer
 * @author Thomas, Benjamin
 * @version 1.0
 */

public interface ReturnNavigationListener<T> {

	/**
	 * Méthode qui retourne la source
     * @param source source du listener
	 */
    public void returnFrom(T source);
}
