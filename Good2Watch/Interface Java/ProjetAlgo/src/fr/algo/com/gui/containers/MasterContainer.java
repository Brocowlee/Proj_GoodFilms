package fr.algo.com.gui.containers;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import fr.algo.com.Main;
import fr.algo.com.gui.MyGUI;
import fr.algo.com.gui.ReturnNavigationListener;
import fr.algo.com.object.TableObject;

/**
 * <b>Classe JPanel de controle </b>
 * <p>
 *   Cette classe gère les différents JPanel à afficher 
 * 
 * @author Thomas, Benjamin
 * @version 1.0
 */

@SuppressWarnings("serial")
public class MasterContainer extends JPanel {
	
	/**
     * ContainerInit principal
     * @see ContainerInit
     */
	private ContainerInit connect;
	
	/**
     * ContainerAdmin principal
     * @see ContainerAdmin
     */
	private ContainerAdmin admin;
	
	/**
     * Gui principal
     * @see MyGUI
     */
	private MyGUI gui;

	/**
	* Constructeur de la classe
	* @param gui dans lequel sera affiché le container
	* @see MyGUI
	*/
    public MasterContainer(MyGUI gui) {
    	this.gui = gui;
        setLayout(new GridLayout());
        presentMenu();
    }
    
    /**
     * 
     * Choisi le bon Container grâce à un listener
     * 
     */
    protected void presentMenu() {
        removeAll();
        
        	
        if (connect == null) {
        	connect = new ContainerInit(new ContainerInit.NavigationListener() {
        		@Override
    	        public void presentAdminContainer(ContainerInit source) {
        			admin = new ContainerAdmin(new ReturnNavigationListener<ContainerAdmin>() {
        				@Override
    	                public void returnFrom(ContainerAdmin source) {
        					presentMenu();
    	                }
    	            });
        			
        			gui.setJMenuBar(createMenuBar(gui));
        			gui.setResizable(true);
        			present(admin);
    	        }
    	                
        	});
    	        	
    	}
        	
        if(Main.connected == false) {
        	gui.setResizable(false);
        	add(connect);
    	}
        else {
        	present(admin);
        	gui.setResizable(true);
        	admin.update();
        }
        
        revalidate();
        repaint();
    }

    /**
     * 
     * Affiche le bon Container
     * 
     */
    protected void present(JPanel panel) {
        removeAll();
        add(panel);
        revalidate();
        repaint();
    }
    
    /**
     * Ajoute des élements à un menu
     * 
     * @param main_menu menu où l'on ajoute les components
     * @param components liste de JComponents
     */
    public void addMenu(JComponent main_menu, JComponent... components ) {
    	
    	for(JComponent component : components) {
    		main_menu.add(component);
    	}
    }
    
    /**
     * Créer le menu ainsi que ses elements
     * 
     * @param gui instance de MyGUI
     * @see MyGUI
     * 
     * @return le menu
     */
    public JMenuBar createMenuBar(MyGUI gui) {
    	
    	JMenuBar menuBar = new JMenuBar();
    	JMenu menu = new JMenu("Fichier");
    	JMenuItem menuItem = new JMenuItem("Nouvelle Fenêtre");
    	
    	
    	JMenu menu2 = new JMenu("Edition");
    	JMenuItem menu2Item = new JMenuItem("AJOUTER TABLE");
    	JMenuItem menu2Item2 = new JMenuItem("SUPPRIMER TABLE");
    	
    	JMenu menu3 = new JMenu("Help");
    	JMenuItem menu3Item = new JMenuItem("Github");
    	JMenuItem menu3Item2 = new JMenuItem("Trello");
		
    	addMenu(menuBar, menu, menu2, menu3);
    	addMenu(menu, menuItem);
    	addMenu(menu2, menu2Item, menu2Item2);
    	addMenu(menu3, menu3Item, menu3Item2);
		
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				
				MyGUI gui = new MyGUI();
				
				gui.setVisible(true);
				
			}
		});
		
		menu2Item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				removeAll();
				ContainerCreateTable container = new ContainerCreateTable(getMasterContainer());
				add(container);
				revalidate();
		        repaint();
			}
		});
		
		menu2Item2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				TableObject table = ContainerAdmin.getCurrentTable();
				
				if(table == null) return;
				
				table.deleteTable();
				
				presentMenu();
			
			}
		});
		
		menu3Item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				try {
					java.awt.Desktop.getDesktop().browse(new URI("https://github.com/Brocowlee/Proj_GoodFilms"));
				} catch (IOException | URISyntaxException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		menu3Item2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				try {
					java.awt.Desktop.getDesktop().browse(new URI("https://trello.com/b/Pj7xMKxc/projgoodfilms"));
				} catch (IOException | URISyntaxException e1) {
					e1.printStackTrace();
				}
								
			}
		});


    	
    	return menuBar;
    }
    
    /**
     * Mise à jour du menu des tables dès l'ajout d'une nouvelle table
     * 
     */
    public void returnFromCreateTable() {

    	presentMenu();
	
    }
    
    /**
     * Getter d'une instance de ContainerAdmin
     * 
     * @return instance de ContainerAdmin
     * @see ContainerAdmin
     */
    public ContainerAdmin getContainerAdmin() {
		return this.admin;
    	
    }
    
    /**
     * Getter d'une instance de MasterContainer
     * 
     * @return instance de MasterContainer
     * @see MasterContainer
     */
    public MasterContainer getMasterContainer() {
		return this;
		
	}
}