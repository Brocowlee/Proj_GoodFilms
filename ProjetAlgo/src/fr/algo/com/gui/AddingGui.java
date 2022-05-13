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

import fr.algo.com.gui.containers.ContainerAdmin;
import fr.algo.com.object.TableObject;

@SuppressWarnings("serial")
public class AddingGui extends JFrame {
	
	private ImageIcon icon = new ImageIcon("./Icon.jpg");
	
	private ArrayList<JTextField> textField_list = new ArrayList<>();	
	
	private TableObject table;
	
	public AddingGui(ContainerAdmin containerAdmin, TableObject table) {
		
		setTitle("Ajouter une donnée");
		setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(800,200));
        setMaximumSize(new Dimension(800,200));
		setLocationRelativeTo(null);
		setResizable(false);
		pack();
		
		JPanel pan = new JPanel();
        
	 	JPanel panel1 = new JPanel();
	 	panel1.setLayout(new BoxLayout(panel1,BoxLayout.X_AXIS));
	 	
	 	this.table = table;
	 	
	 	for(int i = 0; i < this.table.getColumnName().size(); i++) {
	 		
	 		String column = (String) this.table.getColumnName().toArray()[i];
	 		
	 		JLabel lab = new JLabel(column);
	 		
	 		lab.setBorder(new EmptyBorder(0,50,0,0));
	 		setSizeAdd(lab,new Dimension(800,505),panel1);
            
	 	}
	 	
        panel1.setBackground(Color.LIGHT_GRAY);

        panel1.setBounds(0, 0, 900, 50);
        pan.add(panel1);
        
        //---------------------------------------------------------------------------------------------
        
        JPanel a_panel = new JPanel();
        a_panel.setLayout(new BoxLayout(a_panel,BoxLayout.X_AXIS));
        
        for(int i = 0; i < this.table.getColumnName().size(); i++) {
	 		
        	JTextField textField = new JTextField();
    		
        	textField.setBorder(new EmptyBorder(0,10,0,5));
        	textField.setBorder(BorderFactory.createLineBorder(Color.black));
        	setSizeAdd(textField,new Dimension(800,505),a_panel);
            this.textField_list.add(textField);
    		
	 	}
    	
        pan.add(a_panel);
        
        GridLayout g = new GridLayout(2,1,5,5);
        pan.setLayout(g);
        
        JScrollPane scrollPane = new JScrollPane(pan);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(0, 0, 785, 100);
        JPanel contentPane = new JPanel(null);
        contentPane.setMinimumSize(new Dimension(500, 400));
        contentPane.setMaximumSize(new Dimension(500, 400));
        contentPane.add(scrollPane);
        
        JButton bAjout = new JButton("Ajouter");
        setSizeAdd(bAjout,new Dimension(800,505),contentPane);
        bAjout.setBounds(350, 105, 100, 50);
        
        
        
        setContentPane(contentPane);
        
        
        revalidate();
        repaint();
        
        bAjout.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	
		    	ArrayList<String> values = new ArrayList<>();
		    	List<Integer> indexes = new ArrayList<>();
		    	
		    	
		    	for(int i = 0; i < textField_list.size(); i++) {
		    		
		    		JTextField text = textField_list.get(i);
		    		
		    		if(text.getText().length() > 0) {
		    			values.add(text.getText());
		    			indexes.add(i);
		    		}
		    		
		    		
		    		
		    	}
		    	
		    	getAddingGui().dispose();
		    	
		    	if(values.size() < table.getTotalColumn()) {
		    		
		    		new WarningGui("Au moins une valeur non renseignée").setVisible(true);
		    		
		    		return;
		    	}
		    	
		    	if(table.alreadyHasPrimaryKey(values.get(0))) {
		    		
		    		new WarningGui("Clé primaire déjà existante").setVisible(true);
		    		
					return;
				}
		    	
		    	
		    	if(values.isEmpty() && indexes.isEmpty()) return;
		    	
		    	if(table.insertInto(indexes, values)) {
		    		
		    		containerAdmin.showTable(table);
		    		
		    		containerAdmin.setAddButton(true);
		    		
		    	} else {
		    		
		    		new WarningGui("Au moins une valeur n'a pas le bon type").setVisible(true);
		    		
		    	}
		    }
		    
		});
        
	}
	
	public static void setSizeAdd(Object test,Dimension dim,JPanel pan) {
		
		((JComponent) test).setMinimumSize(dim);
        ((JComponent) test).setMaximumSize(dim);
        pan.add((JComponent) test);
	}

	public AddingGui getAddingGui() {
		return this;
	
	}

}
