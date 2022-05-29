package fr.algo.com.gui;


import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import fr.algo.com.gui.containers.ContainerCreateTable;
import fr.algo.com.object.Column;
import fr.algo.com.object.TableObject;

/**
 * <b>Classe permettant d'ajouter une colonne à une table dans un GUI</b>
 * <p>
 *   Cette classe va réaliser le gui permettant l'ajout d'une colonne à une table
 *
 * @see ContainerCreateTable
 * 
 * @author Thomas, Benjamin
 * @version 1.0
 */

@SuppressWarnings("serial")
public class AddColumnGui extends JFrame {
	
	/**
     * Image servant d'icon
     */
	private ImageIcon icon = new ImageIcon("./Icon.jpg");
	
	/**
     * Label pour le nom
     */
	private JLabel LName = new JLabel("Nom de la colonne : ");
	
	/**
     * Zone de texte pour le nom
     */
	private JTextField TName = new JTextField();
	
	/**
     * Label pour le type
     */
	private JLabel LType = new JLabel("Type de la colonne : ");
	
	/**
     * ComboBox pour le type
     */
	private JComboBox<String> CBType = new JComboBox<>();
	
	/**
     * Label pour la valeur par defaut
     */
	private JLabel LDefaultValue = new JLabel("Valeur par défaut : ");
	
	/**
     * Zone de texte pour la valeur par defaut
     */
	private JTextField TDefaultValue = new JTextField();
	
	/**
     * Label si la valeur peut etre nul
     */
	private JLabel LisNull = new JLabel("Valeur null ? : ");
	
	/**
     * CheckBox isNull
     */
	private JCheckBox CisNull = new JCheckBox();
	
	/**
     * Label si la valeur peut etre autoIncrement
     */
	private JLabel LisAutoIncrement = new JLabel("Auto Increment ? : ");
	
	/**
     * Checkbox is AutoIncrement
     */
	private JCheckBox CisAutoIncrement = new JCheckBox();
	
	/**
     * Label si la valeur est une clé primaire
     */
	private JLabel LisPrimary = new JLabel("Primary Key ? : ");
	
	/**
     * Checkbox is Primary
     */
	private JCheckBox CisPrimary = new JCheckBox();
	
	/**
	* Constructeur de la classe
	* @param containerCreateTable instance de création d'une nouvelle table
	* @see ContainerCreateTable
	*/
	public AddColumnGui(ContainerCreateTable containerCreateTable) {
		
		setTitle("Ajouter une colonne");
		setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(500,300));
        setMaximumSize(new Dimension(500,300));
		setLocationRelativeTo(null);
		setResizable(false);
		pack();
		
		JPanel pan = new JPanel(new GridLayout(8,2,0,0));
		
		addJComponents2JPanel(pan, LName, TName, LType, setType(this.CBType), LDefaultValue, TDefaultValue, LisNull, CisNull, LisAutoIncrement, CisAutoIncrement, LisPrimary, CisPrimary); 
		
		JButton button = new JButton("Ajouter");
		pan.add(button);
		
        add(pan);
        
        button.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	
		    	String Name = getAddColumnGui().TName.getText();
		    	
		    	String Type = (String) getAddColumnGui().CBType.getSelectedItem();
		    	String DefaultValue = getAddColumnGui().TDefaultValue.getText();
		    	
		    	boolean isNull;
		    	if(getAddColumnGui().CisNull.isSelected()) {
		    		isNull = true;
		    	}
		    	else {
		    		isNull = false;
		    	}
		    	
		    	boolean isPrimary;
		    	if(getAddColumnGui().CisPrimary.isSelected()) {
		    		isPrimary = true;
		    	}
		    	else {
		    		isPrimary = false;
		    	}
		    	
		    	boolean isAutoIncrement;
		    	if(getAddColumnGui().CisAutoIncrement.isSelected()) {
		    		isAutoIncrement = true;
		    	}
		    	else {
		    		isAutoIncrement = false;
		    	}
		    	
		    	if(Name.isBlank()) {
		    		new WarningGui("Aucun nom renseigné").setVisible(true);
		    		return;
		    	}
		    	
		    	Column column = new Column(Name);
		    	column.setType(Type);
		    	column.setDefaultValue(DefaultValue);
				column.setNotNull(isNull);
		    	column.setPrimary(isPrimary);
		    	column.setAutoIncrement(isAutoIncrement);
		    	
		    	containerCreateTable.AddColumn(column);
		    	getAddColumnGui().dispose();
		    }
		    
		});
        
	}
	
	/**
     * Ajoute un nombre indéfini de JComponent à un JPanel tout en définissant l'espace entre les JComponents
     * 
     * @param pan Jpanel 
     * @param components liste de JComponent
     */
	private void addJComponents2JPanel(JPanel pannel, JComponent... components) {
		
		for(JComponent component : components) {
			if(component instanceof JLabel) {
				component.setBorder(new EmptyBorder(0, 30, 0, 0));
			}
			pannel.add(component);
		}
		
		for(int i = 0; i<3; i++) pannel.add(new JLabel(""));
		
	}

	/**
     * Definit les élements de la checkbox de types
     * 
     * @param box JCheckBox de type
     * @return une JComboBox contenant tous les types
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JComboBox setType(JComboBox box) {
		
		ArrayList<String> lstType = new ArrayList<>(Arrays.asList("INT","VARCHAR(20)","VARCHAR(50)","VARCHAR(100)","CHAR(20)","CHAR(50)","CHAR(100)","BINARY(20)","BINARY(50)","BINARY(100)",
				"VARBINARY(20)","VARBINARY(50)","VARBINARY(100)","TINYBLOB","TINYTEXT","TEXT","BLOB(20)","BLOB(50)","BLOB(100)",
				"MEDIUMTEXT","MEDIUMBLOB","LONGTEXT","LONGBLOB","BIT(20)","BIT(50)","BIT(100)","TINYINT","BOOL","BOOLEAN","SMALLINT","MEDIUMINT",
				"BIGINT","FLOAT","DOUBLE","DECIMAL","DATE","DATETIME","TIMESTAMP","TIME"));
		
		for(String item : lstType) {
			box.addItem(item);
		}
		
		return box;
		
	}
	
	/**
     * Retourne une instance AddColumnGui.
     *
     * @return Une instance d'un AddColumnGui
     */
	public AddColumnGui getAddColumnGui() {
		return this;
		
	}
}