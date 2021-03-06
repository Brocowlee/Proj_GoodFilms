package fr.algo.com.gui.containers;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import fr.algo.com.gui.AddingGui;
import fr.algo.com.gui.ReturnNavigationListener;
import fr.algo.com.gui.containers.JItems.JItemButton;
import fr.algo.com.handler.InitTable;
import fr.algo.com.object.TableObject;

/**
 * <b>Classe JPanel d'affichage des tables</b>
 * <p>
 *   Cette classe r?alise l'affichage des tables
 * 
 * @author Thomas, Benjamin
 * @version 1.0
 */

@SuppressWarnings("serial")
public class ContainerAdmin extends JPanel {
	
	/**
     * HashMap reliant une checkbox ? l'id d'une ligne
     */
	public HashMap<JCheckBox, String> check_box_ids = new HashMap<>();
	
	/**
     * HashMap reliant une checkbox ? un objet TableObject
     */
	public HashMap<JCheckBox, TableObject> check_box_table = new HashMap<>();
	
	/**
     * HashMap reliant un bouton d'?dition ? l'id d'une ligne
     */
	public HashMap<JButton, String> edit_ids = new HashMap<>();
	
	/**
     * HashMap reliant un bouton d'?dition ? un objet TableObject
     */
	public HashMap<JButton, TableObject> edit_table = new HashMap<>();
	
	/**
     * HashMap reliant un bouton de table ? un objet TableObject
     */
	public static HashMap<JItemButton, TableObject> button_table = new HashMap<>();
	
	/**
     * Image bouton ?diter
     */
	private ImageIcon editIcon = new ImageIcon(new ImageIcon("./edit.png").getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
	
	/**
     * Label r?ussite de la suppression partie 1
     */
	private JLabel lDelete = new JLabel("La suppression");
	
	/**
     * Label r?ussite de la suppression partie 2
     */
    private JLabel lDelete2 = new JLabel("a ?t? effectu?");
    
    /**
     * Label ?chec de la suppression partie 1
     */
    private JLabel lDeleteRefused = new JLabel("Veuillez choisir");
    
    /**
     * Label ?chec de la suppression partie 2
     */
    private JLabel lDeleteRefused2 = new JLabel("une donn?e");
    
    /**
     * Label r?ussite de l'ajout partie 1
     */
    private JLabel lAdd = new JLabel("L'ajout");
    
    /**
     * Label r?ussite de l'ajout partie 2
     */
    private JLabel lAdd2 = new JLabel("a ?t? effectu?");
    
    /**
     * Label ?chec de l'ajout partie 1
     */
    private JLabel lAddRefused = new JLabel("Veuillez choisir");
    
    /**
     * Label ?chec de l'ajout partie 2
     */
    private JLabel lAddRefused2 = new JLabel("une table");
    
    /**
     * Listener de retour
     */
	private ReturnNavigationListener<ContainerAdmin> navigationListener;
	
	/**
	* Constructeur de la classe
	* @param navigationListener listener de retour
	*/
	public ContainerAdmin(ReturnNavigationListener<ContainerAdmin> navigationListener){
			this.navigationListener = navigationListener;
		
			setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		 
			showMenu(null);
	        
	}
	
	/**
	* Methode de mise ? jour de la classe
	*
	*/
	public void update() {
		removeAll();
		showMenu(null);
	}
	
	/**
	* Methode permettant de changer la visibilit? des boutons de r?ussite d'ajout
	*
	*@param bool un boolean
	*/
	public void setAddButton(boolean bool) {
		
		this.lAdd.setVisible(bool);
		this.lAdd2.setVisible(bool);
	}
	
	/**
	* Methode permettant d'afficher le menu des tables
	*
	*@param table_name nom de la table s?lection?e
	*/
	public void showMenu(String table_name) {
		
		button_table.clear();
		
		String type = "Table";
		JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        for(TableObject table : InitTable.liste_tables.values()) {
        	
          JItemButton button = new JItemButton(table.getName(), this, type);
          button_table.put(button,table);
          
          button.getButton().setMinimumSize(new Dimension(150,30));
      	  button.getButton().setMaximumSize(new Dimension(150,30));
        
      	  if(button.getName().equalsIgnoreCase(table_name)) {
      		  button.getButton().setBackground(Color.LIGHT_GRAY);
      		  button.getButton().setForeground(Color.BLACK);
      	  }
      	  
          panel.add(button.getButton());
      	
        }
        
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(0, 0, 150, 250);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.black));
        JPanel contentPane = new JPanel(null);
        contentPane.setMinimumSize(new Dimension(150,950));
        contentPane.setMaximumSize(new Dimension(150,950));
        contentPane.add(scrollPane);
        
        
        JLabel Loption = new JLabel("Option :");
        Loption.setBounds(45, 250, 100, 25);
        Loption.setFont(new Font("Serif", Font.PLAIN, 20));
        contentPane.add(Loption);
        
