package fr.algo.com.gui;


import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


@SuppressWarnings("serial")
public class AddColumnGui extends JFrame{

	private ImageIcon icon = new ImageIcon("./Icon.jpg");
	
	public AddColumnGui() {
		
		setTitle("Ajouter une colonne");
		setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(800,300));
        setMaximumSize(new Dimension(800,300));
		setLocationRelativeTo(null);
		setResizable(false);
		pack();
		
		
		
	}
	
	public AddColumnGui getAddColumnGui() {
		return this;
		
	}
}
