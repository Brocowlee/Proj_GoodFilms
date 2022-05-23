package fr.algo.com.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingConstants;

import fr.algo.com.utils.Config;

/**
 * <b>Classe GUI d'erreur </b>
 * <p>
 *   Cette classe va réaliser le gui permettant d'avoir un affichage d'erreur
 * 
 * @author Thomas, Benjamin
 * @version 1.0
 */

@SuppressWarnings("serial")
public class WarningGui extends JFrame{
	
	/**
     * Image panneau attention.
     */
	private ImageIcon attention = new ImageIcon(new ImageIcon("./attention.png").getImage().getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH));
	
	/**
     * message d'erreur.
     */
	private String message;
	
	/**
     * Button "ok".
     */
	private JButton button;
	
	/**
	* Constructeur de la classe
	* @param message message à afficher
	*/
	public WarningGui(String message) {
	
		this.message = message;
		
		setTitle("Warning");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(400,200));
        setMaximumSize(new Dimension(400,200));
		setLocationRelativeTo(null);
		setResizable(false);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.ERROR_DIALOG);
		pack();
		
		JPanel contentPane = new JPanel(new BorderLayout(0, 0));
		
		JLabel LMessage = new JLabel(this.message, SwingConstants.CENTER);
		LMessage.setForeground(Color.RED);
		contentPane.add(LMessage,BorderLayout.CENTER);
		
		this.button = new JButton("OK");
		contentPane.add(button,BorderLayout.SOUTH);
		
		JLabel Limg = new JLabel(this.attention);
		contentPane.add(Limg,BorderLayout.WEST);
		
		add(contentPane);
		
		this.button.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    
		    		getWarningGui().dispose();
		    }
		    
		});
		
	}
	
	 /**
     * Retourne un objet WarningGUI.
     * 
     * @return Une instance d'un WarningGUI
     * 
     */
	public WarningGui getWarningGui() {
		return this;
		
	}
}
