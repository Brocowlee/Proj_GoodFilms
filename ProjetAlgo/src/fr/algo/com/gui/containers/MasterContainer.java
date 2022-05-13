package fr.algo.com.gui.containers;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import fr.algo.com.Main;
import fr.algo.com.gui.MyGUI;
import fr.algo.com.gui.ReturnNavigationListener;
import fr.algo.com.object.TableObject;

@SuppressWarnings("serial")
public class MasterContainer extends JPanel {
	
	private ContainerInit Connect;
	private ContainerAdmin Admin;
	private MyGUI gui;

    public MasterContainer(MyGUI gui) {
    	this.gui = gui;
        setLayout(new GridLayout());
        presentMenu();
    }
    
    protected void presentMenu() {
        removeAll();
        
        	
        if (Connect == null) {
        	Connect = new ContainerInit(new ContainerInit.NavigationListener() {
        		@Override
    	        public void presentAdminContainer(ContainerInit source) {
        			Admin = new ContainerAdmin(new ReturnNavigationListener<ContainerAdmin>() {
        				@Override
    	                public void returnFrom(ContainerAdmin source) {
        					presentMenu();
    	                }
    	            });
        			
        			gui.setJMenuBar(CreateMenuBar(gui));
        			gui.setResizable(true);
        			present(Admin);
    	        }
    	              
    	        @Override
    	        public void presentUserContainer(ContainerInit source) {
    	        	ContainerUser containerUser = new ContainerUser(new ReturnNavigationListener<ContainerUser>() {
    	        		@Override
    	                public void returnFrom(ContainerUser source) {
    	        			presentMenu();
    	                }
    	            });
    	        	gui.setResizable(true);
    	            present(containerUser);
    	        }
    	                
        	});
    	        	
    	}
        	
        if(Main.connected == false) {
        	gui.setResizable(false);
        	add(Connect);
    	}
        else {
        	present(Admin);
        	gui.setResizable(true);
        	Admin.maj();
        }
        
        revalidate();
        repaint();
    }

    protected void present(JPanel panel) {
        removeAll();
        add(panel);
        revalidate();
        repaint();
    }
    
    public void addMenu(JComponent main_menu, JComponent... components ) {
    	
    	for(JComponent component : components) {
    		main_menu.add(component);
    	}
    }
    
    public JMenuBar CreateMenuBar(MyGUI gui) {
    	
    	JMenuBar menuBar = new JMenuBar();
    	JMenu menu = new JMenu("Fichier");
    	JMenuItem menuItem = new JMenuItem("Nouvelle Fenêtre");
    	
    	
    	JMenu menu2 = new JMenu("Edition");
    	JMenuItem menu2Item = new JMenuItem("AJOUTER TABLE");
    	JMenuItem menu2Item2 = new JMenuItem("SUPPRIMER TABLE");
    	
    	JMenu menu3 = new JMenu("Help");
    	JMenuItem menu3Item = new JMenuItem("Site");
    	JMenuItem menu3Item2 = new JMenuItem("GitHub");
		
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
    	
    	return menuBar;
    }
    
    public void ReturnFromCreateTabe() {

    	presentMenu();
	
    }
    
    public ContainerAdmin getContainerAdmin() {
		return this.Admin;
    	
    }
    
    public MasterContainer getMasterContainer() {
		return this;
		
	}
}