package fr.algo.com.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class WarningGui extends JFrame{
	

	public ImageIcon img = new ImageIcon("./attention.png");
	public Image attention1 = img.getImage();
	private ImageIcon attention = new ImageIcon(attention1.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH));
	
	private String message;
	private JButton button;
	
	public WarningGui(String message) {
	
		this.message = message;
		
		setTitle("Warning");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(400,200));
        setMaximumSize(new Dimension(400,200));
		setLocationRelativeTo(null);
		setResizable(false);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.ERROR_DIALOG);
		pack();
		
		JPanel contentPane = new JPanel(new BorderLayout(0, 0));
		
		JLabel LMessage = new JLabel(this.message, SwingConstants.CENTER);
		LMessage.setForeground(Color.RED);
		contentPane.add(LMessage,BorderLayout.CENTER);
		
		this.button = new JButton("OK");
		contentPane.add(button,BorderLayout.SOUTH);
		
		JLabel Limg = new JLabel(this.attention);
		contentPane.add(Limg,BorderLayout.WEST);
		
		add(contentPane);
		
		this.button.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    
		    		getWarningGui().dispose();
		    }
		    
		});
		
	}
	
	public WarningGui getWarningGui() {
		return this;
		
	}
}
