package fr.algo.com.gui.containers;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.mindrot.jbcrypt.BCrypt;

import fr.algo.com.Main;
import fr.algo.com.gui.WarningGui;


@SuppressWarnings("serial")
public class ContainerInit extends JPanel{
	
	public static interface NavigationListener {
        public void presentAdminContainer(ContainerInit source);
		public void presentUserContainer(ContainerInit source);
    }
	
	private JButton connect = new JButton("Connexion");
	
	private JLabel Lidentifiant = new JLabel("Identifiant : ");
	private JTextField Tidentifiant = new JTextField("");
	
	private JLabel Lmdp = new JLabel("Mot de passe : ");
	private JPasswordField Tmdp = new JPasswordField("");
	
	
	private NavigationListener navigationListener;
	
	public ContainerInit(NavigationListener navigationListener){
		this.navigationListener = navigationListener;
		
		if(Main.connected) {
			getNavigationListener().presentAdminContainer(ContainerInit.this);
    	} 
		
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		setBorder(BorderFactory.createLineBorder(Color.black));
		setBorder(new EmptyBorder(400, 275, 0, 0));
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
		
		
		layout.setHorizontalGroup(
				  	layout.createSequentialGroup()
				  	  .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				  			  .addComponent(Lidentifiant)
				  			  .addComponent(Lmdp))
				      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				    		  .addComponent(Tidentifiant)
				    		  .addComponent(Tmdp)
				      		  .addComponent(connect))
				      
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
				);
		
		this.connect.addActionListener(new ActionListener() {
		    @SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
		    	
		    	String login = Tidentifiant.getText();
		    	String password = Tmdp.getText();
		    	
		    	if(isKnownLogin(login)) {
		    		
		    		String current_pass = BCrypt.hashpw(password, getSaltFromLogin(login));
			    	
					if (current_pass.equalsIgnoreCase(getPasswordFromLogin(login))) {
						getNavigationListener().presentAdminContainer(ContainerInit.this);	
						Main.connected = true;
			    	} else {
			    		//TODO Régler le problème de non affichage
			    		new WarningGui("Login ou mot de passe incorrect.").setVisible(true);;
					} 

		    	} else {
		    		//TODO Régler le problème de non affichage
		    		new WarningGui("Login ou mot de passe incorrect.").setVisible(true);;
		    	}
		    	
		    }
		    
		});

		
		
	}
	
	private boolean isKnownLogin(String login) {
		
		int count = 0;
		
		try {
			ResultSet rs = Main.database.querySQL("SELECT COUNT(*) AS rowcount FROM utilisateur WHERE login = '" + login + "';");
			
			rs.next();
			count = rs.getInt("rowcount");
			rs.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return count > 0;
		
	}
	
	private String getPasswordFromLogin(String login) {
		
		String password = null;
		
		try {
			ResultSet rs = Main.database.querySQL("select mot_de_passe from utilisateur where login = '" + login + "';");
			rs.next();
			password = rs.getString("mot_de_passe");
			rs.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return password;
		
	}

	public String getHashFromString(String login, String password) {
		
		return BCrypt.hashpw(password, getSaltFromLogin(login));
		
	}
	
	private String getSaltFromLogin(String login) {
		
		String salt = null;
		
		try {
			ResultSet rs = Main.database.querySQL("select salt from utilisateur where login = '" + login + "';");
			rs.next();
			salt = rs.getString("salt");
			rs.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return salt;
		
	}

	protected NavigationListener getNavigationListener() {
        return navigationListener;
    }
}