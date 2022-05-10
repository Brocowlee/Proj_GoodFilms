package fr.algo.com.gui;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import fr.algo.com.gui.containers.MasterContainer;


@SuppressWarnings("serial")
public class MyGUI extends JFrame{

	
	private ImageIcon icon = new ImageIcon("./Icon.jpg");

	public MyGUI() {
		
		setContentPane(new MasterContainer(this));

		//Application initialising :
		setTitle("ZeFilmViewer");
		setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(1000,1000));
        setMaximumSize(new Dimension(1000,1000));
		setLocationRelativeTo(null);
		setResizable(false);
		pack();
		
	}
	
}
