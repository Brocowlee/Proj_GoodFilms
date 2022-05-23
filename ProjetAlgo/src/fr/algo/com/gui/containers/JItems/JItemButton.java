package fr.algo.com.gui.containers.JItems;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import fr.algo.com.gui.EditGui;
import fr.algo.com.gui.containers.ContainerAdmin;
import fr.algo.com.gui.containers.ContainerCreateTable;
import fr.algo.com.handler.InitTable;
import fr.algo.com.object.TableObject;

/**
 * <b>Classe de creation d'un bouton ainsi que son listener</b>
 * <p>
 *   Cette classe gère un bouton en dehros de la classe où il est crée
 * 
 * @author Thomas, Benjamin
 * @version 1.0
 */

public class JItemButton {

	/**
     * String nom du bouton
     */
	private String name;
	
	/**
     * JButton bouton
     */
	private JButton button;
	
	/**
     * String type d'action du bouton
     */
	private String type;
	
	/**
     * ContainerAdmin conteneur d'où peut provenir le bouton
     */
	private ContainerAdmin containerAdmin;
	
	/**
     * ContainerCreateTable conteneur d'où peut provenir le bouton
     */
	private ContainerCreateTable containerCreateTable;
	
	/**
	* Constructeur de la classe pour un bouton texte dans ContainerAdmin
	* 
	* @param name nom du bouton
	* @param containerAdmin conteneur d'où peut provenir le bouton 
	* @param type type d'action
	*/
	public JItemButton(String name,ContainerAdmin containerAdmin, String type) {
		this.name = name;
		this.type = type;
		this.containerAdmin = containerAdmin;
		initButton(name);
		
		
		this.button.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	if (type.equalsIgnoreCase("Table")) {
		    		
		    		TableObject table = InitTable.liste_tables.get(name);
				    getContainerAdmin().showTable(table);
				}
		    	if(type.equalsIgnoreCase("Supprimer")) {
		    		getContainerAdmin().delete();
		    	}
		    	if(type.equalsIgnoreCase("Ajouter")) {
		    		getContainerAdmin().adding();
		    	}
		    }
		    
		});
		
	}
	
	/**
	* Constructeur de la classe pour un bouton Image dans ContainerAdmin
	* 
	* @param name nom du bouton
	* @param containerAdmin conteneur d'où peut provenir le bouton
	* @param type type d'action
	*/
	public JItemButton(ImageIcon name,ContainerAdmin containerAdmin, String type) {
		this.type = type;
		this.containerAdmin = containerAdmin;
		initButton(name);
		
		
		this.button.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	
		    	if (type.equalsIgnoreCase("Edit")) {
		    		
		    		EditGui gui = new EditGui(containerAdmin, getItemButton());
		    		
		    		gui.setVisible(true);
		    		
		    	}
		    }
		    
		});
		
	}
	
	/**
	* Constructeur de la classe pour une image dans ContainerCreateTable
	* 
	* @param name nom du bouton
	* @param containerCreateTable conteneur d'où peut provenir le bouton
	* @param type type d'action
	*/
	public JItemButton(ImageIcon name,ContainerCreateTable containerCreateTable, String type) {
		this.type = type;
		this.containerCreateTable = containerCreateTable;
		initButton(name);
		
		
		this.button.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	
		    	if (type.equalsIgnoreCase("Edit")) {
		    		
		    		//EditColumnGui gui = new EditColumnGui(containerCreateTable, getItemButton());
		    		
		    		//gui.setVisible(true);
		    		
		    	}
		    }
		    
		});
		
	}

	/**
	* Methode de creation d'un bouton avec un texte
	*
	*@param name nom du bouton
	*/
	private void initButton(String name) {
		this.button = new JButton(name);
		this.button.setAlignmentX(Component.CENTER_ALIGNMENT);
	}
	
	/**
	* Methode de creation d'un bouton avec une image
	*
	*@param name image du bouton
	*/
	private void initButton(ImageIcon name) {
		this.button = new JButton(name);
		this.button.setMinimumSize(new Dimension(20, 20));
		this.button.setMaximumSize(new Dimension(20, 20));
		this.button.setBorderPainted( false );
	}
	
	/**
	* Getter du type
	*
	*@return le type
	*/
	public String getType() {
		return type;
	}

	/**
	* Setter du type
	*
	*@param type type à définir
	*/
	public void setType(String type) {
		this.type = type;
	}

	/**
	* Getter du nom
	*
	*@return le nom
	*/
	public String getName() {
		return name;
	}

	/**
	* Setter du nom
	*
	*@param name nom du bouton
	*/
	public void setName(String name) {
		this.name = name;
	}

	/**
	* Getter du bouton
	*
	*@return le bouton
	*/
	public JButton getButton() {
		return button;
	}

	/**
	* Setter du bouton
	*
	*@param button bouton à définir
	*/
	public void setButton(JButton button) {
		this.button = button;
	}
	
	/**
	* Getter de containerAdmin
	*
	*@return containerAdmin
	*/
	public ContainerAdmin getContainerAdmin() {
		return this.containerAdmin;
	}
	
	/**
	* Getter de cet objet
	*
	*@return cet objet
	*/
	public JItemButton getItemButton() {
		return this;
		
	}
	
	/**
	* Getter de containerCreateTable
	*
	*@return containerCreateTable
	*/
	public ContainerCreateTable getContainerCreateTable() {
		return this.containerCreateTable;
	}
	
}