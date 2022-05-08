package fr.algo.com.gui.containers;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class ContainerInit extends JPanel{
	
	public static interface NavigationListener {
        public void presentAdminContainer(ContainerInit source);
		public void presentUserContainer(ContainerInit source);
    }

	
	private JButton connect = new JButton("Connexion");
	private JButton inscri = new JButton("Inscription");
	
	private JLabel Lidentifiant = new JLabel("Identifiant : ");
	private JTextField Tidentifiant = new JTextField("");
	
	private JLabel Lmdp = new JLabel("Mot de passe : ");
	private JPasswordField Tmdp = new JPasswordField("");
	
	
	private NavigationListener navigationListener;
	
	public ContainerInit(NavigationListener navigationListener){
		this.navigationListener = navigationListener;
		
		
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		setMinimumSize(new Dimension(340,190));
		setMaximumSize(new Dimension(340,190));
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		
		add(Lidentifiant);
		
		Tidentifiant.setMinimumSize(new Dimension(200,20));
		Tidentifiant.setMaximumSize(new Dimension(200,Lidentifiant.getHeight()));
		add(Tidentifiant);
		
		
		add(Lmdp);
		
		Tmdp.setMinimumSize(new Dimension(200,20));
		Tmdp.setMaximumSize(new Dimension(200,Lmdp.getHeight()));
		add(Tmdp);
		
		connect.setMinimumSize(new Dimension(200,20));
		connect.setMaximumSize(new Dimension(200,20));
		add(connect);
		
		inscri.setMinimumSize(new Dimension(200,20));
		inscri.setMaximumSize(new Dimension(200,20));
		add(inscri);
	
		
		layout.setHorizontalGroup(
				  	layout.createSequentialGroup()
				  	  .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				  			  .addComponent(Lidentifiant)
				  			  .addComponent(Lmdp))
				      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				    		  .addComponent(Tidentifiant)
				    		  .addComponent(Tmdp)
				      		  .addComponent(connect)
				      		  .addComponent(inscri))
				      
				);
		layout.setVerticalGroup(
				   layout.createSequentialGroup()
				   		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				   			.addComponent(Lidentifiant)
						   	.addComponent(Tidentifiant))
				   		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				   				.addComponent(Lmdp)
				   				.addComponent(Tmdp))
		
				   		.addComponent(connect)
				   		.addComponent(inscri)
				);
		
		
		this.connect.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	
		    	getNavigationListener().presentAdminContainer(ContainerInit.this);
		    	
		    }
		    
		});

		
		
	}
	
	protected NavigationListener getNavigationListener() {
        return navigationListener;
    }
}
