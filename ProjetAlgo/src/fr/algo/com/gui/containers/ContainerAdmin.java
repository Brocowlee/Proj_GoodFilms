package fr.algo.com.gui.containers;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
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
	public HashMap<String, JCheckBox> check_box_ids = new HashMap<>();
	
	private ReturnNavigationListener<ContainerAdmin> navigationListener;
	
	
	public ContainerAdmin(ReturnNavigationListener<ContainerAdmin> navigationListener){
			this.navigationListener = navigationListener;
		
			setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		 
			JPanel panel = new JPanel();
	        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	        
	        for(TableObject table : InitTable.liste_tables.values()) {
	        	
	          JItemButton button = new JItemButton(table.getName(), this);
	          
	      	  liste_buttons.put(table.getName(), button);
	      	  
	          panel.add(button.getButton());
	      	
	        }
	        
	        JScrollPane scrollPane = new JScrollPane(panel);
	        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	        scrollPane.setBounds(0, 0, 150, 250);
	        scrollPane.setBorder(BorderFactory.createLineBorder(Color.black));
	        JPanel contentPane = new JPanel(null);
	        contentPane.setMinimumSize(new Dimension(4500,1000));
	        contentPane.setMaximumSize(new Dimension(4500,1000));
	        contentPane.add(scrollPane);
	        add(contentPane);
	        
	  //--------------------------------------------------------------------------------------------------------------------------      
	        
	        
	        
	       
	        
	        
	}
	
	public void showTable(TableObject table, ContainerAdmin containerAdmin) {
		
		removeAll();
		
		JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        for(TableObject to : InitTable.liste_tables.values()) {
        	
          JItemButton button = new JItemButton(to.getName(), this);
          
      	  liste_buttons.put(to.getName(), button);
      	  
          panel.add(button.getButton());
      	
        }
        
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(0, 0, 150, 250);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.black));
        JPanel contentPane = new JPanel(null);
        contentPane.setMinimumSize(new Dimension(4500,1000));
        contentPane.setMaximumSize(new Dimension(4500,1000));
        contentPane.add(scrollPane);
        containerAdmin.add(contentPane);
		
		JPanel pan = new JPanel();
        pan.setBorder(BorderFactory.createLineBorder(Color.black));
        
        
	 	JPanel panel1 = new JPanel();
	 	panel1.setLayout(new BoxLayout(panel1,BoxLayout.X_AXIS));
	 	
        
        for(String column : table.getColumnName()) {
        	
        	  JLabel lab = new JLabel(column);
              lab.setBorder(new EmptyBorder(0,50,0,0));
              
        	  panel1.add(lab);
        }
        panel1.setBackground(Color.LIGHT_GRAY);
        pan.add(panel1);
        
        for(List<String> list : table.selectAll()) {
        	
        	JPanel a_panel = new JPanel();
            a_panel.setLayout(new BoxLayout(a_panel,BoxLayout.X_AXIS));
            
        	for(String attribut : list) {
        		
        		JLabel lab = new JLabel(attribut);
        		
        		int space = 100 - lab.getMaximumSize().width;
        		
        		
                lab.setBorder(new EmptyBorder(0,10,0,space));
                
                a_panel.add(lab);
                
                
               /* if(list.get(1).equalsIgnoreCase(attribut)) {
            		System.out.println("witdth : " + lab.getMaximumSize().width + " | length : " + attribut.length());
            	} */
                
                
        	}
        	
        	
        	JCheckBox checkbox = new JCheckBox();
            a_panel.add(checkbox);
            check_box_ids.put(list.get(0), checkbox);
            pan.add(a_panel);
        	
        }
        
        int total_lines = table.getTotalLine() + 1;
        
        if(total_lines < 33) total_lines = 33;
        
        GridLayout g = new GridLayout(total_lines,1,5,5);
        pan.setLayout(g);
        
        JScrollPane scrollPane2 = new JScrollPane(pan);
        scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane2.setBounds(0, 0, 820, 900);
        JPanel contentPane2 = new JPanel(null);
        contentPane2.setMinimumSize(new Dimension(23000,1000));
        contentPane2.setMaximumSize(new Dimension(23000,1000));
        contentPane2.add(scrollPane2);
        
        containerAdmin.add(contentPane2);
        
        containerAdmin.setMinimumSize(new Dimension(800,500));
        containerAdmin.setMaximumSize(new Dimension(800,500));
        revalidate();
        repaint();
		
		
	}
	
	
	public ReturnNavigationListener<ContainerAdmin> getReturnNavigationListener() {
        return navigationListener;
    }
}
