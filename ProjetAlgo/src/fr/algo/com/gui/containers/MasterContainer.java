package fr.algo.com.gui.containers;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

import fr.algo.com.Main;
import fr.algo.com.gui.ReturnNavigationListener;

public class MasterContainer extends JPanel{
	
	private ContainerInit Connect;

    public MasterContainer() {
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
}