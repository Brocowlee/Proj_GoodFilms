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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import fr.algo.com.gui.containers.ContainerCreateTable;
import fr.algo.com.object.Column;


@SuppressWarnings("serial")
public class AddColumnGui extends JFrame{

	private ImageIcon icon = new ImageIcon("./Icon.jpg");
	
	private JLabel LName = new JLabel("Nom de la colonne : ");
	private JTextField TName = new JTextField();
	private JLabel LType = new JLabel("Type de la colonne : ");
	private JComboBox<String> CBType = new JComboBox<>();
	private JLabel LDefaultValue = new JLabel("Valeur par défaut : ");
	private JTextField TDefaultValue = new JTextField();
	private JLabel LisNull = new JLabel("Valeur null ? : ");
	private JCheckBox CisNull = new JCheckBox();
	private JLabel LisAutoIncrement = new JLabel("Auto Increment ? : ");
	private JCheckBox CisAutoIncrement = new JCheckBox();
	private JLabel LisPrimary = new JLabel("Primary Key ? : ");
	private JCheckBox CisPrimary = new JCheckBox();
	
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
		
		pan.add(LName);
		this.LName.setBorder(new EmptyBorder(0, 30, 0, 0));
		pan.add(TName);
		pan.add(LType);
		this.LType.setBorder(new EmptyBorder(0, 30, 0, 0));
		pan.add(setType(this.CBType));
		pan.add(LDefaultValue);
		this.LDefaultValue.setBorder(new EmptyBorder(0, 30, 0, 0));
		pan.add(TDefaultValue);
		pan.add(LisNull);
		this.LisNull.setBorder(new EmptyBorder(0, 30, 0, 0));
		pan.add(CisNull);
		pan.add(LisAutoIncrement);
		this.LisAutoIncrement.setBorder(new EmptyBorder(0, 30, 0, 0));
		pan.add(CisAutoIncrement);
		pan.add(LisPrimary);
		this.LisPrimary.setBorder(new EmptyBorder(0, 30, 0, 0));
		pan.add(CisPrimary);
		
		pan.add(new JLabel(""));
		pan.add(new JLabel(""));
		pan.add(new JLabel(""));
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JComboBox setType(JComboBox box) {
		
		ArrayList<String> lstType = new ArrayList<>(Arrays.asList("INT","VARCHAR(20)","VARCHAR(50)","VARCHAR(100)"));
		for(String item : lstType) {
			box.addItem(item);
		}
		
		return box;
		
	}
	
	
	public AddColumnGui getAddColumnGui() {
		return this;
		
	}
}
