package fr.algo.com.gui.containers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import fr.algo.com.gui.containers.JItems.JItemButton;
import fr.algo.com.object.Column;


@SuppressWarnings("serial")
public class ContainerCreateTable extends JPanel{
	
	private int nbColumn = 0;
	private ArrayList<Column> lstColumns = new ArrayList<>();
	public HashMap<JCheckBox, Column> check_box_column = new HashMap<>();
	public HashMap<JButton, Column> edit_column = new HashMap<>();
	
	ImageIcon edit = new ImageIcon("./edit.png");
	Image imgedit = edit.getImage();
	private ImageIcon editIcon = new ImageIcon(imgedit.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
	
	Column col = new Column("Test");
	Column col2 = new Column("Test22222222222222222222222");
	
	public ContainerCreateTable() {
        
		setLayout(new GridLayout());
		col.setType("INT");
		lstColumns.add(col);
		
		col2.setType("VARCHAR");
		lstColumns.add(col2);
        
        JPanel pan = new JPanel();
        pan.setBorder(BorderFactory.createLineBorder(Color.black));
        
        
	 	JPanel panel1 = new JPanel();
	 	panel1.setLayout(new BoxLayout(panel1,BoxLayout.X_AXIS));
	 	
	 	JLabel espace = new JLabel("");    
		espace.setBorder(new EmptyBorder(0,0,0,150));
        panel1.add(espace);
	 	
	 	ArrayList<String> attributs = new ArrayList<>(Arrays.asList("Nom","Type","Est null","A une Valeur par défaut","Est AutoIncrement","Est Clé Primaire"));
		for(String attribut : attributs) {
	 			 		
	 		JLabel lab = new JLabel(attribut);   
    		lab.setBorder(new EmptyBorder(0,10,20,100));
    		
            panel1.add(lab);
	 	}
	 	
        panel1.setBackground(Color.LIGHT_GRAY);
        pan.add(panel1);
        
        
        for(Column column : this.lstColumns) {
        	this.nbColumn++;
        	
        	JPanel a_panel = new JPanel();
            a_panel.setLayout(new BoxLayout(a_panel,BoxLayout.X_AXIS));
        	
        	JLabel lab = new JLabel("Colonne " + this.nbColumn +" :");    
    		lab.setBorder(new EmptyBorder(0,10,0,50));
    		a_panel.add(lab);
        	
        	JCheckBox checkbox = new JCheckBox();
            a_panel.add(checkbox);
            check_box_column.put(checkbox, column);
            
            
            JItemButton editbutton = new JItemButton(this.editIcon,this,"Edit");
            a_panel.add(editbutton.getButton());
            edit_column.put(editbutton.getButton(), column);
            
        		
        	JLabel NameColumn = new JLabel(column.getName());
        	NameColumn.setBorder(new EmptyBorder(0,10,0, 100));
            a_panel.add(NameColumn);
                
            JLabel TypeColumn = new JLabel(column.getType());
            TypeColumn.setBorder(new EmptyBorder(0,10,0, 100));
            a_panel.add(TypeColumn);
            
            if(column.isNotNull()) {
            	JLabel isNotNullColumn = new JLabel("True");
            	isNotNullColumn.setBorder(new EmptyBorder(0,10,0, 100));
                a_panel.add(isNotNullColumn);
            }
            else {
            	JLabel isNotNullColumn = new JLabel("False");
            	isNotNullColumn.setBorder(new EmptyBorder(0,10,0, 100));
                a_panel.add(isNotNullColumn);
            }
            
            if(column.hasDefaultValue()) {
            	JLabel hasDefaultValueColumn = new JLabel(column.getDefaultValue());
            	hasDefaultValueColumn.setBorder(new EmptyBorder(0,10,0, 100));
                a_panel.add(hasDefaultValueColumn);
            }
            else {
            	JLabel hasDefaultValueColumn = new JLabel("Pas de valeur par défaut");
            	hasDefaultValueColumn.setBorder(new EmptyBorder(0,10,0, 100));
                a_panel.add(hasDefaultValueColumn);
            }
            
            if(column.isAutoIncrement()) {
            	JLabel isAutoIncrementColumn = new JLabel("True");
            	isAutoIncrementColumn.setBorder(new EmptyBorder(0,10,0, 100));
                a_panel.add(isAutoIncrementColumn);
            }
            else {
            	JLabel isAutoIncrementColumn = new JLabel("False");
            	isAutoIncrementColumn.setBorder(new EmptyBorder(0,10,0, 100));
                a_panel.add(isAutoIncrementColumn);
            }
            
            if(column.isPrimary()) {
            	JLabel isPrimaryColumn = new JLabel("True");
            	isPrimaryColumn.setBorder(new EmptyBorder(0,10,0, 100));
                a_panel.add(isPrimaryColumn);
            }
            else {
            	JLabel isPrimaryColumn = new JLabel("False");
            	isPrimaryColumn.setBorder(new EmptyBorder(0,10,0, 100));
                a_panel.add(isPrimaryColumn);
            }

            pan.add(a_panel);
        }
        
        
        GridLayout g = new GridLayout(this.nbColumn + 1,1,5,5);
        pan.setLayout(g);
        
        JScrollPane scrollPane2 = new JScrollPane(pan);
        scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        
        scrollPane2.setBounds(0, 0, 985, 500);
        
        
        JPanel contentPane2 = new JPanel(null);
        contentPane2.setMinimumSize(new Dimension(100,100));
        contentPane2.setMaximumSize(new Dimension(100,100));
        contentPane2.add(scrollPane2);
        
        JButton BColumn = new JButton("Ajouter Colonne");
        BColumn.setBounds(500, 600, 150, 50);
        contentPane2.add(BColumn);
        JButton BDelete = new JButton("Supprimer Colonne");
        BDelete.setBounds(300, 600, 150, 50);
        contentPane2.add(BDelete);
        
        
        JLabel Ltable = new JLabel("Nom de la table : ");
        Ltable.setBounds(300, 700, 100, 50);
        contentPane2.add(Ltable);
        JTextField Ttable = new JTextField();
        Ttable.setBounds(400, 700, 150, 50);
        contentPane2.add(Ttable);
        
        JButton BValid = new JButton("Ajouter Table");
        BValid.setBounds(400, 800, 150, 50);
        contentPane2.add(BValid);
        
        add(contentPane2);
        
        setMinimumSize(new Dimension(800,100));
        setMaximumSize(new Dimension(800,100));
        
        
        
        //--------------Listeners---------------------------------------
        
        BColumn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	//TODO
		    }
		    
		});
        
        BDelete.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	//TODO
		    }
		    
		});
        
        BValid.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	//TODO
		    }
		    
		});
        
    }
    
}
