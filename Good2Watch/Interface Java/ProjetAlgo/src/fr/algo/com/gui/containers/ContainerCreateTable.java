package fr.algo.com.gui.containers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import fr.algo.com.gui.AddColumnGui;
import fr.algo.com.gui.WarningGui;
import fr.algo.com.handler.InitTable;
import fr.algo.com.object.Column;
import fr.algo.com.utils.TableBuilder;


/**
 * <b>Classe JPanel de creation de tables</b>
 * <p>
 *   Cette classe va gérer la création d'une table
 * 
 * @author Thomas, Benjamin
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ContainerCreateTable extends JPanel{

	/**
     * Liste de colonnes
     */
	private ArrayList<Column> lstColumns = new ArrayList<>();
	
	/**
     * HashMap reliant une checkbox à sa colonne
     */
	public HashMap<JCheckBox, Column> check_box_column = new HashMap<>();
	
	/**
     * Liste des valeurs maximales
     */
	private ArrayList<Integer> max_values = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0));
	
	/**
     * Liste d'attributs
     */
	private ArrayList<String> attributs = new ArrayList<>(Arrays.asList("Nom","Type","Null","Valeur par défaut","Auto Increment","Clé Primaire"));
	
	/**
     * Zone de texte pour le nom de la table
     */
	private JTextField Ttable = new JTextField();
	
	/**
     * Container JPanel de gestion d'affichage
     */
	private MasterContainer mastercontainer;
	
	/**
	* Constructeur de la classe
	* @param mastercontainer container de gestion d'affichage
	* @see MasterContainer
	*/
	public ContainerCreateTable(MasterContainer mastercontainer) {
        
		this.mastercontainer = mastercontainer;
		setLayout(new GridLayout());
        
        
        ShowColumn();
          
    }
	
	/**
     * Methode permettant l'affichage des différentes colonnes
     * 
     */
	public void ShowColumn() {
		removeAll();

		this.check_box_column.clear();
		
		JPanel pan = new JPanel();
        pan.setBorder(BorderFactory.createLineBorder(Color.black));
        
        
	 	JPanel panel1 = new JPanel();
	 	panel1.setLayout(new BoxLayout(panel1,BoxLayout.X_AXIS));
	 	
	 	JLabel espace = new JLabel("");    
		espace.setBorder(new EmptyBorder(0,0,0,150));
        panel1.add(espace);
	 	
        initListColumn();
		for(String attribut : attributs) {
	 			 		
	 		JLabel lab = new JLabel(attribut);   
    		lab.setBorder(new EmptyBorder(0,10,20,getSpace(lab.getMaximumSize().width, attributs.indexOf(attribut))));
    		
            panel1.add(lab);
	 	}
	 	
        panel1.setBackground(Color.LIGHT_GRAY);
        pan.add(panel1);
        
        int nbColumn = 0;
        for(Column column : this.lstColumns) {
        	nbColumn++;
        	
        	JPanel a_panel = new JPanel();
            a_panel.setLayout(new BoxLayout(a_panel,BoxLayout.X_AXIS));
        	
        	JLabel lab = new JLabel("Colonne " + nbColumn +" :");    
    		lab.setBorder(new EmptyBorder(0,10,0,50));
    		a_panel.add(lab);
        	
        	JCheckBox checkbox = new JCheckBox();
            a_panel.add(checkbox);
            this.check_box_column.put(checkbox, column);
            
        	JLabel NameColumn = new JLabel(column.getName());
        	NameColumn.setBorder(new EmptyBorder(0,10,0, getSpace(NameColumn.getMaximumSize().width, 0)));
            a_panel.add(NameColumn);
            
            JLabel TypeColumn = new JLabel(column.getType());
            TypeColumn.setBorder(new EmptyBorder(0,10,0, getSpace(TypeColumn.getMaximumSize().width, 1)));
            a_panel.add(TypeColumn);
            
            if(column.isNotNull()) {
            	JLabel isNotNullColumn = new JLabel("True");
            	isNotNullColumn.setBorder(new EmptyBorder(0,10,0, getSpace(isNotNullColumn.getMaximumSize().width, 2)));
                a_panel.add(isNotNullColumn);
            }
            else {
            	JLabel isNotNullColumn = new JLabel("False");
            	isNotNullColumn.setBorder(new EmptyBorder(0,10,0, getSpace(isNotNullColumn.getMaximumSize().width, 2)));
                a_panel.add(isNotNullColumn);
            }
            
            if(column.hasDefaultValue()) {
            	JLabel hasDefaultValueColumn = new JLabel(column.getDefaultValue());
            	hasDefaultValueColumn.setBorder(new EmptyBorder(0,10,0, getSpace(hasDefaultValueColumn.getMaximumSize().width, 3)));
                a_panel.add(hasDefaultValueColumn);
            }
            else {
            	JLabel hasDefaultValueColumn = new JLabel("Pas de valeur par défaut");
            	hasDefaultValueColumn.setBorder(new EmptyBorder(0,10,0, getSpace(hasDefaultValueColumn.getMaximumSize().width, 3)));
                a_panel.add(hasDefaultValueColumn);
            }
            
            if(column.isAutoIncrement()) {
            	JLabel isAutoIncrementColumn = new JLabel("True");
            	isAutoIncrementColumn.setBorder(new EmptyBorder(0,10,0, getSpace(isAutoIncrementColumn.getMaximumSize().width, 4)));
                a_panel.add(isAutoIncrementColumn);
            }
            else {
            	JLabel isAutoIncrementColumn = new JLabel("False");
            	isAutoIncrementColumn.setBorder(new EmptyBorder(0,10,0, getSpace(isAutoIncrementColumn.getMaximumSize().width, 4)));
                a_panel.add(isAutoIncrementColumn);
            }
            
            if(column.isPrimary()) {
            	JLabel isPrimaryColumn = new JLabel("True");
            	isPrimaryColumn.setBorder(new EmptyBorder(0,10,0, getSpace(isPrimaryColumn.getMaximumSize().width, 5)));
                a_panel.add(isPrimaryColumn);
            }
            else {
            	JLabel isPrimaryColumn = new JLabel("False");
            	isPrimaryColumn.setBorder(new EmptyBorder(0,10,0, getSpace(isPrimaryColumn.getMaximumSize().width, 5)));
                a_panel.add(isPrimaryColumn);
            }

            pan.add(a_panel);
        }
        
        if(nbColumn< 3) {
        	GridLayout g = new GridLayout(3,1,5,5);
            pan.setLayout(g);
        }
        else {
        	GridLayout g = new GridLayout(nbColumn + 1,1,5,5);
            pan.setLayout(g);
        }
        
        
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
        
        this.Ttable.setBounds(400, 700, 150, 50);
        contentPane2.add(this.Ttable);
        
        JButton BValid = new JButton("Ajouter Table");
        BValid.setBounds(400, 800, 150, 50);
        contentPane2.add(BValid);
        
        this.add(contentPane2);
        
        this.setMinimumSize(new Dimension(800,100));
        this.setMaximumSize(new Dimension(800,100));
        
        revalidate();
        repaint();
        
//--------------Listeners---------------------------------------
        
        BColumn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	AddColumnGui columnGui = new AddColumnGui(getContainerCreateTable());
		    	columnGui.setVisible(true);
		    }
		    
		});
        
        BDelete.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	
		    	Column column = null;
		    	
		    	for(JCheckBox box : check_box_column.keySet()){
					
					if(!check_box_column.keySet().isEmpty()) {
						
						if(box.isSelected()) {
							
							column = check_box_column.get(box);
							
							lstColumns.remove(column);
						}
					}
				}
		    	
		    	ShowColumn();
		    }
		    
		});
        
        BValid.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	
		    	String tableName = getContainerCreateTable().Ttable.getText();
		    	
		    	TableBuilder table_builder = new TableBuilder(tableName);
		    	
		    	for(Column column : lstColumns) {
		    		
		    		table_builder.addColumn(column);
		    		
		    	}
		    	
		    	if(lstColumns.isEmpty()) return;
		    	
		    	if(tableName.isBlank()) {
		    		new WarningGui("Veuillez ajouter un nom de table").setVisible(true);
		    		return;
		    	}
		    		
		    	
		    	table_builder.build();
		    	
		    	InitTable.initTable();
		    	
		    	getMasterContainer().returnFromCreateTable();
		    }
		    
		});
	}
	
	/**
     * Initialise les valeurs des colonnes
     * 
     */
	public void initListColumn() {
		
		for(String attribut : attributs) {
			
			JLabel lab = new JLabel(attribut);   
			
			if(lab.getMaximumSize().width > max_values.get(attributs.indexOf(attribut))){
				max_values.set(attributs.indexOf(attribut), lab.getMaximumSize().width);
			}
			
		} 
		
		for(Column column : this.lstColumns) {
			
			for(int i = 0; i < 6; i++) {
				switch (i) {
				case 0: {
					JLabel lab = new JLabel(column.getName());  
					if(lab.getMaximumSize().width > max_values.get(i)){
						max_values.set(i, lab.getMaximumSize().width);
					}
				}
				case 1: {
					JLabel lab = new JLabel(column.getType());  
					if(lab.getMaximumSize().width > max_values.get(i)){
						max_values.set(i, lab.getMaximumSize().width);
					}
				}
				case 2: {
					JLabel lab = null;  
					if(column.isNotNull()) {
						lab = new JLabel("True");  
					} else {
						lab = new JLabel("False");  
					}
					if(lab.getMaximumSize().width > max_values.get(i)){
						max_values.set(i, lab.getMaximumSize().width);
					}
				}
				case 3: {
					JLabel lab = null;  
					if(column.hasDefaultValue()) {
						lab = new JLabel(column.getDefaultValue());  
					} else {
						lab = new JLabel("Pas de valeur par défaut");  
					}
					if(lab.getMaximumSize().width > max_values.get(i)){
						max_values.set(i, lab.getMaximumSize().width);
					}
				}
				case 4: {
					JLabel lab = null;  
					if(column.isAutoIncrement()) {
						lab = new JLabel("True");  
					} else {
						lab = new JLabel("False");  
					}
					if(lab.getMaximumSize().width > max_values.get(i)){
						max_values.set(i, lab.getMaximumSize().width);
					}
				}
				case 5: {
					JLabel lab = null;  
					if(column.isPrimary()) {
						lab = new JLabel("True");  
					} else {
						lab = new JLabel("False");  
					}
					if(lab.getMaximumSize().width > max_values.get(i)){
						max_values.set(i, lab.getMaximumSize().width);
					}
					
				}
			}
			}
			
		}
		
	}
	
	/**
     * Recupère l'espace nécessaire entre deux colonnes
     * 
     * @param width largeur
     * @param id_column id de la colonne
     * @return un entier correspondant à l'espace
     */
	public int getSpace(int width, int id_column) {
		
		int space = max_values.get(id_column) + 30;
		int real_space = space - width;
		
		return real_space;
		
	}
	
	/**
     * Ajoute la nouvelle colonne à la liste de colonnes
     * 
     * @param colonne colonne à ajouter
     * @see Column
     */
	public void AddColumn(Column colonne) {
		this.lstColumns.add(colonne);
		ShowColumn();
	}
	
	/**
     * Getter de cet objet
     * 
     * @return une instance de cette classe
     * @see ContainerCreateTable
     */
	public ContainerCreateTable getContainerCreateTable() {
		return this;
		
	}
	
	/**
     * Getter de masterContainer
     * 
     * @return une intance de MasterContainer
     * @see MasterContainer
     */
	public MasterContainer getMasterContainer() {
		return this.mastercontainer;
		
	}
    
}
