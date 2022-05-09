package fr.algo.com.gui.containers.JItems;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import fr.algo.com.gui.EditGui;
import fr.algo.com.gui.containers.ContainerAdmin;
import fr.algo.com.handler.InitTable;
import fr.algo.com.object.TableObject;

public class JItemButton {

	private String name;
	private JButton button;
	private String type;
	private ContainerAdmin containerAdmin;
	
	
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

	private void initButton(String name) {
		this.button = new JButton(name);
		this.button.setAlignmentX(Component.CENTER_ALIGNMENT);
	}
	
	private void initButton(ImageIcon name) {
		this.button = new JButton(name);
		this.button.setMinimumSize(new Dimension(20, 20));
		this.button.setMaximumSize(new Dimension(20, 20));
		this.button.setBorderPainted( false );
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}
	
	public ContainerAdmin getContainerAdmin() {
		return this.containerAdmin;
	}
	
	public JItemButton getItemButton() {
		return this;
		
	}
	
}