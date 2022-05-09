package fr.algo.com.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import fr.algo.com.gui.containers.MasterContainer;


@SuppressWarnings("serial")
public class MyGUI extends JFrame{

	
	private ImageIcon icon = new ImageIcon("./Icon.jpg");
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu = new JMenu("Fichier");
	private JMenuItem menuItem = new JMenuItem("Nouvelle Fenêtre");
	private JMenuItem menuItem2 = new JMenuItem("AJOUTER TABLE");
	
	private JMenu menu2 = new JMenu("Edition");
		
	
	private JMenu menu3 = new JMenu("Help");
	
	 
	
	public MyGUI() {
		
		setContentPane(new MasterContainer());

		//Application initialising :
		setTitle("ZeFilmViewer");
		setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(1000,1000));
        setMaximumSize(new Dimension(1000,1000));
		setLocationRelativeTo(null);
		pack();
		
		//Menu bar setup :
		
		this.menuBar.add(menu);
		this.menuBar.add(menu2);
		this.menuBar.add(menu3);
		
		menu.add(menuItem);
		menu.add(menuItem2);
		
		setJMenuBar(menuBar);
		
		
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				
				MyGUI gui = new MyGUI();
				
				gui.setVisible(true);
				
				/*WarningGui gui = new WarningGui("jjjjjjj");
				
				gui.setVisible(true);*/
				
			}
		});
		
		menuItem2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
}