        JItemButton delete = new JItemButton("Supprimer",this, "Supprimer");
        delete.getButton().setMinimumSize(new Dimension(20,20));
        delete.getButton().setMaximumSize(new Dimension(20,20));
        delete.getButton().setBounds(25, 300, 100, 25);
        contentPane.add(delete.getButton());
        
        createTextArea(this.lDelete, contentPane, new int[] {35, 340, 100, 25}, Color.RED, false);
        createTextArea(this.lDelete2, contentPane, new int[] {40, 360, 100, 25}, Color.RED, false);
        createTextArea(this.lDeleteRefused, contentPane, new int[] {35, 340, 100, 25}, Color.RED, false);
        createTextArea(this.lDeleteRefused2, contentPane, new int[] {40, 360, 100, 25}, Color.RED, false);
        createTextArea(this.lDeleteRefused2, contentPane, new int[] {40, 360, 100, 25}, Color.RED, false);
        createTextArea(this.lAddRefused, contentPane, new int[] {40, 440, 100, 25}, Color.RED, false);
        createTextArea(this.lAddRefused2, contentPane, new int[] {40, 460, 100, 25}, Color.RED, false);
        createTextArea(this.lAdd, contentPane, new int[] {50, 440, 100, 25}, Color.GREEN, false);
        createTextArea(this.lAdd2, contentPane, new int[] {40, 460, 100, 25}, Color.GREEN, false);
        
        JItemButton adding = new JItemButton("Ajouter",this, "Ajouter");
        adding.getButton().setMinimumSize(new Dimension(20,20));
        adding.getButton().setMaximumSize(new Dimension(20,20));
        adding.getButton().setBounds(25, 400, 100, 25);
        contentPane.add(adding.getButton());
        
        
        
