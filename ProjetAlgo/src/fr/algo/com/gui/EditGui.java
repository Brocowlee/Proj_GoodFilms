package fr.algo.com.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import fr.algo.com.Main;
import fr.algo.com.gui.containers.ContainerAdmin;
import fr.algo.com.gui.containers.JItems.JItemButton;
import fr.algo.com.object.TableObject;

/**
 * <b>Classe permettant d'éditer une ligne d'une table dans un GUI</b>
 * <p>
 *   Cette classe va réaliser le gui permettant la modification d'une ligne
 * 
 * @author Thomas, Benjamin
 * @version 1.0
 */

@SuppressWarnings("serial")
public class EditGui extends JFrame{

	/**
     * Image servant d'icon
     */
	private ImageIcon icon = new ImageIcon("./Icon.jpg");
	
	/**
     * Liste de zones de texte
     */
	private ArrayList<JTextField> textField_list = new ArrayList<>();	
	
	/**
     * Objet TableObject contenant la table d'où la ligne provient
     */
	private TableObject table;
	
	/**
	* Constructeur de la classe
	* @param containerAdmin panel contenant l'affichage de la table
	* @param button bouton éditer d'une ligne qui a été cliqué 
	*/
	public EditGui(ContainerAdmin containerAdmin, JItemButton button) {
		
		setTitle("Editer une donnée");
		setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(800,300));
        setMaximumSize(new Dimension(800,300));
		setLocationRelativeTo(null);
		setResizable(false);
		pack();
		
		JPanel pan = new JPanel();
        
	 	JPanel panel1 = new JPanel();
	 	panel1.setLayout(new BoxLayout(panel1,BoxLayout.X_AXIS));
	 	
	 	this.table = containerAdmin.edit_table.get(button.getButton());
	 	
	 	
	 	for(int i = 1; i < this.table.getColumnName().size(); i++) {
	 		
	 		String column = (String) this.table.getColumnName().toArray()[i];
	 		
	 		JLabel lab = new JLabel(column);
	 		
	 		lab.setBorder(new EmptyBorder(0,50,0,0));
	 		setSizeAdd(lab,new Dimension(200,50),panel1);
            panel1.add(lab);
            
	 	}
	 	
        panel1.setBackground(Color.LIGHT_GRAY);

        panel1.setBounds(0, 0, 900, 50);
        pan.add(panel1);
        
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2,BoxLayout.X_AXIS));
        
        //---------------------------------------------------------------------------------------------
        
        List<String> list = this.table.selectLigne(containerAdmin.edit_ids.get(button.getButton()));
        
        for(String attribut : list) {
	 		
        	JLabel lab = new JLabel(attribut);
    		
            lab.setBorder(new EmptyBorder(0,10,0,5));
            lab.setAlignmentX(CENTER_ALIGNMENT);
            setSizeAdd(lab,new Dimension(200,50),panel2);
	 	}
    	
        pan.add(panel2);
        
        //---------------------------------------------------------------------------------------------
        
        JPanel a_panel = new JPanel();
        a_panel.setLayout(new BoxLayout(a_panel,BoxLayout.X_AXIS));
        
        for(int i = 1; i < this.table.getColumnName().size(); i++) {
	 		
        	JTextField textField = new JTextField();
    		
        	textField.setBorder(new EmptyBorder(0,10,0,5));
        	textField.setBorder(BorderFactory.createLineBorder(Color.black));
        	setSizeAdd(textField,new Dimension(200,50),a_panel);
    		this.textField_list.add(textField);
	 	}
    	
        pan.add(a_panel);
        
        
        
        GridLayout g = new GridLayout(3,1,5,5);
        pan.setLayout(g);
        
        JScrollPane scrollPane = new JScrollPane(pan);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(0, 0, 785, 200);
        JPanel contentPane = new JPanel(null);
        contentPane.setMinimumSize(new Dimension(500, 400));
        contentPane.setMaximumSize(new Dimension(500, 400));
        contentPane.add(scrollPane);
        
        JButton bModif = new JButton("Modifier");
        setSizeAdd(bModif,new Dimension(800,505),contentPane);
        bModif.setBounds(350, 205, 100, 50);
        
        setContentPane(contentPane);
        
        revalidate();
        repaint();
        
        bModif.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	
		    	
		    	int id = Integer.parseInt(containerAdmin.edit_ids.get(button.getButton()));
		    	
		    	ArrayList<String> values = new ArrayList<>();
		    	List<Integer> indexes = new ArrayList<>();
		    	
		    	
		    	for(int i = 0; i < textField_list.size(); i++) {
		    		
		    		JTextField text = textField_list.get(i);
		    		
		    		if(text.getText().length() > 0) {
		    			
		    			values.add(text.getText());
		    			indexes.add(i + 1);
		    			
		    		}
		    	}
		    	
		    	getEditGui().dispose();
		    	
		    	if(values.isEmpty() && indexes.isEmpty()) return;
		    	
		    	table.updateInto(id, indexes, values);
		    	
		    	
		    	containerAdmin.showTable(table);
		    }
		    
		});
        
	}
	
	/**
     * Definit la taille d'un composant et l'ajoute à son JPanel
     * 
     * @param component composant de GUI
     * @param dim dimension
     * @param pan Jpanel 
     */
	public static void setSizeAdd(JComponent component,Dimension dim,JPanel pan) {
		
		component.setMinimumSize(dim);
        component.setMaximumSize(dim);
        pan.add(component);
	}
	
	/**
     * Retourne une instance EditGui
     *
     * @return Une instance d'un EditGui
     * @see EditGui
     */
	public EditGui getEditGui() {
		return this;
		
	}
}