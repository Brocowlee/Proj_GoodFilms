package fr.algo.com.gui.containers.JItems;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerAdapter;

import javax.swing.JButton;

import fr.algo.com.gui.containers.ContainerAdmin;
import fr.algo.com.handler.InitTable;
import fr.algo.com.object.TableObject;

public class JItemButton {

	private String name;
	private JButton button;
	private ContainerAdmin containerAdmin;
	
	public JItemButton(String name,ContainerAdmin containerAdmin) {
		this.name = name;
		this.containerAdmin = containerAdmin;
		initButton(name);
		
		
		this.button.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	
		    	TableObject table = InitTable.liste_tables.get(name);
		    	
		    	getContainerAdmin().showTable(table, getContainerAdmin());
		    	
		    	
		    }
		    
		});
		
	}

	private void initButton(String name) {
		this.button = new JButton(name);
		this.button.setAlignmentX(Component.CENTER_ALIGNMENT);
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
	
	
}
