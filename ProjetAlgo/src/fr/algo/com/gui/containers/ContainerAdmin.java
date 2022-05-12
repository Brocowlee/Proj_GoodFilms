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

@SuppressWarnings("serial")
public class ContainerAdmin extends JPanel {
	
	
	public HashMap<JCheckBox, String> check_box_ids = new HashMap<>();
	public HashMap<JCheckBox, TableObject> check_box_table = new HashMap<>();
	public HashMap<JButton, String> edit_ids = new HashMap<>();
	public HashMap<JButton, TableObject> edit_table = new HashMap<>();
	public static HashMap<JItemButton, TableObject> button_table = new HashMap<>();
	
	ImageIcon edit = new ImageIcon("./edit.png");
	Image imgedit = edit.getImage();
	private ImageIcon editIcon = new ImageIcon(imgedit.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
	
	
	private JLabel Ldelete = new JLabel("La suppression");
    private JLabel Ldelete2 = new JLabel("a été effectué");
    private JLabel Ldeleterefused = new JLabel("Veuillez choisir");
    private JLabel Ldeleterefused2 = new JLabel("une donnée");
    
    private JLabel Ladd = new JLabel("L'ajout");
    private JLabel Ladd2 = new JLabel("a été effectué");
    private JLabel Laddrefused = new JLabel("Veuillez choisir");
    private JLabel Laddrefused2 = new JLabel("une table");
    
	private ReturnNavigationListener<ContainerAdmin> navigationListener;
	
	
	public ContainerAdmin(ReturnNavigationListener<ContainerAdmin> navigationListener){
			this.navigationListener = navigationListener;
		
			setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		 
			showMenu(null);
	        
	}
	
	public void maj() {
		removeAll();
		showMenu(null);
	}
	
	public void setAddButton(boolean bool) {
		
		this.Ladd.setVisible(bool);
		this.Ladd2.setVisible(bool);
		
	}
	
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
        
        createTextArea(this.Ldelete, contentPane, new int[] {35, 340, 100, 25}, Color.RED, false);
        createTextArea(this.Ldelete2, contentPane, new int[] {40, 360, 100, 25}, Color.RED, false);
        createTextArea(this.Ldeleterefused, contentPane, new int[] {35, 340, 100, 25}, Color.RED, false);
        createTextArea(this.Ldeleterefused2, contentPane, new int[] {40, 360, 100, 25}, Color.RED, false);
        createTextArea(this.Ldeleterefused2, contentPane, new int[] {40, 360, 100, 25}, Color.RED, false);
        createTextArea(this.Laddrefused, contentPane, new int[] {40, 440, 100, 25}, Color.RED, false);
        createTextArea(this.Laddrefused2, contentPane, new int[] {40, 460, 100, 25}, Color.RED, false);
        createTextArea(this.Ladd, contentPane, new int[] {50, 440, 100, 25}, Color.GREEN, false);
        createTextArea(this.Ladd2, contentPane, new int[] {40, 460, 100, 25}, Color.GREEN, false);
        
        JItemButton adding = new JItemButton("Ajouter",this, "Ajouter");
        adding.getButton().setMinimumSize(new Dimension(20,20));
        adding.getButton().setMaximumSize(new Dimension(20,20));
        adding.getButton().setBounds(25, 400, 100, 25);
        contentPane.add(adding.getButton());
        
        
        
        this.add(contentPane);
		
	}
	
	private void createTextArea(JLabel text, JPanel contentPane, int[] list, Color color, boolean bool) {
		
		text.setBounds(list[0], list[1], list[2], list[3]);
		text.setForeground(color);
		text.setFont(new Font("Serif", Font.PLAIN, 13));
		text.setVisible(bool);
        contentPane.add(text);
		
	}
	
	public void adding() {
		TableObject table = getCurrentTable();
		
		if(table == null) {
			this.Laddrefused.setVisible(true);
	        this.Laddrefused2.setVisible(true);
			return;
		}
		
		AddingGui gui = new AddingGui(this, table);
		
		gui.setVisible(true);
		
	}
	
	public void delete() {
		
		this.Ldelete.setVisible(false);
        this.Ldelete2.setVisible(false);
        this.Ldeleterefused.setVisible(false);
        this.Ldeleterefused2.setVisible(false);
        this.Laddrefused.setVisible(false);
        this.Laddrefused2.setVisible(false);
        
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
			this.Ldeleterefused.setVisible(true);
	        this.Ldeleterefused2.setVisible(true);
			return;
		}
		
		if(!suppressed) return;
		
		showTable(table);
		
		this.Ldelete.setVisible(true);
        this.Ldelete2.setVisible(true);
		
	}
	
	private List<Integer> getMaxValues(TableObject table) {
		
		 List<Integer> maxvalues = new ArrayList<>();
	        
	     for(String attribut : table.getMaxSizeFromColumn()) {
	        	
	        	JLabel lab = new JLabel(attribut);
	        	maxvalues.add(lab.getMaximumSize().width);
	        	
	     }
	     
	     return maxvalues;
	}
	
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
	
	public static TableObject getCurrentTable() {
		
		TableObject table = null;
		
		for(JItemButton button : button_table.keySet()) {  
			
			if(button.getButton().getForeground() == Color.BLACK){
				 table = button_table.get(button) ;
			}
		}
		
		return table;
	}
	
	public ReturnNavigationListener<ContainerAdmin> getReturnNavigationListener() {
        return navigationListener;
    }
}
