package fr.algo.com.gui.containers;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import fr.algo.com.gui.ReturnNavigationListener;
import fr.algo.com.gui.containers.JItems.JItemButton;
import fr.algo.com.handler.InitTable;
import fr.algo.com.object.TableObject;

@SuppressWarnings("serial")
public class ContainerAdmin extends JPanel {
	
	
	public HashMap<String, JItemButton> liste_buttons = new HashMap<>();
	public HashMap<JCheckBox, String> check_box_ids = new HashMap<>();
	public HashMap<JCheckBox, TableObject> check_box_table = new HashMap<>();
	
	private JLabel Ldelete = new JLabel("La suppression");
    private JLabel Ldelete2 = new JLabel("a été effectué");
    private JLabel Ldeleterefused = new JLabel("Veuillez choisir");
    private JLabel Ldeleterefused2 = new JLabel("une donnée");
    
    
	private ReturnNavigationListener<ContainerAdmin> navigationListener;
	
	
	public ContainerAdmin(ReturnNavigationListener<ContainerAdmin> navigationListener){
			this.navigationListener = navigationListener;
		
			setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		 
			showMenu();
	        
	  //--------------------------------------------------------------------------------------------------------------------------      
	        
	}
	
	public void showMenu() {
		
		String type = "Table";
		JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        for(TableObject table : InitTable.liste_tables.values()) {
        	
          JItemButton button = new JItemButton(table.getName(), this, type);
          
      	  liste_buttons.put(table.getName(), button);
      	  
          button.getButton().setMinimumSize(new Dimension(150,30));
      	  button.getButton().setMaximumSize(new Dimension(150,30));
        
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
        
        this.Ldelete.setBounds(35, 340, 100, 25);
        this.Ldelete.setForeground(Color.RED);
        this.Ldelete.setFont(new Font("Serif", Font.PLAIN, 13));
        this.Ldelete.setVisible(false);
        contentPane.add(this.Ldelete);

        this.Ldelete2.setBounds(40, 360, 100, 25);
        this.Ldelete2.setForeground(Color.RED);
        this.Ldelete2.setFont(new Font("Serif", Font.PLAIN, 13));
        this.Ldelete2.setVisible(false);
        contentPane.add(this.Ldelete2);
        
        this.Ldeleterefused.setBounds(35, 340, 100, 25);
        this.Ldeleterefused.setForeground(Color.RED);
        this.Ldeleterefused.setFont(new Font("Serif", Font.PLAIN, 13));
        this.Ldeleterefused.setVisible(false);
        contentPane.add(this.Ldeleterefused);

        this.Ldeleterefused2.setBounds(40, 360, 100, 25);
        this.Ldeleterefused2.setForeground(Color.RED);
        this.Ldeleterefused2.setFont(new Font("Serif", Font.PLAIN, 13));
        this.Ldeleterefused2.setVisible(false);
        contentPane.add(this.Ldeleterefused2);
        
        this.add(contentPane);
		
	}

	
	public void delete() {
		
		this.Ldelete.setVisible(false);
        this.Ldelete2.setVisible(false);
        this.Ldeleterefused.setVisible(false);
        this.Ldeleterefused2.setVisible(false);
        
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
		removeAll();
		
		showMenu();
		
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
	 			
	 			if(i < 4) {
	 				space += (maxvalues.get(i) / 2) + 30;
	 				space += (maxvalues.get(i+1) / 2);
	 			} else {
	 				space += maxvalues.get(i) + 30;
	 			}
	 		}
	 		
	 		
    		int real_space = space - lab.getMaximumSize().width;
    		
    		if(i == 0) {
    			lab.setBorder(new EmptyBorder(0,30,0,real_space));
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
        
        if(total_lines < 33) total_lines = 33;
        
        GridLayout g = new GridLayout(total_lines,1,5,5);
        pan.setLayout(g);
        
        JScrollPane scrollPane2 = new JScrollPane(pan);
        scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane2.setBounds(0, 0, 835, 900);
        JPanel contentPane2 = new JPanel(null);
        contentPane2.setMinimumSize(new Dimension(835,1000));
        contentPane2.setMaximumSize(new Dimension(835,1000));
        contentPane2.add(scrollPane2);
        
        this.add(contentPane2);
        
        this.setMinimumSize(new Dimension(800,500));
        this.setMaximumSize(new Dimension(800,500));
        revalidate();
        repaint();
		
		
	}
	
	
	public ReturnNavigationListener<ContainerAdmin> getReturnNavigationListener() {
        return navigationListener;
    }
}
