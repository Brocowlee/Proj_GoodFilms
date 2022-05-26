package fr.algo.com.gui;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import fr.algo.com.gui.containers.MasterContainer;

/**
 * <b>Classe GUI principal </b>
 * <p>
 *   Cette classe va réaliser le gui permettant d'avoir un affichage de toute les fonctionalité.
 * 
 * @author Thomas, Benjamin
 * @version 1.0
 */

@SuppressWarnings("serial")
public class MyGUI extends JFrame{

	/**
     * Image servant d'icon.
     */
	private ImageIcon icon = new ImageIcon("./Icon.jpg");

	
	/**
	* Constructeur de la classe
	*/
	public MyGUI() {
		
		setContentPane(new MasterContainer(this));

		//Application initialising :
		setTitle("ZeFilmViewer");
		setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(1000,1000));
        setMaximumSize(new Dimension(1000,1000));
		setLocationRelativeTo(null);

		pack();
		
	}
}
