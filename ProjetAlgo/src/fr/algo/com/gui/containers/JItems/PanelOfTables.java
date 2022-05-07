package fr.algo.com.gui.containers.JItems;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class PanelOfTables extends JPanel{

	
	public PanelOfTables() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
        for (int i = 0; i < 20; i++) {
        	JButton button = new JButton("Hello-" + i);
        	button.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(button);
        }
		
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(50,30, 100, 200);
        setPreferredSize(new Dimension(600, 500));
        setMinimumSize(new Dimension(600, 500));
        setMaximumSize(new Dimension(600, 500));
        add(scrollPane);
        
        
	}

}
