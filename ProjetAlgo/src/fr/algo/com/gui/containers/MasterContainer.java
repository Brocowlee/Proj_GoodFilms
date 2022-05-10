package fr.algo.com.gui.containers;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import fr.algo.com.gui.AddColumnGui;
import fr.algo.com.gui.MyGUI;
import fr.algo.com.gui.ReturnNavigationListener;

@SuppressWarnings("serial")
public class MasterContainer extends JPanel{
	
	private ContainerInit Connect;
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
                       ContainerAdmin containerAdmin = new ContainerAdmin(new ReturnNavigationListener<ContainerAdmin>() {
                        @Override
                        public void returnFrom(ContainerAdmin source) {
                            presentMenu();
                        }
                    });
                    setMinimumSize(containerAdmin.getMinimumSize());
                    setMaximumSize(containerAdmin.getMaximumSize());
                    setSize(containerAdmin.getMinimumSize());
                    gui.setJMenuBar(CreateMenuBar(gui));
                    present(containerAdmin);
                }
              
                @Override
                public void presentUserContainer(ContainerInit source) {
                	ContainerUser containerUser = new ContainerUser(new ReturnNavigationListener<ContainerUser>() {
                        @Override
                        public void returnFrom(ContainerUser source) {
                            presentMenu();
                        }
                    });
                    present(containerUser);
                }
                
            });
        }
        add(Connect);
        revalidate();
        repaint();
    }

    protected void present(JPanel panel) {
        removeAll();
        add(panel);
        revalidate();
        repaint();
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
		
    	menuBar.add(menu);
		menuBar.add(menu2);
		menuBar.add(menu3);
		
		menu.add(menuItem);
		menu2.add(menu2Item);
		menu2.add(menu2Item2);
		
		menu3.add(menu3Item);
		menu3.add(menu3Item2);
		
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				
				MyGUI gui = new MyGUI();
				
				gui.setVisible(true);
				
				/*WarningGui gui = new WarningGui("jjjjjjj");
				
				gui.setVisible(true);*/
				
			}
		});
		
		menu2Item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				removeAll();
				ContainerCreateTable container = new ContainerCreateTable();
				add(container);
				revalidate();
		        repaint();
			}
		});
		
		
		menu2Item2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
    	
    	return menuBar;
    }
}