        this.add(contentPane);
		
	}
	
	/**
	* Methode permettant de changer le format des JLabel
	*
	*@param text le label ? modifier
	*@param contentPane le JPanel o? l'on ajoute le JLabel
	*@param list liste de positions
	*@param color couleur de fond du JLabel
	*@param bool un boolean pour la visibilit?
	*/
	private void createTextArea(JLabel text, JPanel contentPane, int[] list, Color color, boolean bool) {
		
		text.setBounds(list[0], list[1], list[2], list[3]);
		text.setForeground(color);
		text.setFont(new Font("Serif", Font.PLAIN, 13));
		text.setVisible(bool);
        contentPane.add(text);
		
	}
	
	/**
	* Methode d'ajout d'une ligne
	* @see AddingGui
	*
	*/
	public void adding() {
		TableObject table = getCurrentTable();
		
		if(table == null) {
			this.lAddRefused.setVisible(true);
	        this.lAddRefused2.setVisible(true);
			return;
		}
		
		AddingGui gui = new AddingGui(this, table);
		
		gui.setVisible(true);
		
	}

	/**
	* Methode de suppression d'une ligne
	* @see TableObject
	*
	*/
	public void delete() {
		
		this.lDelete.setVisible(false);
        this.lDelete2.setVisible(false);
        this.lDeleteRefused.setVisible(false);
        this.lDeleteRefused2.setVisible(false);
        this.lAddRefused.setVisible(false);
        this.lAddRefused2.setVisible(false);
        
		TableObject table = null;
		
		boolean suppressed = false;
		
		for(JCheckBox box : check_box_ids.keySet()){
			
			if(!check_box_ids.keySet().isEmpty()) {
				
				if(box.isSelected()) {
					
					int index = Integer.parseInt(check_box_ids.get(box));
					table = check_box_table.get(box);
					
					suppressed = true;
					table.deleteLine(index);
				}
				
			}
		}
		
		if(table == null) {
			this.lDeleteRefused.setVisible(true);
	        this.lDeleteRefused2.setVisible(true);
			return;
		}
		
		if(!suppressed) return;
		
		showTable(table);
		
		this.lDelete.setVisible(true);
        this.lDelete2.setVisible(true);
		
	}
	
	/**
	* Methode permettant d'obtenir la liste des valeurs maximales entre deux attributs
	*
	*@param table la table dans laquelle on recup?re les attributs
	*/
	private List<Integer> getMaxValues(TableObject table) {
		
		 List<Integer> maxvalues = new ArrayList<>();
	        
	     for(String attribut : table.getMaxSizeFromColumn()) {
	        	
	        	JLabel lab = new JLabel(attribut);
	        	maxvalues.add(lab.getMaximumSize().width);
	        	
	     }
	     
	     return maxvalues;
	}
	
	/**
	* Methode permettant d'afficher les informations d'une table
	*
	*@param table table s?lection?e
	*@see TableObject
	*/
	public void showTable(TableObject table) {
		
		this.check_box_ids.clear();
		this.check_box_table.clear();
		this.edit_ids.clear();
		this.edit_table.clear();
		removeAll();
		
		showMenu(table.getName());
		
		JPanel pan = new JPanel();
        pan.setBorder(BorderFactory.createLineBorder(Color.black));
        
        
	 	JPanel panel1 = new JPanel();
	 	panel1.setLayout(new BoxLayout(panel1,BoxLayout.X_AXIS));
	 	
	 	List<Integer> maxvalues = getMaxValues(table);   
	 	
	 	for(int i = 0; i < table.getColumnName().size(); i++) {
	 		
	 		String column = (String) table.getColumnName().toArray()[i];
	 		
	 		JLabel lab = new JLabel(column);
	 		
	 		int space = 0;
	 		
	 		if(lab.getMaximumSize().width > maxvalues.get(i)) {
	 			
	 			maxvalues.set(i, lab.getMaximumSize().width);
	 			space += maxvalues.get(i) + 30;
	 		} else {
	 			
	 			if(i < maxvalues.size()-1) {
	 				space += (maxvalues.get(i) / 2) + 30;
	 				space += (maxvalues.get(i+1) / 2);
	 				space += (maxvalues.get(i+1) / 35);
	 			} else {
	 				space += maxvalues.get(i) + 30;
	 			}
	 		}
	 		
    		int real_space = space - lab.getMaximumSize().width;
    		
    		if(i == 0) {
    			
    			if(!table.isRelationTable()) {
    				lab.setBorder(new EmptyBorder(0,60,0,real_space));
    			} else {
    				lab.setBorder(new EmptyBorder(0,30,0,real_space));
    			}
    			
    			
    		} else {
    			lab.setBorder(new EmptyBorder(0,10,0,real_space));
    		}
    		
            panel1.add(lab);
    		
	 	}
	 	
        panel1.setBackground(Color.LIGHT_GRAY);
        pan.add(panel1);
        
        
        for(List<String> list : table.selectAll()) {
        	
        	JPanel a_panel = new JPanel();
            a_panel.setLayout(new BoxLayout(a_panel,BoxLayout.X_AXIS));
            
        	JCheckBox checkbox = new JCheckBox();
            a_panel.add(checkbox);
            check_box_ids.put(checkbox, list.get(0));
            check_box_table.put(checkbox, table);
            
            if(!table.isRelationTable()) {
            	  JItemButton editbutton = new JItemButton(this.editIcon,this,"Edit");
                  a_panel.add(editbutton.getButton());
                  edit_ids.put(editbutton.getButton(), list.get(0));
                  edit_table.put(editbutton.getButton(), table);
            }
            
        	for(String attribut : list) {
        		
        		int currentColumnIndex = list.indexOf(attribut);
        		int currentColumnMaxSize = maxvalues.get(currentColumnIndex);
        		
        		JLabel lab = new JLabel(attribut);
        		
        		int space = currentColumnMaxSize + 30;
        		int real_space = space - lab.getMaximumSize().width;
        		
        		
                lab.setBorder(new EmptyBorder(0,10,0, real_space));
                
                a_panel.add(lab);
                
        	}
        	
            pan.add(a_panel);
        	
        }
        
        int total_lines = table.getTotalLine() + 1;
        
        if(total_lines < 29) total_lines = 29;
        
        GridLayout g = new GridLayout(total_lines,1,5,5);
        pan.setLayout(g);
        
        JScrollPane scrollPane2 = new JScrollPane(pan);
        scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        
        scrollPane2.setBounds(0, 0, this.getWidth() - 150, 900);
        
        
        JPanel contentPane2 = new JPanel(null);
        contentPane2.setMinimumSize(new Dimension(this.getWidth() - 150,1000));
        contentPane2.setMaximumSize(new Dimension(this.getWidth() - 150,1000));
        contentPane2.add(scrollPane2);
        
        this.add(contentPane2);
        
        this.setMinimumSize(new Dimension(800,500));
        this.setMaximumSize(new Dimension(800,500));
        revalidate();
        repaint();
		
		
	}
	
	/**
	* Methode permettant d'obtenir la table actuellement selection?e
	*
	*@return table s?lection?e
	*@see TableObject
	*/
	public static TableObject getCurrentTable() {
		
		TableObject table = null;
		
		for(JItemButton button : button_table.keySet()) {  
			
			if(button.getButton().getForeground() == Color.BLACK){
				 table = button_table.get(button) ;
			}
		}
		
		return table;
	}
	
	/**
	* Getter du Listener
	*
	*@return le listener de la classe
	*/
	public ReturnNavigationListener<ContainerAdmin> getReturnNavigationListener() {
        return navigationListener;
    }
}
