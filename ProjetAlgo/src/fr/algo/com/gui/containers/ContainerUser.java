package fr.algo.com.gui.containers;

import javax.swing.JPanel;

import fr.algo.com.gui.ReturnNavigationListener;

public class ContainerUser extends JPanel {
	
private ReturnNavigationListener<ContainerUser> navigationListener;
	
	
	public ContainerUser(ReturnNavigationListener<ContainerUser> navigationListener){
		this.navigationListener = navigationListener;
		

	
		
		
		
		
	}
	public ReturnNavigationListener<ContainerUser> getReturnNavigationListener() {
        return navigationListener;
    }

}
