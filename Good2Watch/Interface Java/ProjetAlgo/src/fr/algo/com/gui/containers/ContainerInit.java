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
import fr.algo.com.gui.ReturnNavigationListener;
import fr.algo.com.gui.WarningGui;

/**
 * <b>Classe JPanel de connection</b>
 * <p>
 *   Cette classe va gérer la page de connection
 * 
 * @author Thomas, Benjamin
 * @version 1.0
 */

@SuppressWarnings("serial")
public class ContainerInit extends JPanel{
	
	/**
	 * <b>Interface Listener</b>
	 * <p>
	 *   Interface de navigation
	 * 
	 * @see ReturnNavigationListener
	 * @author Thomas, Benjamin
	 * @version 1.0
	 */
	public static interface NavigationListener {
		/**
		* Methode de retour pour MasterContainer
		* @see MasterContainer
		* 
		* @param source Source de l'information
		*/
        public void presentAdminContainer(ContainerInit source);
    }
	
	/**
     * Bouton connexion
     */
	private JButton connect = new JButton("Connexion");
	
	/**
     * Label pour l'identifiant
     */
	private JLabel lIdentifiant = new JLabel("Identifiant : ");
	
	/**
     * Zone de texte pour l'indentifiant
     */
	private JTextField tIdentifiant = new JTextField("");
	
	/**
     * Label pour le mot de passe
     */
	private JLabel lMdp = new JLabel("Mot de passe : ");
	
	/**
     * Zone de texte pour le mot de passe
     */
	private JPasswordField tMdp = new JPasswordField("");
	
	/**
     * Interface listener
     */
	private NavigationListener navigationListener;
	
	
	/**
	* Constructeur de la classe
	* @param navigationListener listener de retour
	* @see NavigationListener
	*/
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
		
		
		add(lIdentifiant);
		
		tIdentifiant.setMinimumSize(new Dimension(200,20));
		tIdentifiant.setMaximumSize(new Dimension(200,lIdentifiant.getHeight()));
		add(tIdentifiant);
		
		
		add(lMdp);
		
		tMdp.setMinimumSize(new Dimension(200,20));
		tMdp.setMaximumSize(new Dimension(200,lMdp.getHeight()));
		add(tMdp);
		
		connect.setMinimumSize(new Dimension(200,20));
		connect.setMaximumSize(new Dimension(200,20));
		add(connect);
		
		
		layout.setHorizontalGroup(
				  	layout.createSequentialGroup()
				  	  .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				  			  .addComponent(lIdentifiant)
				  			  .addComponent(lMdp))
				      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				    		  .addComponent(tIdentifiant)
				    		  .addComponent(tMdp)
				      		  .addComponent(connect))
				      
				);
		layout.setVerticalGroup(
				   layout.createSequentialGroup()
				   		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				   			.addComponent(lIdentifiant)
						   	.addComponent(tIdentifiant))
				   		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				   				.addComponent(lMdp)
				   				.addComponent(tMdp))
		
				   		.addComponent(connect)
				);
		
		this.connect.addActionListener(new ActionListener() {
		    @SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
		    	
		    	String login = tIdentifiant.getText();
		    	String password = tMdp.getText();
		    	
		    	if(isKnownLogin(login)) {
		    		
		    		if(isAdmin(login)) {
		    			String current_pass = BCrypt.hashpw(password, getSaltFromLogin(login));
				    	
						if (current_pass.equalsIgnoreCase(getPasswordFromLogin(login))) {
							
							getNavigationListener().presentAdminContainer(ContainerInit.this);	
							Main.connected = true;
							
							
				    	} else {
				    		new WarningGui("Login ou mot de passe incorrect.").setVisible(true);
						} 
		    		} else {
		    			new WarningGui("Ce compte n'est pas autorisé.").setVisible(true);
		    		}
		    		

		    	} else {
		    		new WarningGui("Login ou mot de passe incorrect.").setVisible(true);
		    	}
		    	
		    }
		    
		});

		
		
	}
	
	/**
     * Demande a la base de données si l'identifiant d'un utilisateur est un admin
     * 
     * @param login identifiant de l'utilisateur
     */
	private boolean isAdmin(String login) {
		
		int count = 0;
		
		try {
			ResultSet rs = Main.database.querySQL("SELECT admin as value FROM utilisateur WHERE login = '" + login + "';");
			
			rs.next();
			count = rs.getInt("value");
			rs.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return count == 1;
		
	}
	
	/**
     * Demande a la base de données si l'identifiant d'un utilisateur est connu
     * 
     * @param login identifiant de l'utilisateur
     */
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
	
	/**
     * Demande a la base de données le mot de passe
     * 
     * @param login identifiant de l'utilisateur
     */
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

	/**
     * Hash le mot de passe entré par l'utilisateur
     * 
     * 
     * @param login identifiant de l'utilisateur
     * @param password mot de passe de l'utilisateur
     * @return un String contenant le mot de passe après le hash
     */
	public String getHashFromString(String login, String password) {
		
		return BCrypt.hashpw(password, getSaltFromLogin(login));
		
	}
	
	/**
     * Demande a la base de données le salt d'un identifiant
     * 
     * @param login identifiant de l'utilisateur
     * @return un String contenant le salt
     */
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

	/**
     * Getter d'un Listener
     * 
     * @return un Listener de navigation
     * @see NavigationListener
     */
	protected NavigationListener getNavigationListener() {
        return navigationListener;
    }
}